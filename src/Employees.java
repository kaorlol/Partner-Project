import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

public class Employees {
	// Was thinking of using float but it lacks precision for small decimal values
	// for example, 7.33 would be 7.3299999237060546875 which is not ideal for pay
	private static final double MINIMUM_WAGE = 14.65;
	private static final double MINIMUM_TIP_WAGE = 7.33;
	private static final double OVERTIME_RATE = 1.5;

	// Using class instead of something like record because school computer is on a
	// lower version of Java
	static class Employee {
		private String name;
		private Job job;
		private double hourlyWage;

		public Employee(String name, Job job, double hourlyWage) {
			this.name = name;
			this.job = job;
			this.hourlyWage = hourlyWage;
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
		Cashier
	}

	// Was thinking of using UUID but AtomicInteger is more straightforward
	private static final AtomicInteger UID_COUNTER = new AtomicInteger();
	// Fail-safe and doesn't allow null keys or values
	private final Dictionary<Integer, Employee> EMPLOYEES = new Hashtable<>();

	public int addEmployee(String name, Job job, double hourlyWage) {
		if (hourlyWage < (job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE)) {
			// System.err.println("Invalid hourlyWage for " + name);
			// return -1;
			throw new IllegalArgumentException(String.format("Invalid hourly wage for %s", name));
		}

		// You can't have multiple managers 🙄
		if (job == Job.Manager) {
			Enumeration<Integer> keys = EMPLOYEES.keys();
			while (keys.hasMoreElements()) {
				Integer key = keys.nextElement();
				Employee jp = EMPLOYEES.get(key);
				if (jp.getJob() == Job.Manager) {
					fireEmployee(key);
					break;
				}
			}
		}

		int uid = UID_COUNTER.incrementAndGet();
		EMPLOYEES.put(uid, new Employee(name, job, hourlyWage));
		return uid;
	}

	public int addEmployee(String name, Job job) {
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
		Enumeration<Integer> keys = EMPLOYEES.keys();
		while (keys.hasMoreElements()) {
			Integer key = keys.nextElement();
			Employee jp = EMPLOYEES.get(key);
			result += String.format("%s (uid: %d) is a %s and makes $%.2f per hour\n", jp.getName(), key, jp.getJob(),
					jp.getHourlyWage());
		}
		return result;
	}
}
