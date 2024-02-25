package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.err.println("Не удалось загрузить драйвер: " + e);
            return;
        }

        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\lab.db");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM client AS cl JOIN Stylists AS st ON cl.IDSTYLISTS = st.idStylists");

        List<Clients> clients = new ArrayList<>();
        List<Stylists> stylists = new ArrayList<>();

        while(rs.next()) {
            Stylists tmpStylist = new Stylists(rs.getInt("idStylists"), rs.getString("S_name"));
            clients.add(new Clients(rs.getInt("idClient"), rs.getString("name"), tmpStylist));
            stylists.add(tmpStylist);
        }
        stmt.close();

        for (Clients client : clients) {
            DBWork.printClient(client); // Print information about clients
        }

        for (Stylists stylist : stylists) {
            DBWork.printStylist(stylist); // Print information about stylists
        }

        for (Clients client : clients) {
            DBWork.printClientStylist(client); // Print information about client-stylist
        }
    }
}
