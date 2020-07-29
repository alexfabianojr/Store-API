package app.services;

import app.module.entities.Seller;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class ReturnSellersByGenre {
    public static List<Seller> find(List<Seller> sellers_input, char genre) {
        List<Seller> sellers_output = new ArrayList<>();
        for (Seller sl : sellers_input) {
            if (sl.getGenre() == genre) {
                sellers_output.add(sl);
            }
        }
        return sellers_output;
    }
}
