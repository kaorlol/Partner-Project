// Ill add a uid system to the employees tmr so the database can have multiple employees with the same name
public class Restaurant {
	public static void main(String[] args) {
		Employees e = new Employees();
		e.addEmployee("Jacoby", Employees.Job.Manager, 20.0);
		e.addEmployee("Will", Employees.Job.Cook);
		e.addEmployee("Casey", Employees.Job.Cashier);
		e.addEmployee("Trent", Employees.Job.Cashier, 10.0);
		e.addEmployee("Brendan", Employees.Job.Cashier, 10.0);
		e.fireEmployee("Trent");
		e.giveRaise("Brendan", 2.77);

		System.out.println("Jacoby's pay: $" + e.calculatePay("Jacoby", 50.0));

		e.addEmployee("Trent", Employees.Job.Manager, 22.5);
	}
}
