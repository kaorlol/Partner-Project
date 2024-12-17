import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Enumeration;

public class Employees {
	private static final double MINIMUM_WAGE = 14.65;
	private static final double MINIMUM_TIP_WAGE = 7.33;
	private static final double OVERTIME_RATE = 1.5;

	// Using class instead of something like record because school computer is on a
	// lower version of Java
	public static class JobPay {
		private Job job;
		private double pay;

		public JobPay(Job job, double pay) {
			this.job = job;
			this.pay = pay;
		}

		public Job getJob() {
			return job;
		}

		public double getPay() {
			return pay;
		}

		public void setJob(Job job) {
			this.job = job;
		}

		public void setPay(double pay) {
			this.pay = pay;
		}
	}

	// Using enum to prevent hard-coding strings
	public enum Job {
		Manager,
		Cook,
		Cashier
	}

	// Fail-safe and doesn't allow null keys or values
	private Dictionary<String, JobPay> EmployeeDatabase = new Hashtable<>();

	public void addEmployee(String name, Job job, double pay) {
		if (pay < (job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE)) {
			System.err.println("Invalid pay for " + name);
			return;
		}

		if (job == Job.Manager) {
			Enumeration<String> keys = EmployeeDatabase.keys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				JobPay jp = EmployeeDatabase.get(key);
				if (jp.getJob() == Job.Manager) {
					fireEmployee(key);
					break;
				}
			}
		}

		EmployeeDatabase.put(name, new JobPay(job, pay));
	}

	public void addEmployee(String name, Job job) {
		addEmployee(name, job, job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE);
	}

	public void fireEmployee(String name) {
		EmployeeDatabase.remove(name);
		System.out.println("Fired: " + name);
	}

	public double calculatePay(String name, double hours) {
		JobPay jp = EmployeeDatabase.get(name);
		if (jp == null) {
			System.err.println("Employee not found: " + name);
			return 0;
		}

		double pay = jp.getPay();
		// Gets the number of hours worked over 40
		double overtime = Math.max(hours - 40.0, 0.0);
		// pay * (hours - overtime) -> pay for regular hours
		// pay * OVERTIME_RATE * overtime -> pay for overtime hours
		return pay * (hours - overtime) + pay * OVERTIME_RATE * overtime;
	}

	public void giveRaise(String name, double raise) {
		JobPay jp = EmployeeDatabase.get(name);
		if (jp == null) {
			System.err.println("Employee not found: " + name);
			return;
		}
		jp.setPay(jp.getPay() + raise);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Enumeration<String> keys = EmployeeDatabase.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			JobPay jp = EmployeeDatabase.get(key);
			sb.append(key + " is a " + jp.getJob() + " and makes $" + jp.getPay() + " per hour\n");
		}
		return sb.toString();
	}
}
