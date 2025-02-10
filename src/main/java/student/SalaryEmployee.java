package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

class SalaryEmployee extends Employee {
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    protected BigDecimal calculateGrossPay(double hoursWorked) {
        return payRate.divide(BigDecimal.valueOf(24), RoundingMode.HALF_UP);
    }

    @Override
    public String toCSV() {
        return String.format("SALARY,%s,%s,%.2f,%.2f,%.2f,%.2f", name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }
}
