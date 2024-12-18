import java.util.*;

public class Employees {
	// Was thinking of using float but it lacks precision for small decimal values
	// for example, 7.33 would be 7.3299999237060546875 which is not ideal for pay
	private final double MINIMUM_WAGE = 14.65;
	private final double MINIMUM_TIP_WAGE = 7.33;
	private final double OVERTIME_RATE = 1.5;

	// Using class instead of something like record because school computer is on a
	// lower version of Java
	class Employee {
		private int uid;
		private String name;
		private Job job;
		private double hourlyWage;

		public Employee(int uid, String name, Job job, double hourlyWage) {
			this.uid = uid;
			this.name = name;
			this.job = job;
			this.hourlyWage = hourlyWage;
		}

		public int getUid() {
			return uid;
		}

		public String getName() {
			return name;
		}

		public Job getJob() {
			return job;
		}

		public double getHourlyWage() {
			return hourlyWage;
		}

		public void setJob(Job job) {
			this.job = job;
		}

		public void setHourlyWage(double hourlyWage) {
			this.hourlyWage = hourlyWage;
		}
	}

	// Using enum to prevent hard-coding strings
	enum Job {
		Manager,
		Cook,
		Cashier;

		public double order() {

			return 10.0;
		}
	}

	private int UID_COUNTER = 0;
	private final Map<Integer, Employee> EMPLOYEES = new HashMap<>();

	public Employee addEmployee(String name, Job job, double hourlyWage) {
		if (hourlyWage < (job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE)) {
			// System.err.println("Invalid hourlyWage for " + name);
			// return -1;
			throw new IllegalArgumentException(String.format("Invalid hourly wage for %s", name));
		}

		// You can't have multiple managers 🙄
		if (job == Job.Manager) {
			for (Map.Entry<Integer, Employee> entry : EMPLOYEES.entrySet()) {
				if (entry.getValue().getJob() == Job.Manager) {
					fireEmployee(entry.getKey());
					break;
				}
			}
		}

		UID_COUNTER++;
		Employee e = new Employee(UID_COUNTER, name, job, hourlyWage);
		EMPLOYEES.put(UID_COUNTER, e);
		return e;
	}

	public Employee addEmployee(String name, Job job) {
		return addEmployee(name, job, job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE);
	}

	public void fireEmployee(int uid) {
		String name = EMPLOYEES.get(uid).getName();
		EMPLOYEES.remove(uid);
		System.out.println("Fired: " + name);
	}

	public double calculatePay(int uid, double hours) {
		Employee jp = EMPLOYEES.get(uid);
		if (jp == null) {
			// System.err.println("Employee uid not found: " + uid);
			// return 0;
			throw new IllegalArgumentException(String.format("Employee with uid %d not found", uid));
		}

		double hourlyWage = jp.getHourlyWage();
		double overtime = Math.max(hours - 40.0, 0.0);
		// hourlyWage * (hours - overtime) -> pay for regular hours
		// hourlyWage * OVERTIME_RATE * overtime -> pay for overtime hours
		return hourlyWage * (hours - overtime) + hourlyWage * OVERTIME_RATE * overtime;
	}

	public void giveRaise(int uid, double raise) {
		Employee jp = EMPLOYEES.get(uid);
		if (jp == null) {
			// System.err.println("Employee uid not found: " + uid);
			// return;
			throw new IllegalArgumentException(String.format("Employee with uid %d not found", uid));
		}
		jp.setHourlyWage(jp.getHourlyWage() + raise);
	}

	public String toString() {
		String result = "";
		for (Map.Entry<Integer, Employee> entry : EMPLOYEES.entrySet()) {
			result += String.format("UID: %d, Name: %s, Job: %s, Hourly Wage: $%.2f\n",
					entry.getKey(), entry.getValue().getName(), entry.getValue().getJob(),
					entry.getValue().getHourlyWage());
		}
		return result;
	}
}