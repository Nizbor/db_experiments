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
        this.executeRequest(str);
    }
    @Override
    public Integer Insert(Stylists stylist) throws SQLException {
        String str = String.format("INSERT INTO stylists (name, isDeleted) VALUES ('%s', %s)",
                stylist.getName(),
                stylist.getIsDeleted());
        this.executeRequest(str);
        try (ResultSet rs = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(idStylists) FROM Stylists")) {
            while (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        }
    }
    @Override
    public void Delete(Stylists stylists) throws SQLException {
        String str = String.format("UPDATE Stylists SET isDeleted = true WHERE id = %s" , stylists.getId());
        this.executeRequest(str);
    }
    @Override
    public void executeRequest(String request) throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(request);
        stmt.close();
    }

    @Override
    public List<Stylists> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT st.idStylists, st.S_name, st.s_isDeleted  FROM stylists AS st");

        List<Stylists> stylists = new ArrayList<>();
        while(rs.next()) {
            Stylists tmpStylist = new Stylists(rs.getInt("idStylists"),
                    rs.getString("S_name"), rs.getBoolean("s_isDeleted"));
            stylists.add(tmpStylist);
        }
        this.closeConnection(stmt);

        return stylists;
    }
    @Override
    public Connection connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\lab2.db");
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
