package dk.via.pro2.assignment_3;

import java.util.ArrayList;
import java.util.Random;

public class King implements Runnable
{
  private TreasureRoomDoor treasureRoomDoor;
  private ArrayList<Valuable> partyValuables = new ArrayList<>();

  public King (TreasureRoomDoor treasureRoomDoor){
    this.treasureRoomDoor = treasureRoomDoor;
  }

  @Override public void run()
  {
    while (true)
    {
      int targetValue = 50 + new Random().nextInt(151);
      int currentValue = 0;
      boolean isReady = false;

      try
      {
        TreasureRoomWritable treasureRoom = treasureRoomDoor.acquireWrite();

        while (!isReady)
        {
          Valuable picked = treasureRoom.retrieveValuable();
          if (picked != null){
            partyValuables.add(picked);
            currentValue += picked.getMoneyValue();

            if (currentValue >= targetValue)
              isReady = true;
          }
          else {
            for (Valuable valuable : partyValuables){
              treasureRoom.addValuable(valuable);
            }
            isReady = true;
          }

          treasureRoomDoor.releaseWrite();
          if (currentValue >= targetValue)
          {
            partyValuables.clear();
            Log.getInstance().log(Class.class.getSimpleName(),
                "PARTY TIMEE!!!");
          }
          else
          {
            Log.getInstance().log(Class.class.getSimpleName(),
                "The fun is over, not enough valuables");
          }
        }

        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
