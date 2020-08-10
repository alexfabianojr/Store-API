package app.services;

import app.controller.ShoppingCartController;
import app.module.entities.Seller;
import app.module.entities.ShoppingCart;
import app.module.pojo.Report;
import app.module.pojo.Sale;
import app.repository.ShoppingCartRepository;
import app.services.subservices.SaleListBuilder;
import app.services.subservices.ShoppingCartListById;
import app.services.subservices.TotalValueOfSaleList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@Component
@Service
public class SellerReport {

    private ShoppingCartController cartController;

    public Report bySeller(Seller seller) {
        List<ShoppingCart> carts = new ArrayList<>();
        List<Sale> sales;
        Double total = 0.0;
        int totalCarts;

        try {
            carts = ShoppingCartListById.run(cartController.findAll(), seller.getId());
            sales = SaleListBuilder.run(carts);
            total = TotalValueOfSaleList.run(sales);
        } catch (NullPointerException exception) {
            System.out.println(Arrays.toString(exception.getStackTrace()));
        }
        totalCarts = (carts == null) ? 0 : carts.size();

        return new Report(seller.getId(),
                seller.getPerson().getName(),
                seller.getContact().getEmail(),
                totalCarts,
                total);
    }
}
