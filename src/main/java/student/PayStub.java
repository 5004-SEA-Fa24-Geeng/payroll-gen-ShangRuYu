package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

class PayStub implements IPayStub {
    private String employeeName;
    private BigDecimal netPay;
    private BigDecimal taxesPaid;
    private BigDecimal ytdEarnings;
    private BigDecimal ytdTaxesPaid;

    public PayStub(String employeeName, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = new BigDecimal(Double.toString(netPay)).setScale(2, RoundingMode.HALF_UP);
        this.taxesPaid = new BigDecimal(Double.toString(taxesPaid)).setScale(2, RoundingMode.HALF_UP);
        this.ytdEarnings = new BigDecimal(Double.toString(ytdEarnings)).setScale(2, RoundingMode.HALF_UP);
        this.ytdTaxesPaid = new BigDecimal(Double.toString(ytdTaxesPaid)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public double getPay() {
        return netPay.doubleValue();
    }

    @Override
    public double getTaxesPaid() {
        return taxesPaid.doubleValue();
    }

    private String formatNumber(double value) {
        String formatted = String.format("%.2f", value);
        if (formatted.endsWith(".00")) {
            formatted = formatted.substring(0, formatted.length() - 3) + ".0";
        }
        return formatted;
    }


    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s",
                employeeName, formatNumber(netPay.doubleValue()),
                formatNumber(taxesPaid.doubleValue()),
                formatNumber(ytdEarnings.doubleValue()),
                formatNumber(ytdTaxesPaid.doubleValue()));
    }
}
