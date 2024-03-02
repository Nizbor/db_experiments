package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepo implements IRepo<Clients> {
    @Override
    public void Update(Clients client) throws SQLException {
        String str = String.format("UPDATE client SET idClient = %s, name = '%s', idStylists = %s WHERE idClient = %s" ,
                client.getId(),
                client.getName(),
                client.getIdStylists().getId(),
                client.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();

        /// add stylists
    }
    @Override
    public void Insert(Clients client) throws SQLException {
        StylistsRepo stylistsRepo = new StylistsRepo();
        stylistsRepo.Insert(client.getIdStylists());
        String str = String.format("INSERT INTO client (idClient, name, idStylists) VALUES (%s, '%s', %s)",
                client.getId(),
                client.getName(),
                client.getIdStylists().getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void Delete(Clients client) throws SQLException {
        String str = String.format("DELETE  FROM client where idClient = %s" , client.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }

    @Override
    public List<Clients> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT cl.idClient, cl.name, cl.idStylists, st.idStylists, st.S_name FROM client AS cl JOIN Stylists AS st ON cl.IDSTYLISTS = st.idStylists");

        List<Clients> clients = new ArrayList<>();
        while(rs.next()) {
            clients.add(new Clients(rs.getInt("idClient"),
                    rs.getString("name"),
                    new Stylists(rs.getInt("idStylists"), rs.getString("S_name"))));
        }
        this.closeConnection(stmt);

        return clients;
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
