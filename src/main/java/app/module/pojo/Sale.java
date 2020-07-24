package app.module.pojo;

import java.io.Serializable;

public class Sale implements Serializable {
    private int productId;
    private int productQuantity;

    public Sale() {
    }

    public Sale(int productId, int productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "productId=" + productId +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
