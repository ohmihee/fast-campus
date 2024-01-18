package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {

    @DisplayName("패스워드를 초기화한다.")
    @Test
    void passwordTest() {
        // given
        User user = new User();

        // when
        user.initPassword(new CorrectFixedPasswordGenerator());
        /**
         * user.initPassword( () -> "abcdefg");
         * FunctionalInterface를 구현한 구현체인 경우 () -> "" 와 같은 형태로 사용이 가능하다.
         * */

        // then
        assertThat(user.getPassword()).isNotNull();

    }

    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화가 되지 않는다.")
    @Test
    void passwordTest2() {
        // given
        User user = new User();

        // when
        user.initPassword(new WrongFixedPasswordGenerator());

        // then
        assertThat(user.getPassword()).isNull();
    }
}