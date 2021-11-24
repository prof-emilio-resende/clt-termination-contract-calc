package fit.termination;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import fit.termination.domain.Employee;
import fit.termination.services.TerminationCalculator;

/**
 * Unit test for contract termination App.
 */
public class AppTest 
{
    /**
     * Validate if the salary balance is being calculated respecting the business rule:
     * Given an termination contract of the employee X
     * When his salary is R$1200.00 and his last labor days count are 10
     * Then the expected balance is R$400.00, according to the formula: totalSalary/30 * last labor days count
     */
    @Test
    public void shouldCalculateSalaryBalance() {
        // arrange (given)
        var employee = new Employee(1200.00, LocalDate.of(2018, 9, 11), LocalDate.of(2019,9,10), 1, 0);
        var calculator = new TerminationCalculator(employee);
        var expectedBalance = 400.00;

        // act (when)
        var balance = calculator.getSalaryBalance();
        
        // assert (then)
        assertEquals(expectedBalance, balance, 0.01);
    }

    /**
     * Validate the calculation for overdue vacations, respecting the business rule:
     * Given an termination contract of the employee X
     * When his initial date was 11/09/2018(dd/MM/yyyy) and his last working day was 11/09/2019(dd/MM/yyyy)
     * Then the expected overdue vacations months count is 12
     */
    @Test
    public void shouldCalculateOverdueVacationsMonthsCount() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2018, 9, 11), LocalDate.of(2019, 9, 11), 0, 0);
        var calculator = new TerminationCalculator(employee);
        var expected = 12;

        // act
        var overdueVacationsMonthsCount = calculator.getOverdueVacationsMonthsCount();

        // assert
        assertEquals(expected, overdueVacationsMonthsCount);
    }

    /**
     * Validate the calculation for overdue vacations, respecting the business rule:
     * Given an termination contract of the employee X
     * When his salary is R$1200.00 and his overdue vacations sums 12 months
     * Then the expected overdue vacations value is expected to be R$1600.00, according to the formula: totalSalary + (totalSalary * 0.3)
     */
    @Test
    public void shouldCalculateOverdueVacationsValue() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2018, 9, 11), LocalDate.of(2019, 9, 11), 0, 0);
        var calculator = new TerminationCalculator(employee);
        var expected = 1600.00;

        // act
        var overdueVacationsValue = calculator.getOverdueVacationsValue();

        // assert
        assertEquals(expected, overdueVacationsValue, 0.01);
    }

    /**
     * Validate the calculation for overdue vacations, respecting the business rule:
     * Given an termination contract of the employee X
     * When his initial date was 11/09/2018(dd/MM/yyyy) 
     *  and his last working day was 11/04/2020(dd/MM/yyyy)
     *  and he took 1 (one) vacation period already
     * Then the expected overdue vacations months count is 7
     */
    @Test
    public void shouldCalculatePartialOverdueVacationsMonthsCount() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2018, 9, 11), LocalDate.of(2020, 4, 11), 1, 1);
        var calculator = new TerminationCalculator(employee);
        var expected = 7;

        // act
        var overdueVacationsMonthsCount = calculator.getOverdueVacationsMonthsCount();

        // assert
        assertEquals(expected, overdueVacationsMonthsCount);
    }

    /**
     * Validate the calculation for overdue vacations, respecting the business rule:
     * Given an termination contract of the employee X with R$1200.00 of monthly salary
     * When his initial date was 11/09/2018(dd/MM/yyyy) 
     *  and his last working day was 11/04/2020(dd/MM/yyyy)
     *  and he took 1 (one) vacation period already
     * Then the expected overdue vacations value is R$933.33..., according to the formula:
     * ((salary/12) * OverdueVacationPeriod) * (1 + 1/3)
     * e.g. ((1200.00/12) * 7) * (1 + 1/3)
     */
    @Test
    public void shouldCalculatePartialOverdueVacationsValue() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2018, 9, 11), LocalDate.of(2020, 4, 11), 1, 1);
        var calculator = new TerminationCalculator(employee);
        var expected = 933.33;

        // act
        var overdueVacationsValue = calculator.getOverdueVacationsValue();

        // assert
        assertEquals(expected, overdueVacationsValue, 0.01);
    }

    /**
     * Validate the calculations for applicable thirteenth salary, according to the following rules:
     * Given an termination contract of the employee X, with R$1200.00 of monthly salary
     * When his initial date was 01/01/2021(dd/MM/yyyy)
     *  and his last working day was 10/04/2021 (dd/MM/yyyy)
     *      considering that every month only counts with minimum of 14 working days
     * Then the expected thirteenth salary mounts count are 3
     */
    @Test
    public void shouldCalculatePartialThirteenthSalaryMonthsCount() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 4, 10), 0, 0);
        var calculator = new TerminationCalculator(employee);
        var expected = 3;

        // act
        var partialThirteenthSalaryMonthsCount = calculator.getOverdueThirteenthMonthsCount();

        // assert
        assertEquals(expected, partialThirteenthSalaryMonthsCount);
    }

    /**
     * Validate the calculations for applicable thirteenth salary, according to the following rules:
     * Given an termination contract of the employee X, with R$1200.00 of monthly salary
     * When his initial date was 01/01/2021(dd/MM/yyyy)
     *  and his last working day was 10/04/2021 (dd/MM/yyyy)
     *      considering that every month only counts with minimum of 14 working days
     * Then the expected thirteenth salary value is R$300.00
     */
    @Test
    public void shouldCalculatePartialThirteenthSalaryValue() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 4, 10), 0, 0);
        var calculator = new TerminationCalculator(employee);
        var expected = 300;

        // act
        var partialThirteenthSalaryValue = calculator.getOverdueThirteenthValue();

        // assert
        assertEquals(expected, partialThirteenthSalaryValue, 0.01);
    }

    /**
     * Validate the contract termination required notice in days, based on the calculation: 30 + (number of labor years * 3)
     * Given the termination contract of the employee X
     * When his initial date was 11/09/2019 (dd/MM/yyyy)
     *  and his last working day was 10/04/2021 (dd/MM/yyyy)
     * Then the expected notice time in days is 33
     */
    @Test
    public void shouldCalculateContractTerminationNoticeDays() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2019, 9, 11), LocalDate.of(2021, 4, 10), 0, 1);
        var calculator = new TerminationCalculator(employee);
        var expected = 33;

        // act
        var noticeDays = calculator.getContractTerminationNoticeDays();

        // assert
        assertEquals(expected, noticeDays, 0.01);
    }

    /** 
     * Validate the contract termination penalty over the tax FGTS, based on the calculation: total FGTS paid * 40%
     * Given the termination contract of the employee X, with R$1200.00 of monthly salary
     * When his initial date was 01/01/2020 (dd/MM/yyyy)
     *   and his last working day was 01/08/2021 (dd/MM/yyyy)
     * Then the expected penalty is R$729.60
     */
    @Test
    public void shouldCalculateFGTSPenalty() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2020, 1, 1), LocalDate.of(2021, 8, 1), 0, 1);
        var calculator = new TerminationCalculator(employee);
        var expected = 729.60;

        // act
        var fgtsPenalty = calculator.getContractTerminationFGTSPenalty();

        // assert
        assertEquals(expected, fgtsPenalty, 0.01);
    }

    /**
     * Validate the contract termination total value calculation, which is a composition of all the previous rules
     * Given the termination contract of the employee X, with R$1200.00 of monthly salary
     * When his initial date was 01/01/2020 (dd/MM/yyyy)
     *  and his last working day was 01/08/2021 (dd/MM/yyyy)
     * Then the expected penalty is R$
     */
    @Test
    public void shouldCalculateTotalTerminationValue() {
        // arrange
        var employee = new Employee(1200, LocalDate.of(2020, 1, 1), LocalDate.of(2021, 8, 1), 0, 1);
        var calculator = new TerminationCalculator(employee);
        var expected = 4002.93;

        // act
        var totalTerminantionValue = calculator.getContractTerminationValue();

        // assert
        assertEquals(expected, totalTerminantionValue, 0.01);
    }
}
