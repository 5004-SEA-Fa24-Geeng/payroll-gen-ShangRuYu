package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Employee implements IEmployee {
    protected String name;
    protected String id;
    protected BigDecimal payRate;
    protected BigDecimal ytdEarnings;
    protected BigDecimal ytdTaxesPaid;
    protected BigDecimal pretaxDeductions;

    public Employee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = BigDecimal.valueOf(payRate).setScale(2, RoundingMode.HALF_UP);
        this.ytdEarnings = BigDecimal.valueOf(ytdEarnings).setScale(2, RoundingMode.HALF_UP);
        this.ytdTaxesPaid = BigDecimal.valueOf(ytdTaxesPaid).setScale(2, RoundingMode.HALF_UP);
        this.pretaxDeductions = BigDecimal.valueOf(pretaxDeductions).setScale(2, RoundingMode.HALF_UP);
    }

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
        return payRate.doubleValue();
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings.doubleValue();
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid.doubleValue();
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions.doubleValue();
    }

    @Override
    public abstract String getEmployeeType();

    protected abstract BigDecimal calculateGrossPay(double hoursWorked);

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        BigDecimal grossPay = calculateGrossPay(hoursWorked);
        grossPay = grossPay.setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxableIncome = grossPay.subtract(pretaxDeductions).setScale(2, RoundingMode.HALF_UP).max(BigDecimal.ZERO);
        BigDecimal taxRate = new BigDecimal(Double.toString(0.2265));
        BigDecimal taxes = taxableIncome.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal netPay = taxableIncome.subtract(taxes).setScale(2,RoundingMode.HALF_UP);

        BigDecimal currentYtdEarnings = ytdEarnings.subtract(grossPay);
        BigDecimal currentYtdTaxesPaid = ytdTaxesPaid.subtract(taxes);

        ytdEarnings = currentYtdEarnings.add(grossPay).setScale(2, RoundingMode.HALF_UP);
        ytdTaxesPaid = currentYtdTaxesPaid.add(taxes).setScale(2, RoundingMode.HALF_UP);


        return new PayStub(name, netPay.doubleValue(), taxes.doubleValue(), ytdEarnings.doubleValue(), ytdTaxesPaid.doubleValue());
    }

    @Override
    public abstract String toCSV();
}
