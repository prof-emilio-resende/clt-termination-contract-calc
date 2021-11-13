package fit.termination.domain;

import java.time.LocalDate;

public class Employee {
    private double totalSalary;
    private LocalDate initialDate;
    private LocalDate lastDate;

    public Employee(double totalSalary) {
        this.totalSalary = totalSalary;
    }
    
    public Employee(double totalSalary, LocalDate initialDate, LocalDate lastDate) {
        this.totalSalary = totalSalary;
        this.initialDate = initialDate;
        this.lastDate = lastDate;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
