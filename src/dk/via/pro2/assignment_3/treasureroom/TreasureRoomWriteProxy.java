package dk.via.pro2.assignment_3.treasureroom;

import dk.via.pro2.assignment_3.valuables.Valuable;

import java.util.List;

public class TreasureRoomWriteProxy implements TreasureRoomWritable {
  private TreasureRoom treasureRoom;
  private boolean released = false;

  public TreasureRoomWriteProxy(TreasureRoom treasureRoom) {
    this.treasureRoom = treasureRoom;
  }

  public void release() {
    released = true;
  }

  @Override
  public void addValuable(Valuable valuable) {
    if (released) throw new IllegalStateException("Write access already released");
    treasureRoom.addValuable(valuable);
  }

  @Override
  public Valuable retrieveValuable() {
    if (released) throw new IllegalStateException("Write access already released");
    return treasureRoom.retrieveValuable();
  }

  @Override
  public List<Valuable> lookAtValuables() {
    if (released) throw new IllegalStateException("Write access already released");
    return treasureRoom.lookAtValuables();
  }
}