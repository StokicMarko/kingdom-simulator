package dk.via.pro2.assignment_3;

public interface TreasureRoomWritable extends TreasureRoomReadable {
  void addValuable(Valuable valuable);
  Valuable retrieveValuable();
}