package dbe.hw.homework.sixthHomework;

/*
- id - big int ---> primary key
        - quantity - int
        - unit_price - decimal
        - total_price - decimal
        - product_code - int
        - shopping_cart_id ---> foreign key from shopping_cart
 */
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table (name = "shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "shopping_cart_item_id")
    int id;

    @NonNull
    int quantity;

    @Column(name = "unit_price")
    @NonNull
    double unitPrice;

    @Column(name = "total_price")
    @NonNull
    double totalPrice;

    @Column(name = "product_code")
    @NonNull
    double productCode;

    @Column(name = "product_name")
    @NonNull
    String productName;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    ShoppingCart shoppingCart;

}
