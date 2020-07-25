package app.services;

import app.module.entities.Seller;
import app.repository.SellersRepository;

import java.util.ArrayList;

public class UpdateSellerSalesIdList {

    private static SellersRepository sellersRepository;

    public static void byId(Long newSaleId, Long idSeller) {
        System.out.println("---> " + idSeller);
        try {
            if (sellersRepository.findById(idSeller).isPresent()) {
                Seller seller = sellersRepository
                        .findById(idSeller)
                        .get();
                ArrayList<Long> longs = sellersRepository
                        .findById(idSeller)
                        .get()
                        .getSalesIdList();
                longs.add(newSaleId);
                seller.setSalesIdList(longs);
                sellersRepository.save(seller);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Seller not found");
            System.out.println(e.getMessage());
        }
    }
}
