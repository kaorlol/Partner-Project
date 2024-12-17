public class Restaurant {
	public static void main(String[] args) {
		Employees e = new Employees();
		int jacoby = e.addEmployee("Jacoby", Employees.Job.Manager, 20.0);
		int will = e.addEmployee("Will", Employees.Job.Cook);
		int casey = e.addEmployee("Casey", Employees.Job.Cashier);
		int trent = e.addEmployee("Trent", Employees.Job.Cashier, 10.0);
		int brendan = e.addEmployee("Brendan", Employees.Job.Cashier, 10.0);
		e.fireEmployee(trent);
		e.giveRaise(brendan, 2.77);

		System.out.println("Jacoby's pay: $" + e.calculatePay(jacoby, 50.0));

		trent = e.addEmployee("Trent", Employees.Job.Manager, 22.5);

		System.out.println(e.toString());
	}
}
