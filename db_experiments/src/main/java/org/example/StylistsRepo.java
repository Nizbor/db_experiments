package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StylistsRepo implements IRepo<Stylists> {
    @Override
    public void Update(Stylists stylists) throws SQLException {
        String str = String.format("UPDATE stylists SET idStylists = %s, S_name = '%s' WHERE idClient = %s" ,
                stylists.getId(),
                stylists.getName(),
                stylists.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();

    }
    @Override
    public void Insert(Stylists stylists) throws SQLException {
        String str = String.format("INSERT INTO stylists (idStylists, S_name) VALUES (%s, '%s')",
                stylists.getId(),
                stylists.getName());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void Delete(Stylists stylists) throws SQLException {
        String str = String.format("DELETE  FROM stylists where idStylists = %s" , stylists.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }

    @Override
    public List<Stylists> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT st.idStylists, st.S_name FROM Stylists AS st");

        List<Stylists> stylists = new ArrayList<>();
        while(rs.next()) {
            Stylists tmpStylist = new Stylists(rs.getInt("idStylists"), rs.getString("S_name"));
            stylists.add(tmpStylist);
        }
        this.closeConnection(stmt);

        return stylists;
    }
    @Override
    public Connection connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\lab.db");
        if (conn==null) {
            System.out.println("Error with connection with DataBase!");
            System.exit(0);
        }
        return conn;
    }
    @Override
    public Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }
    public void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
    }
}
