package dk.via.pro2.assignment_3;

import java.util.Random;

public class Mine
{
  private Valuable[] typeOfValuable = {};

  public Mine(){
    typeOfValuable = new Valuable[] {
        Valuable.getInstance("Diamond", 50),
        Valuable.getInstance("GoldNugget", 30),
        Valuable.getInstance("Jewel", 30),
        Valuable.getInstance("Ruby", 10),
        Valuable.getInstance("Wooden coin", 1)
    };
  }

  public Valuable getValuable(){
    int randomValuable = new Random().nextInt(typeOfValuable.length);
    return typeOfValuable[randomValuable];
  }
}
