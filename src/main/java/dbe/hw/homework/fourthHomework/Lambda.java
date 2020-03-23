package dbe.hw.homework.fourthHomework;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1 gasiti toate tranzactiile in anul 2011 si sortatile dupa valoare
        System.out.println(transactions
                .stream()
                .filter(a-> a.getYear()>2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList()));
        //2 care sunt toate orasele (unice)?
        System.out.println(transactions
                .stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList()));
        //3 gasiti toti traders din Cambridge si sortatii dupa nume
        System.out.println(transactions
                .stream()
                .map(Transaction::getTrader)
                .filter (a -> a.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList()));
        //4 Un string cu toate numele traderilor ordonati alfabetic
        System.out.println(transactions
                .stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList()));
        //5 Sunt traderi din Milan?
        System.out.println(transactions
                .stream()
                .map(Transaction::getTrader)
                .anyMatch(t-> t.getCity().equals("Milan")));
        //6 Printati valoarea tuturor tranzactiilor traderilor care traiesc in Cambridge.
        System.out.println(transactions
                .stream()
                .filter(t-> t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum());
        //7 Care e tranzactia cu cea mai mare valoare?
        System.out.println(transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .max());
        //8 Dar cea mai mică
        System.out.println(transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .min());
    }
}
