package fit.termination.services;

import java.time.Period;
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
        var monthsTaken = employee.getVacationsPeriodsTaken() * 12;
        var totalMonths = Long.valueOf(ChronoUnit.MONTHS.between(employee.getInitialDate(), employee.getLastDate())).intValue();
        var months = totalMonths - monthsTaken;
        return months > 0 ? months : 0;
    }

    public double getOverdueVacationsValue() {
        var salaryToCalc = (employee.getTotalSalary()/12) * getOverdueVacationsMonthsCount();
        
        return salaryToCalc + (salaryToCalc/3);
    }

    public int getOverdueThirteenthMonthsCount() {
        var monthsOutOfScope = employee.getThirteenthSalaryPaidCount() * 12;
        var totalMonths = Long.valueOf(ChronoUnit.MONTHS.between(employee.getInitialDate(), employee.getLastDate())).intValue();
        var daysOnLastMonth = Period.between(employee.getInitialDate(), employee.getLastDate()).getDays();
        if (daysOnLastMonth > 14) totalMonths++;

        return totalMonths - monthsOutOfScope;
    }

    public double getOverdueThirteenthValue() {
        var thirteenthSalaryTerm = employee.getTotalSalary() / 12;
        return thirteenthSalaryTerm * getOverdueThirteenthMonthsCount();
    }

    public int getContractTerminationNoticeDays() {
        var totalYears = Period.between(employee.getInitialDate(), employee.getLastDate());
        return 30 + (3 * totalYears.getYears());
    }

    public double getContractTerminationFGTSPenalty() {
        var monthtlyFGTS = employee.getTotalSalary() * 0.08;
        var totalMonths = Long.valueOf(ChronoUnit.MONTHS.between(employee.getInitialDate(), employee.getLastDate())).intValue();
        var totalFGTS = totalMonths * monthtlyFGTS;
        return totalFGTS * 0.4;
    }

    public double getContractTerminationValue() {
        var salaryBalance = this.getSalaryBalance();
        var vacations = this.getOverdueVacationsValue();
        var thirteenth = this.getOverdueThirteenthValue();
        var fgts = this.getContractTerminationFGTSPenalty();

        return salaryBalance + vacations + thirteenth + fgts;
    }
}
