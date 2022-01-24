import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Beverage extends BaseProduct{

    private LocalDate expirationDate;

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Beverage setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    private BigDecimal calculateTotalPrice(BigDecimal price, double quantity){

        BigDecimal totalPrice  = price.multiply(BigDecimal.valueOf(quantity));

        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {

        DecimalFormat format = new DecimalFormat("0.##");

        return this.getName() + " " + this.getBrand() + '\n' +
                format.format(this.getQuantity()) + " x $" + format.format(this.getPrice()) + " = $" + calculateTotalPrice(this.getPrice(),this.getQuantity());
    }
}
