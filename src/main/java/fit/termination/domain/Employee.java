package fit.termination.domain;

import java.time.LocalDate;

public class Employee {
    private double totalSalary;
    private LocalDate initialDate;
    private LocalDate lastDate;
    private int vacationsPeriodsTaken;
    private int thirteenthSalaryPaidCount;

    public Employee(double totalSalary) {
        this.setTotalSalary(totalSalary);
    }
    
    public Employee(double totalSalary, LocalDate initialDate, LocalDate lastDate, int vacationsPeriodsTaken, int thirteenthSalaryPaidCount) {
        this.setTotalSalary(totalSalary);
        this.setInitialDate(initialDate);
        this.setLastDate(lastDate);
        this.setVacationsPeriodsTaken(vacationsPeriodsTaken);
        this.setThirteenthSalaryPaidCount(thirteenthSalaryPaidCount);
    }

    public int getVacationsPeriodsTaken() {
        return vacationsPeriodsTaken;
    }

    public void setVacationsPeriodsTaken(int vacationsPeriodsTaken) {
        this.vacationsPeriodsTaken = vacationsPeriodsTaken;
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

    public int getThirteenthSalaryPaidCount() {
        return thirteenthSalaryPaidCount;
    }

    public void setThirteenthSalaryPaidCount(int thirteenthSalaryPaidCount) {
        this.thirteenthSalaryPaidCount = thirteenthSalaryPaidCount;
    }
}
