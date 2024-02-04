package org.example.di;

import org.example.annonation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.example.di.BeanFactorUtils.getInjectredConstructor;


public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    // ex) 얘를 들어 UserController 라는 클래스 타입 객체가 들어와서, 생성자를 가지고 오고
    // 생성자를 통해 파라미터 정보를 가지고 오고, 파라미터에 대한 getParameterByClass 호출
    // 위의 과정에 의해 UserService getBean을 호출했는데, 아직 userController에 대한 인스턴스도 호출되기 이전의 상태 (즉 아직 UserService에 대한 빈이 존재하지 않음)
    // 때문에 createInstance를 통해 다시 생성, 즉 UserController, UserService
    private Object createInstance(Class<?> clazz) {
        // 생성자
        Constructor<?> constructor = findConstructor(clazz);

        // 파라미터
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getParameterByClass(typeClass));
        }

        // 인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = getInjectredConstructor(clazz);
        if (Objects.nonNull(constructor)) {
            return constructor;
        }
        return clazz.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);
        if (Objects.nonNull(instanceBean)){
            return instanceBean;
        }
        return createInstance(typeClass);

    }


    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }


}
