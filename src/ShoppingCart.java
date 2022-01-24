import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import static java.time.temporal.ChronoUnit.DAYS;

public class ShoppingCart {

    private ArrayList<BaseProduct> items = new ArrayList<>();
    private LocalDateTime purchaseDate;


    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public ShoppingCart setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public ArrayList<BaseProduct> getItems() {
        return items;
    }

    public ShoppingCart setItems(ArrayList<BaseProduct> items) {
        this.items = items;
        return this;
    }

    //Adding the product to the cart and checking the class, so a discount is added if it matches the requirements.
    public void addProduct(BaseProduct product){
            if(product.getClass().equals(Food.class) || product.getClass().equals(Beverage.class) ){
                if (product instanceof Food) {
                    Food food =(Food) product;
                    long daysBetween = DAYS.between(food.getExpirationDate(), purchaseDate);
                    if(daysBetween == 0){
                        food.setDiscount(50);
                    }else if(Math.abs(daysBetween) <= 5){
                        food.setDiscount(10);
                    }
                    items.add(food);
                }else{
                    Beverage beverage = (Beverage) product;
                    long daysBetween = DAYS.between(beverage.getExpirationDate(), purchaseDate);
                    if(daysBetween == 0){
                        beverage.setDiscount(50);
                    }else if(Math.abs(daysBetween) <= 5){
                        beverage.setDiscount(10);
                    }
                    items.add(beverage);
                }
            }else if(product.getClass().equals(Clothing.class)){
                String dayOfWeek = getDayOfWeek(purchaseDate);
                Clothing clothing = (Clothing) product;
                if(!dayOfWeek.equals("Saturday") && !dayOfWeek.equals("Sunday")){
                    clothing.setDiscount(10);
                }
                items.add(clothing);

            }else{
                Appliance appliance = (Appliance) product;
                String dayOfWeek = getDayOfWeek(purchaseDate);
                if((dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday")) && appliance.getPrice().compareTo(BigDecimal.valueOf(999)) > 0){
                    appliance.setDiscount(5);
                }
                items.add(appliance);
            }


        }


    public BigDecimal getSubtotal(){
        BigDecimal subtotal = new BigDecimal(0);
        for (BaseProduct item : items) {
            BigDecimal price = item.getSubtotal();
            subtotal = subtotal.add(price);
        }
        return subtotal;
    }

    public BigDecimal getTotalDiscount(){
        BigDecimal totalDiscount = new BigDecimal(0);
        for (BaseProduct item : items) {
            BigDecimal discount = item.getTotalDiscount();
            totalDiscount = totalDiscount.add(discount);
        }
        return totalDiscount;
    }

    private String getDayOfWeek(LocalDateTime purchaseDate) {

        int day = purchaseDate.getDayOfMonth();
        int month = purchaseDate.getMonthValue();
        int year = purchaseDate.getYear();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH,month - 1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.YEAR, year);

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);

    }
}
