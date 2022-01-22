import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class Cashier {

    public static void main(String[] args) {

        ShoppingCart shoppingCart = initShoppingCart();
        printReceipt(shoppingCart);


    }

    //printing the receipt
    private static void printReceipt(ShoppingCart shoppingCart){

        shoppingCart.addDiscount();
        System.out.println("Date: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(shoppingCart.getPurchaseDate()));
        System.out.println();
        System.out.println("---Products---");
        System.out.println();


        for (int i = 0; i < shoppingCart.getItems().size(); i++) {
            System.out.println(shoppingCart.getItems().get(i));
            if(shoppingCart.getItems().get(i).getDiscount() != 0){
                System.out.println("#discount " + shoppingCart.getItems().get(i).getDiscount() + "% -$" + shoppingCart.getItems().get(i).getTotalDiscount());
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("SUBTOTAL: $" + shoppingCart.getSubtotal());
        System.out.println("DISCOUNT: -$" + shoppingCart.getTotalDiscount());
        System.out.println("TOTAL: $" + shoppingCart.getSubtotal().subtract(shoppingCart.getTotalDiscount()));
    }




    //Initializing the shopping cart with products given in the example and saving them.
    private static ShoppingCart initShoppingCart() {
        Food apple = new Food();
        apple.setName("apple").setBrand("BrandA").setPrice(BigDecimal.valueOf(1.50).setScale(2));
        apple.setExpirationDate(LocalDate.parse("2021-06-14")).setQuantity(2.45);

        Beverage milk = new Beverage();
        milk.setName("milk").setBrand("BrandM").setPrice(BigDecimal.valueOf(0.99).setScale(2));
        milk.setExpirationDate(LocalDate.parse("2022-02-02")).setQuantity(3.0);

        Clothing shirt = new Clothing();
        shirt.setName("T-shirt").setBrand("BrandT").setPrice(BigDecimal.valueOf(15.99).setScale(2));
        shirt.setSize(ClothingSizeEnum.M).setColor("violet").setQuantity(2.0);

        Appliance laptop = new Appliance();
        laptop.setName("laptop").setBrand("BrandL").setPrice(BigDecimal.valueOf(2345).setScale(2));
        laptop.setModel("ModelL").setProductionDate(LocalDate.parse("2021-03-03")).setWeight(1.125).setQuantity(1.0);

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setPurchaseDate(LocalDateTime.of(2021, 6,14,12,34,56));
        shoppingCart.addProduct(apple);
        shoppingCart.addProduct(milk);
        shoppingCart.addProduct(shirt);
        shoppingCart.addProduct(laptop);

        return shoppingCart;
    }
}
