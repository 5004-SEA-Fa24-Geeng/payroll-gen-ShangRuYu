package student;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class HourlyEmployee extends Employee {
    /**
     * Constructor for Employee
     * @param name Employee's name
     * @param id Employee's id
     * @param payRate Employee's pay rate
     * @param ytdEarnings Employee's year-to-date earnings
     * @param ytdTaxesPaid Employee's year-to-date taxes paid
     * @param pretaxDeductions Employee's pretax deductions
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    /**
     * Calculate the total payment based on hour worked
     * @param hoursWorked Total number of hours worked
     * @return Calculated gross pay include overtime hours
     */

    @Override
    public double calculateGrossPay(double hoursWorked) {
        if (hoursWorked < 0 ){
            return 0;
        }
        double grossPay = 0;
        if (hoursWorked <= 40) {
            grossPay = getPayRate() * hoursWorked;
        } else {
            grossPay = getPayRate() * 40 + getPayRate() * 1.5 * (hoursWorked - 40);
        }
        return roundValue(grossPay);
    }

    /**
     * Converts info into format
     * @return A string contains employee info in a CSV format
     */
    @Override
    public String toCSV() {
        return String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getName(), getID(), getPayRate(), getYTDEarnings(), getYTDTaxesPaid(), getPretaxDeductions());
    }

    /**
     * Returns type of employee
     * @return Hourly employee
     */
    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }
}
