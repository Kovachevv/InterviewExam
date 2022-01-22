import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Appliance extends BaseProduct{



    private String model;
    private LocalDate productionDate;
    private double weight;


    public String getModel() {
        return model;
    }

    public Appliance setModel(String model) {
        this.model = model;
        return this;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public Appliance setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public Appliance setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    private BigDecimal calculateTotalPrice(BigDecimal price,double quantity){

        BigDecimal value  = price.multiply(BigDecimal.valueOf(quantity));

        return value.setScale(2,RoundingMode.HALF_UP).stripTrailingZeros();
    }

    @Override
    public String toString() {

        DecimalFormat format = new DecimalFormat("0.##");


        return this.getName() + " " + this.getBrand() + " " + this.getModel() + '\n' +
                format.format(this.getQuantity()) + " x $" + format.format(this.getPrice()) + " = $" + calculateTotalPrice(this.getPrice(),this.getQuantity());
    }

    public BigDecimal getTotalDiscount(int discount, BigDecimal price){

        return BigDecimal.valueOf(discount * 100L).divide(price);
    }


}
