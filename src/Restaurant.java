public class Restaurant {
	public static void main(String[] args) {
		Employees e = new Employees();
		Employees.Employee jacoby = e.addEmployee("Jacoby", Employees.Job.Manager, 20.0);
		Employees.Employee will = e.addEmployee("Will", Employees.Job.Cook);
		Employees.Employee casey = e.addEmployee("Casey", Employees.Job.Cashier);
		Employees.Employee trent = e.addEmployee("Trent", Employees.Job.Cashier, 10.0);
		Employees.Employee brendan = e.addEmployee("Brendan", Employees.Job.Cashier, 10.0);
		e.fireEmployee(trent.getUid());
		e.giveRaise(brendan.getUid(), 2.77);

		double a = will.getJob().order();
		System.out.println(a);

		System.out.println("Jacoby's pay: $" + e.calculatePay(jacoby.getUid(), 50.0));

		trent = e.addEmployee("Trent", Employees.Job.Manager, 22.5);

		System.out.println(e.toString());
	}
}
