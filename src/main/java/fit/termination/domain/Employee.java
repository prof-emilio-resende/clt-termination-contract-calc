package fit.termination.domain;

public class Employee {
    private double totalSalary;

    public Employee(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
