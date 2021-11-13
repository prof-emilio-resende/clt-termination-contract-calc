package fit.termination;

import static org.junit.Assert.assertEquals;

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
}
