package dk.via.pro2.assignment_3.actor;

import dk.via.pro2.assignment_3.Log;
import dk.via.pro2.assignment_3.treasureroom.TreasureRoomDoor;
import dk.via.pro2.assignment_3.treasureroom.TreasureRoomWritable;
import dk.via.pro2.assignment_3.valuables.Valuable;

import java.util.ArrayList;
import java.util.Random;

public class King implements Runnable {
  private final TreasureRoomDoor treasureRoomDoor;
  private final ArrayList<Valuable> partyValuables = new ArrayList<>();
  private final Random random = new Random();

  public King(TreasureRoomDoor treasureRoomDoor) {
    this.treasureRoomDoor = treasureRoomDoor;
  }

  @Override
  public void run() {
    while (true) {
      int targetValue = 50 + random.nextInt(101);
      int currentValue = 0;
      partyValuables.clear();
      boolean partyHappened = false;

      try {
        TreasureRoomWritable treasureRoom = treasureRoomDoor.acquireWrite();
        try {
          while (currentValue < targetValue) {
            Valuable picked = treasureRoom.retrieveValuable();
            if (picked == null) {
              for (Valuable v : partyValuables) treasureRoom.addValuable(v);
              partyValuables.clear();
              break;
            }
            partyValuables.add(picked);
            currentValue += picked.getMoneyValue();
            Thread.sleep(50);
          }
          if (currentValue >= targetValue) {
            partyValuables.clear();
            partyHappened = true;
          }
        } finally {
          treasureRoomDoor.releaseWrite(treasureRoom);
        }

        Log.getInstance().log(King.class.getSimpleName(),
            partyHappened ? "PARTY TIME!!!" : "Not enough valuables, party cancelled");
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }
  }
}