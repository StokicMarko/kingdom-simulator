package dk.via.pro2.assignment_3;

import java.util.ArrayList;
import java.util.Random;

public class ValuablesTransporter implements Runnable
{

  private ArrayList<Valuable> carriage;
  private Deposit deposit;

  public ValuablesTransporter(Deposit deposit){
    this.deposit = deposit;
    carriage = new ArrayList<>();
  }

  @Override public void run()
  {
    while (true)
    {
      Random random = new Random();
      int targetValue = 50 + (int)(Math.random() * (200 - 50 + 1));

      int currentValue = 0;

      while (targetValue >= currentValue)
      {
        try
        {
          Valuable pickedValuable = deposit.removeValuable();
          carriage.add(pickedValuable);
          currentValue += pickedValuable.getMoneyValue();
        }
        catch (InterruptedException e)
        {
          throw new RuntimeException(e);
        }
      }

      Log.getInstance().log(ValuablesTransporter.class.getSimpleName(),
          "Has deliver " + carriage.size() + " valuables to the treasure room");
      carriage.clear(); //TODO put Valuables in the treasure room

      try
      {
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }

    }
  }
}
