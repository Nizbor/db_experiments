package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        IRepo<Clients> clientsRepo = new ClientsRepo();
        IRepo<Stylists> stylistsRepo = new StylistsRepo();

        List<Clients> clients = clientsRepo.getList();
        List<Stylists> stylists = stylistsRepo.getList();

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
