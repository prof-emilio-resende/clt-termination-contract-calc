package fit.termination.services;

import java.time.temporal.ChronoUnit;

import fit.termination.domain.Employee;

public class TerminationCalculator {
    private Employee employee;
    private int lastLaborDaysCount;

    public TerminationCalculator(Employee employee, int lastLaborDaysCount) {
        this.employee = employee;
        this.lastLaborDaysCount = lastLaborDaysCount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getLastLaborDaysCount() {
        return lastLaborDaysCount;
    }

    public void setLastLaborDaysCount(int lastLaborDaysCount) {
        this.lastLaborDaysCount = lastLaborDaysCount;
    }

    public double getSalaryBalance() {
        var dailySalary = employee.getTotalSalary() / 30;
        return dailySalary * lastLaborDaysCount;
    }

    public int getOverdueVacationsMonthsCount() {
        return Long.valueOf(ChronoUnit.MONTHS.between(employee.getInitialDate(), employee.getLastDate())).intValue();
    }

    public double getOverdueVacationsValue() {
        var salaryToCalc = (employee.getTotalSalary()/12) * getOverdueVacationsMonthsCount();
        
        return salaryToCalc + (salaryToCalc/3);
    }

}
