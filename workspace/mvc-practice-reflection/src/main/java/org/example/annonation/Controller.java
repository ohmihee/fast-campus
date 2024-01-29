package org.example.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
// Class, interface (including annotation type), or enum declaration 등에 사용 가능한 어노테이션
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
