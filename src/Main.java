import dk.via.pro2.assignment_3.*;
import dk.via.pro2.assignment_3.actor.Accountant;
import dk.via.pro2.assignment_3.actor.King;
import dk.via.pro2.assignment_3.actor.Miner;
import dk.via.pro2.assignment_3.actor.ValuablesTransporter;
import dk.via.pro2.assignment_3.treasureroom.Guardsman;
import dk.via.pro2.assignment_3.treasureroom.TreasureRoom;
import dk.via.pro2.assignment_3.treasureroom.TreasureRoomDoor;

void main()
{
  Deposit deposit = new Deposit();
  Miner miner1 = new Miner("John", deposit);
  Miner miner2 = new Miner("Mikel", deposit);

  TreasureRoom treasureRoom = new TreasureRoom();
  TreasureRoomDoor guard = new Guardsman(treasureRoom);

  ValuablesTransporter valuablesTransporter1 = new ValuablesTransporter(deposit, guard);
  ValuablesTransporter valuablesTransporter2 = new ValuablesTransporter(deposit, guard);

  Accountant accountant1 = new Accountant(guard);
  Accountant accountant2 = new Accountant(guard);

  King king = new King(guard);

  new Thread(miner1).start();
  new Thread(miner2).start();
  new Thread(valuablesTransporter1).start();
  new Thread(valuablesTransporter2).start();
  new Thread(accountant1).start();
  new Thread(accountant2).start();
  new Thread(king).start();
}
