package app.utils;

import app.module.entities.SalesList;
import app.module.pojo.Sale;
import app.repository.SalesListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class DummyDataSalesList {

    @Autowired
    SalesListRepository salesListRepository;

    @PostConstruct
    public void salesListPopulation() {

        ArrayList<SalesList> salesListArrayList = new ArrayList<>();
        ArrayList<Sale> listOfSingleSales = new ArrayList<>();

        SalesList salesList1 = new SalesList();
        SalesList salesList2 = new SalesList();
        SalesList salesList3 = new SalesList();
        SalesList salesList4 = new SalesList();

        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        Sale sale3 = new Sale();
        Sale sale4 = new Sale();

        sale1.setProductId(1);
        sale1.setProductQuantity(10);

        sale2.setProductId(2);
        sale2.setProductQuantity(30);

        sale3.setProductId(3);
        sale3.setProductQuantity(20);

        sale4.setProductId(4);
        sale4.setProductQuantity(80);

        listOfSingleSales.add(sale1);
        listOfSingleSales.add(sale2);
        listOfSingleSales.add(sale3);
        listOfSingleSales.add(sale4);

        salesList1.setSalespeopleId(1);
        salesList1.setSales(listOfSingleSales);

        salesList2.setSalespeopleId(2);
        salesList2.setSales(listOfSingleSales);

        salesList3.setSalespeopleId(3);
        salesList3.setSales(listOfSingleSales);

        salesList4.setSalespeopleId(4);
        salesList4.setSales(listOfSingleSales);

        salesListArrayList.add(salesList1);
        salesListArrayList.add(salesList2);
        salesListArrayList.add(salesList3);
        salesListArrayList.add(salesList4);

        for (SalesList salesList : salesListArrayList) {
            SalesList salesListSaved = salesListRepository.save(salesList);
            System.out.println(salesListSaved.getId());
        }
    }
}