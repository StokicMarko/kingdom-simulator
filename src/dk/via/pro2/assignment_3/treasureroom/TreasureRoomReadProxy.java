package dk.via.pro2.assignment_3.treasureroom;

import dk.via.pro2.assignment_3.valuables.Valuable;

import java.util.List;

public class TreasureRoomReadProxy implements TreasureRoomReadable {
  private TreasureRoom treasureRoom;
  private boolean released = false;

  public TreasureRoomReadProxy(TreasureRoom treasureRoom) {
    this.treasureRoom = treasureRoom;
  }

  public void release() {
    released = true;
  }

  @Override
  public List<Valuable> lookAtValuables() {
    if (released) throw new IllegalStateException("Read access already released");
    return treasureRoom.lookAtValuables();
  }
}