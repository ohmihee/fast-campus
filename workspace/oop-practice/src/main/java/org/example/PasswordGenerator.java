package org.example;

/**
 * FunctionalInterface를 구현한 구현체인 경우 () -> "" 와 같은 형태로 사용이 가능하다.
 * */
@FunctionalInterface
public interface PasswordGenerator {
    String generatePassword();
}
