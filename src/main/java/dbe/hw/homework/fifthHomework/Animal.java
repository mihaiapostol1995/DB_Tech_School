package dbe.hw.homework.fifthHomework;

@FunctionalInterface
public interface Animal {

    String eat(String firstMeal, String secondMeal, String thirdMeal);
    //int calories(); multiple non-overriding abstract methods found
}
