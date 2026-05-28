package dk.via.pro2.assignment_3;

import java.util.ArrayList;
import java.util.Random;

public class ValuablesTransporter implements Runnable {
  private ArrayList<Valuable> carriage = new ArrayList<>();
  private Deposit deposit;
  private TreasureRoomDoor treasureRoomDoor;

  public ValuablesTransporter(Deposit deposit, TreasureRoomDoor treasureRoomDoor) {
    this.deposit = deposit;
    this.treasureRoomDoor = treasureRoomDoor;
  }

  @Override
  public void run() {
    while (true) {
      int targetValue = 50 + new Random().nextInt(151);
      int currentValue = 0;

      while (currentValue < targetValue) {
        try {
          Valuable picked = deposit.removeValuable();
          carriage.add(picked);
          currentValue += picked.getMoneyValue();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

      Log.getInstance().log(ValuablesTransporter.class.getSimpleName(),
          "Delivering " + carriage.size() + " valuables to the treasure room");

      try {
        putValuablesInTreasureRoom();
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void putValuablesInTreasureRoom() throws InterruptedException {
    TreasureRoomWritable treasureRoom = treasureRoomDoor.acquireWrite();
    for (int i = 0; i < carriage.size(); i++) {
      treasureRoom.addValuable(carriage.get(i));
    }
    Thread.sleep(2000);
    treasureRoomDoor.releaseWrite();
    carriage.clear();
  }
}