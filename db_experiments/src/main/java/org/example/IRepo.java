package org.example;
import java.sql.*;
import java.util.List;

interface IRepo<T> {
    void Insert(T object) throws SQLException;
    void Delete(T object) throws SQLException;
    void Update(T object) throws SQLException;

    List<T> getList() throws SQLException;
    Connection connectToDB() throws SQLException;
    Statement getStatement(Connection conn) throws SQLException;
}
