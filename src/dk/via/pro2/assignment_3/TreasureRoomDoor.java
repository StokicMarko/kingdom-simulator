package dk.via.pro2.assignment_3;

public interface TreasureRoomDoor {
  TreasureRoomReadable acquireRead() throws InterruptedException;
  void releaseRead();
  TreasureRoomWritable acquireWrite() throws InterruptedException;
  void releaseWrite();
}