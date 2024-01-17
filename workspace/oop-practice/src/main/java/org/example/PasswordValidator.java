package org.example;

/**
 * [ 요구사항 ]
 * 1. 비밀번호는 최소 8자 이상 12자 이하
 * 2. 8자 미만 12자 초과인 경우 illegalArgumentException 예외 발생
 * 3. 경계 조건에 대한 테스크 코드 작성 필요
 * */
public class PasswordValidator {
    public static final String WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE = "비밀번호는 최소 8자 이상 12자 이하여야 한다.";
    public static void validate(String password) {
        int length = password.length();

        if(length < 8 || length > 12) {
            throw new IllegalArgumentException(WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
        }
    }
}
