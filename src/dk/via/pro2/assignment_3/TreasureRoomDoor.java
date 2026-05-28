package dk.via.pro2.assignment_3;

public interface TreasureRoomDoor {
  TreasureRoomReadable acquireRead() throws InterruptedException;
  void releaseRead(TreasureRoomReadable proxy);
  TreasureRoomWritable acquireWrite() throws InterruptedException;
  void releaseWrite(TreasureRoomWritable proxy);
}