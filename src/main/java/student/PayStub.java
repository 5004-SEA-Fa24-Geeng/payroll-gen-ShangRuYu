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
        this.netPay = new BigDecimal(String.valueOf(netPay)).setScale(2, RoundingMode.HALF_EVEN);
        this.taxesPaid = new BigDecimal(String.valueOf(taxesPaid)).setScale(2, RoundingMode.HALF_EVEN);
        this.ytdEarnings = new BigDecimal(String.valueOf(ytdEarnings)).setScale(2, RoundingMode.HALF_EVEN);
        this.ytdTaxesPaid = new BigDecimal(String.valueOf(ytdTaxesPaid)).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public double getPay() {
        return netPay.doubleValue();
    }

    @Override
    public double getTaxesPaid() {
        return taxesPaid.doubleValue();
    }

    private String formatNumber(double number) {
        String formatted = String.format("%.2f", number);
        if (formatted.endsWith(".00")) {
            formatted = formatted.substring(0, formatted.length() - 3) + ".0";
        } else if (formatted.endsWith("0")) {
            formatted = formatted.substring(0, formatted.length() - 1);
        }
        return formatted;
    }

    private String formatAmount(double number) {
        return String.format("%.2f", number);
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s",
                employeeName,
                formatNumber(netPay.doubleValue()),
                formatNumber(taxesPaid.doubleValue()),
                formatNumber(ytdEarnings.doubleValue()),
                formatNumber(ytdTaxesPaid.doubleValue()));
    }
}
