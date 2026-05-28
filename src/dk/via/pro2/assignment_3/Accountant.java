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
        int count = 0;

        for (int i = 0; i < treasureRoom.lookAtValuables().size(); i++)
        {
          count++;
          Thread.sleep(3000);
        }
        Log.getInstance().log(Accountant.class.getSimpleName(),
            "has count a total of " + count + " items");

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
