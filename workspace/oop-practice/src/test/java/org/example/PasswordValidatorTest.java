package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PasswordValidatorTest {
    @DisplayName("비밀번호가 최소 8자 이상, 12 이하면 예외가 발생하지 않는다.")
    @Test
    void validatePasswordTest() {
        assertThatCode( () -> PasswordValidator.validate("serverwizard"))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"aabbcce", "aabbccddeeffg"}) // 경계값에 대한 검사, 7자와 13자
    void validatePasswordTest2(String password) {
        assertThatCode( () -> PasswordValidator.validate("aabb"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자 이상 12자 이하여야 한다.");
    }
    /**
     * parameterizedTest 링크
     * :https://reflectoring.io/tutorial-junit5-parameterized-tests/
     * */




}
