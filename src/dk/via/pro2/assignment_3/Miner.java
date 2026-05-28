package dk.via.pro2.assignment_3;

import java.util.Random;

public class Miner implements Runnable
{
  String name;
  Mine mine;
  Deposit deposit;

  public Miner(String name, Deposit deposit){
    this.deposit = deposit;
    this.name = name;
    mine = new Mine();
  }

  @Override public void run()
  {
    while (true){
      try
      {
        mining();
        Thread.sleep(2000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  private void mining() throws InterruptedException
  {
    Random random = new Random();
    if(random.nextBoolean()) return;

    Valuable randomValuable = mine.getValuable();
    deposit.addValuable(randomValuable);
    Log.getInstance().log(name + " The Miner", "Added Valuable to the Deposit");
  }
}
