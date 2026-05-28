package dk.via.pro2.assignment_3;

import java.util.HashMap;
import java.util.Map;

public class Valuable
{
  private static Map<String, Valuable> instances = new HashMap<>();
  private String name;
  private int moneyValue;

  private Valuable(String name, int moneyValue){
    this.name = name;
    this.moneyValue = moneyValue;
  }

  public static Valuable getInstance(String name, int moneyValue) {
    if (!instances.containsKey(name)){
      synchronized (Valuable.class) {
        if(!instances.containsKey(name)){
          instances.put(name, new Valuable(name, moneyValue));
        }
      }
    }
    return instances.get(name);
  }
}
