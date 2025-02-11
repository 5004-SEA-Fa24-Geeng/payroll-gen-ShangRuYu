package student;

/** 
 * This is a static class (essentially functions) that will help you build objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {
    
    private Builder() {
    }
    /**
     * Helper method to check if the string can be parsed as a valid double.
     * If not, throws an IllegalArgumentException.
     *
     * @param value the string value
     * @param field the field name for debugging
     * @return the parsed double value
     */
    private static double checkValue(String value, String field) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for " + field + ": " + value);
        }
    }

     /**
     * Builds an employee object from a CSV string.
     * 
     * You may end up checking the type of employee (hourly or salary) by looking at the first
     * element of the CSV string. Then building an object specific to that type.
     * 
     * @param csv the CSV string
     * @return the employee object
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid CSV format for Employee");
        }

        String employeeType = parts[0].trim();
        String name = parts[1].trim();
        String id = parts[2].trim();
        double payRate = Double.parseDouble(parts[3].trim());
        double pretaxDeductions = Double.parseDouble(parts[4].trim());
        double ytdEarnings = Double.parseDouble(parts[5].trim());
        double ytdTaxesPaid = Double.parseDouble(parts[6].trim());

        if (employeeType.equals("HOURLY")) {
            return new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        } else if (employeeType.equals("SALARY")) {
            return new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        }

        throw new IllegalArgumentException("Invalid employee type: " + employeeType);
    }
    /**
      * Converts a TimeCard from a CSV String.
      *
      * @param csv csv string
      * @return a TimeCard object
      */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        int columnNum = 2;
        String[] parts = csv.split(",");

        if (parts.length != columnNum) {
            throw new IllegalArgumentException("Invalid CSV format for TimeCard, expected " + columnNum + " columns.");
        }

        String stringId = parts[0];
        String stringHoursWorked = parts[1];

        double hoursWorked = 0;
        try {
            hoursWorked = Double.parseDouble(stringHoursWorked);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hoursWorked value for TimeCard: " + stringHoursWorked);
        }

        return new TimeCard(stringId, hoursWorked);
    }
}

