package dbe.hw.homework.sixthHomework;

/*
    - id - big int ----> primary key
            - client_name - varchar 255
            - delivery_city - varchar 255
            - delivery_street - varchar 255
            - delivery number - varchar 10
            - order_status - varchar 255
            - order_total - decimal

 */

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO lets the persistence provider (in this case Hibernate) select the way
            //it generates the next primary key. Hibernate uses GenerationType.SEQUENCE
    //@Column(name = "shopping_cart_id")
    int id;

    @Column(name = "client_name")
    @NonNull
    String clientName;

    @Column(name = "delivery_city")
    @NonNull
    String deliveryCity;

    @Column(name = "delivery_street")
    @NonNull
    String deliveryStreet;

    @Column(name = "delivery_number")
    @NonNull
    String deliveryNumber;

    @Column(name = "order_status")
    @NonNull
    String orderStatus;

    @Column(name = "order_total")
    @NonNull
    double orderTotal;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "shoppingCart")
    List<ShoppingCartItem> items;

}
