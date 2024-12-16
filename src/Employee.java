import java.util.Dictionary;
import java.util.Hashtable;

public class Employee {
	private double MinimumWage = 14.65;
	private double MinimumTipWage = 7.33;
	private double OvertimeRate = 1.5;

	Dictionary<String, Double> EmployeeDatabase = new Hashtable<>();

	public String Manager = "";
	public String Cook = "";
	public String Cashier = "";

	public Employee(String name, String job, double pay) {
		switch (job) {
			case "manager":
				Manager = name;
				break;
			case "cook":
				Cook = name;
				break;
			case "cashier":
				Cashier = name;
			default:
				break;
		}
	}

	public Employee(String name, String job) {
		
	}
}
