import dk.via.pro2.assignment_3.Deposit;
import dk.via.pro2.assignment_3.Miner;

void main()
{
  Deposit deposit = new Deposit();
  Miner miner1 = new Miner("John", deposit);
  Miner miner2 = new Miner("Mikel", deposit);

  miner1.run();
  miner2.run();
}
