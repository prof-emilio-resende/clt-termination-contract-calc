package fit.termination;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

import fit.termination.domain.Employee;
import fit.termination.services.TerminationCalculator;

/**
 * Unit test for simple App.
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
        var employee = new Employee(1200.00);
        var calculator = new TerminationCalculator(employee, 10);
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
        var employee = new Employee(1200, LocalDate.of(2018, 9, 11), LocalDate.of(2019, 9, 11));
        var calculator = new TerminationCalculator(employee, 11);
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
        // act
        // assert
    }
}
