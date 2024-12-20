import java.time.LocalDate;

public class Employee {
	// Was thinking of using float but it lacks precision for small decimal values
	// for example, 7.33 would be 7.3299999237060546875 which is not ideal for pay
	private static final double MINIMUM_WAGE = 14.65;
	private static final double MINIMUM_TIP_WAGE = 7.33;
	private final double OVERTIME_RATE = 1.5;

	// Using enum to prevent hard-coding strings
	enum Job {
		Manager,
		Cook,
		Cashier;
	}

	private String name;
	private Job job;
	private double hourlyWage;

	public Employee(int uid, String name, Job job, double hourlyWage) {
		this.name = name;
		this.job = job;
		this.hourlyWage = hourlyWage;
	}

	public void setName(String name) {
		this.name = name;
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

	public void fireEmployee(Employee e) {
		e = null;
		System.out.println("Fired: " + name);
	}

	public String order(String o) {
		double change = 0;

		switch (o.toLowerCase().trim()) {
			case "burger":
				cook(o);
				change += 8.5;
				break;
			case "fries":
				cook(o);
				change += 6.4;
				break;
			case "wings":
				cook(o);
				change += 11.3;
				break;
			default:
				break;
		}

		return String.format("Benny's Burger, cost: %s, %s (%s), %s", change, this.getName(),
				this.getJob(), LocalDate.now());
	}

	private void cook(String food) {
		System.out.println("Cooked " + food);

	}

	Employee(String name, Job job, double hourlyWage) {
		if (hourlyWage < (job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE)) {
			throw new IllegalArgumentException(String.format("Invalid hourly wage for %s", name));
		}

		this.setName(name);
		this.setJob(job);
		this.setHourlyWage(hourlyWage);
	}

	Employee(String name, Job job) {
		this(name, job, job == Job.Cashier ? MINIMUM_TIP_WAGE : MINIMUM_WAGE);
	}

	public double calculatePay(double hours) {
		double hourlyWage = this.getHourlyWage();
		double overtime = Math.max(hours - 40.0, 0.0);
		// hourlyWage * (hours - overtime) -> pay for regular hours
		// hourlyWage * OVERTIME_RATE * overtime -> pay for overtime hours
		return hourlyWage * (hours - overtime) + hourlyWage * OVERTIME_RATE * overtime;
	}

	public void giveRaise(double raise) {
		this.setHourlyWage(this.getHourlyWage() + raise);
	}

	public String toString() {
		String result = "";
		result += String.format("Name: %s, Job: %s, Hourly Wage: $%.2f\n", this.getName(), this.getJob(),
				this.getHourlyWage());
		return result;
	}
}