package dbe.hw.homework.sixthHomework;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction

        insertCart(session, new ShoppingCart("Gigi","Timisoara", "1", "1",
                "finished", 20.02));

        insertItem(session, new ShoppingCartItem(21, 20,2,40,
                        "orez"), 1);
        insertItem(session, new ShoppingCartItem(22,2,4,8,
                    "apa"),1);

        getItemsPerCity(session, "Timisoara");
    }

    private static void insertItem(Session session, ShoppingCartItem shoppingCartItem, int id) {
        Transaction transaction = session.beginTransaction();

        ShoppingCart shoppingCart = session.get(ShoppingCart.class, id);
        shoppingCartItem.setShoppingCart(shoppingCart);
        session.save(shoppingCartItem);

        transaction.commit();
    }

    private static void insertCart(Session session, ShoppingCart shoppingCart) {
        Transaction transaction = session.beginTransaction();
        session.save(shoppingCart);
        transaction.commit();
    }

    private static List<ShoppingCartItem> getItemsPerCity(Session session, String city){
        Transaction transaction = session.beginTransaction();
        String getMoreItems = "SELECT sci FROM ShoppingCartItem as sci INNER JOIN sci.shoppingCart as sc " +
                "WHERE sc.deliveryCity = '" + city +"'";
        Query<ShoppingCartItem> query = session.createQuery(getMoreItems, ShoppingCartItem.class);
        return query.list();
    }
}
