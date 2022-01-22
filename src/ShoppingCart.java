import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void addProduct(BaseProduct product){
        items.add(product);
    }

    public void addDiscount(){

        for (int i = 0; i < items.size(); i++) {
            BaseProduct currentItem = items.get(i);
            if(currentItem.getClass().equals(Food.class) ||currentItem.getClass().equals(Beverage.class) ){
                if (currentItem instanceof Food) {
                    Food food =(Food) currentItem;
                    long daysBetween = DAYS.between(food.getExpirationDate(), purchaseDate);
                    if(daysBetween == 0){
                        food.setDiscount(50);
                    }else if(Math.abs(daysBetween) <= 5){
                        food.setDiscount(10);
                    }
                    items.set(i,food);
                }else{
                    Beverage beverage = (Beverage) currentItem;
                    long daysBetween = DAYS.between(beverage.getExpirationDate(), purchaseDate);
                    if(daysBetween == 0){
                        beverage.setDiscount(50);
                    }else if(Math.abs(daysBetween) <= 5){
                        beverage.setDiscount(10);
                    }
                    items.set(i,beverage);
                }
            }else if(currentItem.getClass().equals(Clothing.class)){
                String dayOfWeek = getDayOfWeek(purchaseDate);
                Clothing clothing = (Clothing) currentItem;
                if(!dayOfWeek.equals("Saturday") && !dayOfWeek.equals("Sunday")){
                    clothing.setDiscount(10);
                    items.set(i,clothing);
                }

            }else{
                Appliance appliance = (Appliance) currentItem;
                String dayOfWeek = getDayOfWeek(purchaseDate);
                if((dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday")) && appliance.getPrice().compareTo(BigDecimal.valueOf(999)) > 0){
                    appliance.setDiscount(5);
                    items.set(i,appliance);
                }
            }

        }


    }

    public BigDecimal getSubtotal(){
        BigDecimal subtotal = new BigDecimal(0);
        for (int i = 0; i < items.size(); i++) {
            BigDecimal price = items.get(i).getSubtotal();

            subtotal = subtotal.add(price);
        }
        return subtotal;
    }

    public BigDecimal getTotalDiscount(){
        BigDecimal totalDiscount = new BigDecimal(0);
        for (int i = 0; i < items.size(); i++) {
            BigDecimal discount = items.get(i).getTotalDiscount();
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

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.US);

    }
}
