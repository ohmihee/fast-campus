package org.example.di;

import org.example.annonation.Controller;
import org.example.annonation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        // UserController UserService
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);
        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}

/**
 * TopDown 방식
 * 메서드나 클래스를 실제 구현하기 이전에 해당 메서드나 클래스가 있다고 가정한 후 먼저 정의하여 사용한 이후에 실제 구현하는 방식
 */