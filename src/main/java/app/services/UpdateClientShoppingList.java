package app.services;

import app.module.entities.Client;
import app.repository.ClientRepository;

import java.util.ArrayList;

public class UpdateClientShoppingList {

    private static ClientRepository clientRepository;

    public static void byId(Long newSaleId, Long idClient) {
        try {
            if (clientRepository.findById(idClient).isPresent()) {
                Client client = clientRepository
                        .findById(idClient)
                        .get();
                ArrayList<Long> longs = clientRepository
                        .findById(idClient)
                        .get()
                        .getShoppingList();
                longs.add(newSaleId);
                client.setShoppingList(longs);
                clientRepository.save(client);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Client not found");
            System.out.println(e.getMessage());
        }
    }
}
