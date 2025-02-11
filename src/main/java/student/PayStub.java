package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

class PayStub implements IPayStub {
    private IEmployee employee;
    private double netPay;
    private double taxes;

    public PayStub(IEmployee employee, double netPay, double taxes) {
        this.employee = employee;
        this.netPay = netPay;
        this.taxes = taxes;
    }

    @Override
    public double getPay() {
        return this.netPay;
    }

    @Override
    public double getTaxesPaid() {
        return this.taxes;
    }



    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employee.getName(),
                netPay,
                taxes,
                employee.getYTDEarnings(),
                employee.getYTDTaxesPaid());
    }
}
