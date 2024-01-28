package org.example;

import org.example.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    Object map(ResultSet rs) throws SQLException;
}
