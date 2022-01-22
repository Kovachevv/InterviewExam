import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Food extends BaseProduct{

    private LocalDate expirationDate;

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Food setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }




    private BigDecimal calculateTotalPrice(BigDecimal price, double quantity){

        BigDecimal value  = price.multiply(BigDecimal.valueOf(quantity));

        return value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {

        return this.getName() + " " + this.getBrand() + '\n' +
                this.getQuantity() + " x $" + this.getPrice() + " = $" + calculateTotalPrice(this.getPrice(),this.getQuantity());
    }
}
