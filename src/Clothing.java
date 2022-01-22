import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Clothing extends BaseProduct{


    private ClothingSizeEnum size;
    private String color;
    private int quantity;

    public ClothingSizeEnum getSize() {
        return size;
}

    public Clothing setSize(ClothingSizeEnum size) {
        this.size = size;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Clothing setColor(String color) {
        this.color = color;
        return this;
    }

    private BigDecimal calculateTotalPrice(BigDecimal price, double quantity){

        BigDecimal value  = price.multiply(BigDecimal.valueOf(quantity));

        return value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.##");

        return this.getName() + " " + this.getBrand() + " " + this.getSize().name() + " " + this.getColor() + '\n' +
                format.format(this.getQuantity()) + " x $" + format.format(this.getPrice()) + " = $" + calculateTotalPrice(this.getPrice(),this.getQuantity());
    }
}
