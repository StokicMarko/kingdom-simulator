package dk.via.pro2.assignment_3;

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

        treasureRoomDoor.releaseRead();
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
