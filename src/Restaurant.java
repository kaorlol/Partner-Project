import java.util.Scanner;

public class Restaurant {
	public static void main(String[] args) {
		Employee jacoby = new Employee("Jacoby", Employee.Job.Manager, 20.0);
		Employee will = new Employee("Will", Employee.Job.Cook);
		Employee casey = new Employee("Casey", Employee.Job.Cashier);
		Employee trent = new Employee("Trent", Employee.Job.Cashier, 10.0);
		Employee brendan = new Employee("Brendan", Employee.Job.Cashier, 10.0);
		jacoby.fireEmployee(trent);
		brendan.giveRaise(2.77);

		System.out.println("Jacoby's pay: $" + jacoby.calculatePay(50.0));

		trent = new Employee("Trent", Employee.Job.Manager, 22.5);

		System.out.println(casey.toString());

		System.out.print("Please state your order: ");
		Scanner sc = new Scanner(System.in);
		String userOrder = sc.nextLine();
		String a = will.order(userOrder);
		System.out.println(a);

		sc.close();
	}
}
