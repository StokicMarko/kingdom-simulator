package dk.via.pro2.assignment_3.actor;

import dk.via.pro2.assignment_3.Log;
import dk.via.pro2.assignment_3.treasureroom.TreasureRoomDoor;
import dk.via.pro2.assignment_3.treasureroom.TreasureRoomReadable;
import dk.via.pro2.assignment_3.valuables.Valuable;

public class Accountant implements Runnable
{
  TreasureRoomDoor treasureRoomDoor;

  public Accountant(TreasureRoomDoor treasureRoomDoor){
      this.treasureRoomDoor = treasureRoomDoor;
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        TreasureRoomReadable treasureRoom = treasureRoomDoor.acquireRead();

        int totalWorth = 0;
        for (Valuable valuable : treasureRoom.lookAtValuables()) {
          totalWorth += valuable.getMoneyValue();
          Thread.sleep(200);
        }
        Log.getInstance().log(Accountant.class.getSimpleName(),
            "Total worth in treasure room: " + totalWorth);

        treasureRoomDoor.releaseRead(treasureRoom);
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
