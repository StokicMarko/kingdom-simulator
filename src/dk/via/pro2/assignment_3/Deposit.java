package dk.via.pro2.assignment_3;

import utility.collection.ArrayList;
import utility.collection.ListADT;

public class Deposit
{
  ListADT<Valuable> deposit = new ArrayList<>();
  private final int CAPACITY = 100;

  public synchronized void addValuable(Valuable valuable) throws InterruptedException {
    while (deposit.size() >= CAPACITY) {
      Log.getInstance().log(Deposit.class.getName(), "Full capacity - Miner waiting");
      wait();
    }
    deposit.add(valuable);
    notifyAll();
  }

  public synchronized Valuable removeValuable() throws InterruptedException {
    while (deposit.size() <= CAPACITY) {
      Log.getInstance().log(Deposit.class.getName(), "Empty deposit - Transporter waiting");
      wait();
    }
    Valuable valuable = deposit.remove(0);
    notifyAll();
    return valuable;
  }
}
