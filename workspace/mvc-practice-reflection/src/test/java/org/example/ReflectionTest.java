package org.example;

import org.example.annonation.Controller;
import org.example.annonation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
