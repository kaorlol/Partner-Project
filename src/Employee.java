import java.util.Dictionary;
import java.util.Hashtable;

public class Employee {
	private double MinimumWage = 14.65;
	private double MinimumTipWage = 7.33;
	private double OvertimeRate = 1.5;

	private class JobPay {
		public Job job;
		public double pay;

		public JobPay(Job j, double p) {
			job = j;
			pay = p;
		}
	}

	private enum Job {
		Manager,
		Cook,
		Cashier
	}

	Dictionary<String, Double> EmployeeDatabase = new Hashtable<>();

	// public String Manager = "";
	// public String Cook = "";
	// public String Cashier = "";

	public Employee(String name, String job, double pay) {
		switch (job) {
			case "manager":
				// EmployeeDatabase.put(name, new JobPay(job, pay))
				break;
			case "cook":
	
				break;
			case "cashier":
			default:
				break;
		}
	}

	public Employee(String name, String job) {

	}
}
