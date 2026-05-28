import dk.via.pro2.assignment_3.Deposit;
import dk.via.pro2.assignment_3.Miner;
import dk.via.pro2.assignment_3.ValuablesTransporter;

void main()
{
  Deposit deposit = new Deposit();
  Miner miner1 = new Miner("John", deposit);
  Miner miner2 = new Miner("Mikel", deposit);

  ValuablesTransporter valuablesTransporter1 = new ValuablesTransporter(deposit);

  new Thread(miner1).start();
  new Thread(miner2).start();
  new Thread(valuablesTransporter1).start();
}
