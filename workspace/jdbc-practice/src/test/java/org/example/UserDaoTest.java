package org.example;

import org.example.ConnectionManager;
import org.example.user.User;
import org.example.user.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    /**
     * 테스트 코드 수행 전 실행되는 메서드로, 즉 테스트 코드 수행을 위해 사전에 필요한 작업을 구현하는 메서드이다.
     * */
    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        // db_schema.sql 파일을 읽어와서 스크립트에 추가해줌
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.create2(new User("mihee", "password", "name", "email"));

        User user = userDao.findByUserId2("mihee");

        assertThat(user).isEqualTo(new User("mihee", "password", "name", "email"));

    }
}
