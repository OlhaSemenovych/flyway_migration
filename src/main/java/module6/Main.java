package module6;

import module6.services.ClientService;

public class Main {

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        clientService.create("Test User");
        clientService.getById(1);
        clientService.setName(7, "Test User 1");
        clientService.deleteById(7);
        clientService.listAll();
    }

}
