package org.example.di;

import org.example.annonation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactorUtils {
    public static Constructor getInjectredConstructor(Class<?> clazz) {
        // clazz 타입의 모든 constructor 중에서, Inject 어노테이션이 붙은 생성자만 가져옴
        Set<Constructor> inejctedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if (inejctedConstructors.isEmpty()) {
            return null;
        }
        return inejctedConstructors.iterator().next();
    }
}
