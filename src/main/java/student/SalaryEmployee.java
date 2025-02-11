package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee extends Employee {
    /**
     * create a SalaryEmployee object.
     * @param name employeeName
     * @param id employeeId
     * @param payRate payRate
     * @param ytdEarnings ytdEarnings
     * @param ytdTaxesPaid ytdTaxesPaid
     * @param pretaxDeductions pretaxDeductions
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    public double calculateGrossPay(double hoursWorked) {
        if (hoursWorked < 0) {
            return 0;
        }
        double grossPay = getPayRate() / 24;
        return roundValue(grossPay);
    }

    @Override
    public String toCSV() {
        return String.format("SALARY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getName(), getID(), getPayRate(), getYTDEarnings(), getYTDTaxesPaid(), getPretaxDeductions());
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }
}
