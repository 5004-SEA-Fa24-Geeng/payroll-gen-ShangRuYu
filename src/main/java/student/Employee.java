package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Abstract base class for all employee types that implements the IEmployee interface.
public abstract class Employee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    /**
     * Constructor for Employee
     * @param name Employee's name
     * @param id Employee's id
     * @param payRate Employee's pay rate
     * @param ytdEarnings Employee's year-to-date earnings
     * @param ytdTaxesPaid Employee's year-to-date taxes paid
     * @param pretaxDeductions Employee's pretax deductions
     */
    public Employee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {

        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    // Getter methods for accessing employee information
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return this.payRate;
    }

    @Override
    public double getYTDEarnings() {
        return this.ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    @Override
    public abstract String getEmployeeType();

    protected abstract double calculateGrossPay(double hoursWorked);

    public double roundValue(double val) {
        BigDecimal bd = new BigDecimal(val).setScale(2, RoundingMode.HALF_UP);
        val = bd.doubleValue();
        return val;
    }

    /**
     * Process payroll for employee, calculates tax, deduction, net pay.
     *
     * @param hoursWorked the hours worked for the pay period
     * @return Contain payment details
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }
        double grossPay = calculateGrossPay(hoursWorked);

        double taxableIncome = grossPay - pretaxDeductions;

        double taxes = roundValue(taxableIncome * 0.2265);
        double netPay = roundValue(taxableIncome - taxes);

        this.ytdEarnings = roundValue(this.ytdEarnings + netPay);

        this.ytdTaxesPaid = roundValue(Math.max(0, this.ytdTaxesPaid + taxes));


        return new PayStub(this, netPay, taxes);
    }

    @Override
    public abstract String toCSV();
}
