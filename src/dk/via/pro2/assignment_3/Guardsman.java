package dk.via.pro2.assignment_3;

public class Guardsman implements TreasureRoomDoor {
  private TreasureRoom treasureRoom;
  private TreasureRoomReadProxy currentReadProxy;
  private TreasureRoomWriteProxy currentWriteProxy;
  private int readers = 0;
  private boolean writing = false;

  public Guardsman(TreasureRoom treasureRoom) {
    this.treasureRoom = treasureRoom;
  }

  @Override
  public synchronized TreasureRoomReadable acquireRead() throws InterruptedException {
    while (writing) {
      Log.getInstance().log("Guardsman", "Reader waiting - writer inside");
      wait();
    }
    readers++;
    currentReadProxy = new TreasureRoomReadProxy(treasureRoom);
    return new TreasureRoomReadProxy(treasureRoom);
  }

  @Override
  public synchronized void releaseRead() {
    if (currentReadProxy != null) {
      currentReadProxy.release();
      currentReadProxy = null;
    }
    readers--;
    notifyAll();
  }

  @Override
  public synchronized TreasureRoomWritable acquireWrite() throws InterruptedException {
    while (writing || readers > 0) {
      Log.getInstance().log("Guardsman", "Writer waiting - room occupied");
      wait();
    }
    writing = true;
    currentWriteProxy = new TreasureRoomWriteProxy(treasureRoom);
    return new TreasureRoomWriteProxy(treasureRoom);
  }

  @Override
  public synchronized void releaseWrite() {
    if (currentWriteProxy != null) {
      currentWriteProxy.release();
      currentWriteProxy = null;
    }
    writing = false;
    notifyAll();
  }
}