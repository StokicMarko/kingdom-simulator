package dk.via.pro2.assignment_3.treasureroom;

import dk.via.pro2.assignment_3.Log;
import dk.via.pro2.assignment_3.valuables.Valuable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreasureRoom implements TreasureRoomWritable {
  private List<Valuable> valuables = new ArrayList<>();

  @Override
  public void addValuable(Valuable valuable) {
    valuables.add(valuable);
    Log.getInstance().log("TreasureRoom", "Valuable added: " + valuable.getName());
  }

  @Override
  public Valuable retrieveValuable() {
    if (valuables.isEmpty()) return null;
    return valuables.remove(0);
  }

  @Override
  public List<Valuable> lookAtValuables() {
    return Collections.unmodifiableList(valuables);
  }
}