package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

class HourlyEmployee extends Employee {
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    protected BigDecimal calculateGrossPay(double hoursWorked) {
        BigDecimal regularHours = BigDecimal.valueOf(Math.min(40, hoursWorked));
        BigDecimal overtimeHours = BigDecimal.valueOf(Math.max(0, hoursWorked - 40));
        BigDecimal overtimeRate = payRate.multiply(BigDecimal.valueOf(1.5));

        return regularHours.multiply(payRate).add(overtimeHours.multiply(overtimeRate)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toCSV() {
        return String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f", name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }
}
