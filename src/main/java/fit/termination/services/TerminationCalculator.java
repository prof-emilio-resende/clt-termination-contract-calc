package fit.termination.services;

import java.time.temporal.ChronoUnit;

import fit.termination.domain.Employee;

public class TerminationCalculator {
    private Employee employee;

    public TerminationCalculator(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getLastLaborDaysCount() {
        return employee.getLastDate().getDayOfMonth();
    }

    public double getSalaryBalance() {
        var dailySalary = employee.getTotalSalary() / 30;
        return dailySalary * getLastLaborDaysCount();
    }

    public int getOverdueVacationsMonthsCount() {
        var monthsTaken = this.employee.getVacationsPeriodsTaken() * 12;
        var totalMonths = Long.valueOf(ChronoUnit.MONTHS.between(employee.getInitialDate(), employee.getLastDate())).intValue();
        var months = totalMonths - monthsTaken;
        return months > 0 ? months : 0;
    }

    public double getOverdueVacationsValue() {
        var salaryToCalc = (employee.getTotalSalary()/12) * getOverdueVacationsMonthsCount();
        
        return salaryToCalc + (salaryToCalc/3);
    }

}
