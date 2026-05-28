package dk.via.pro2.assignment_3;

import java.util.Random;

public class Mine
{
  private Valuable[] typeOfValuable = {};

  public Mine(){
    typeOfValuable = new Valuable[] {
        Valuable.getInstance("Diamond", 500),
        Valuable.getInstance("GoldNugget", 300),
        Valuable.getInstance("Jewel", 300),
        Valuable.getInstance("Ruby", 100),
        Valuable.getInstance("Wooden coin", 5)
    };
  }

  public Valuable getValuable(){
    int randomValuable = new Random().nextInt(typeOfValuable.length);
    return typeOfValuable[randomValuable];
  }
}
