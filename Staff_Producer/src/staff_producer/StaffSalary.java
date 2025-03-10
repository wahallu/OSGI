package staff_producer;

public class StaffSalary {
	 private String id;
	    private String staffName;
	    private String role;
	    private double salary;
	    private String date;
	    private String month;

	    public StaffSalary(String id, String staffName, String role, double salary, String date, String month) {
	        this.id = id;
	        this.staffName = staffName;
	        this.role = role;
	        this.salary = salary;
	        this.date = date;
	        this.month = month;
	    }

	    public String getId() { return id; }
	    public String getStaffName() { return staffName; }
	    public String getRole() { return role; }
	    public double getSalary() { return salary; }
	    public String getDate() { return date; }
	    public String getMonth() { return month; }

	    public void setId(String id) { this.id = id; }
	    public void setStaffName(String staffName) { this.staffName = staffName; }
	    public void setRole(String role) { this.role = role; }
	    public void setSalary(double salary) { this.salary = salary; }
	    public void setDate(String date) { this.date = date; }
	    public void setMonth(String month) { this.month = month; }

}
