import java.math.BigDecimal;
import java.math.RoundingMode;

//Since all products have a name, brand and a price, it's more efficient to add a base product class,
//that all products will inherit.

public class BaseProduct {

    private String name;
    private String brand;
    private BigDecimal price;
    private int discount;
    private Double quantity;

    public String getName() {
        return name;
    }

    public BaseProduct setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public BaseProduct setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BaseProduct setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getDiscount() {
        return discount;
    }

    public BaseProduct setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public BaseProduct setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getSubtotal(){
        return price.multiply(BigDecimal.valueOf(quantity)).setScale(2,RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public BigDecimal getTotalDiscount(){
        BigDecimal subTotal = getSubtotal();
        subTotal = subTotal.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100));

        return subTotal.setScale(2,RoundingMode.HALF_UP);
    }

}
