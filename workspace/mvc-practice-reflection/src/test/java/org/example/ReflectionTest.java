package org.example;

import org.example.annonation.Controller;
import org.example.annonation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Controller 어노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 * */
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatiedWith(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]", beans);
    }

    @Test
    void showClass(){
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }


    @Test
    void load() throws ClassNotFoundException {
        /**
         * 힙 영역에 로드되어 있는 클래스 객체를 가져오는 방법
         * 1. Class.class
         * 2. instance.getClass
         * 3. Class.forName()
         * */
        Class<User> clazz = User.class;
        User user = new User("userid", "mihee");
        Class<? extends User> clazz2 = user.getClass();
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz: [{}]",clazz);
        logger.debug("clazz: [{}]",clazz2);
        logger.debug("clazz: [{}]",clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();

    }

    private static Set<Class<?>> getTypesAnnotatiedWith(List<Class<? extends Annotation>> annotations) {
        // org.example 하위의 패키지에서 적용
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();

        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        // org.example 패키지 하위의 클래스에서 Controller 어노테이션이 붙은 클래스를 찾아서 beans에 담은
        // beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        // beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
        return beans;
    }
}
