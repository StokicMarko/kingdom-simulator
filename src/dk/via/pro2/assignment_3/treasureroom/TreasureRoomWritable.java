package dk.via.pro2.assignment_3.treasureroom;

import dk.via.pro2.assignment_3.valuables.Valuable;

public interface TreasureRoomWritable extends TreasureRoomReadable {
  void addValuable(Valuable valuable);
  Valuable retrieveValuable();
}