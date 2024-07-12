import java.util.Scanner;

public class Employee {
    private final int employeeNumber;
    private String lastName;
    private String firstName;
    private final String position;
    private final double basicSalary;
    private double hourlyRate;
    private final double riceSubsidy;
    private final double clothingAllowance;
    private final double phoneAllowance;
    private double weeklySalary;
    private double sssDeduction;
    private double pagIbigDeduction;
    private double philhealthDeduction;
    private double incomeTaxDeduction;
    private String sssNumber;
    private String philHealthNumber;
    private String pagIbigNumber;
    private String tinNumber;

    private String startTime; // Define startTime variable
    private String endTime; // Define endTime variable

    public double calculateTotalSalary() {
        // Calculate the total salary based on basic salary, allowances, and deductions
        return basicSalary + riceSubsidy + phoneAllowance + clothingAllowance;
    }

    public Employee(int employeeNumber, String lastName, String firstName, String position, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, String sssNumber, String philHealthNumber, String pagIbigNumber, String tinNumber) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
        this.riceSubsidy = riceSubsidy;
        this.clothingAllowance = clothingAllowance;
        this.phoneAllowance = phoneAllowance;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.tinNumber = tinNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSSSNumber() {
        return sssNumber;
    }

    public void setSSSNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }

    public String getPagibigNumber() {
        return pagIbigNumber;
    }

    public void setPagibigNumber(String pagibigNumber) {
        this.pagIbigNumber = pagIbigNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public double calculateHoursWorked(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            return  0; // Return 0 hours if start time or end time is null
        }

        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");

        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);

        // Calculate total minutes worked
        int totalMinutesWorked = (endHour * 60 + endMinute) - (startHour * 60 + startMinute);

        // Convert total minutes worked to hours
        double totalHoursWorked = totalMinutesWorked / 60.0;

        return totalHoursWorked;
    }

    public void calculateWeeklySalary() {
        Scanner scanner = new Scanner(System.in);
        double totalHoursWorked = 0;

        for (int day = 1; day <= 7; day++) {
            System.out.print("Enter hours worked for day " + day + ":");
            double hoursWorked = scanner.nextDouble();
            totalHoursWorked += hoursWorked;
        }

        // Apply 10-minute grace period for each day
        totalHoursWorked -= Math.min(10*7, totalHoursWorked); // Assuming 10-minute grace period per day

        // Calculate Weekly Salary
        weeklySalary = totalHoursWorked * hourlyRate;

        // Calculate deductions
        calculateSSSDeduction(weeklySalary);

        calculatePagIbigDeduction(weeklySalary);

        calculatePhilhealthDeduction(weeklySalary);

        calculateIncomeTaxDeduction(weeklySalary);
    }

    public double calculateSSSDeduction(double salary) {
        // Define the compensation ranges and corresponding contributions in parallel arrays
        double[] compensationRanges = {3250, 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750, 10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250, 17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250, 24750, 25250};
        double[] contributions = {135.00, 157.50, 180.00, 202.50, 225.00, 247.50, 270.00, 292.50, 315.00, 337.50, 360.00, 382.50, 405.00, 427.50, 450.00, 472.50, 495.00, 517.50, 540.00, 562.50, 585.00, 607.50, 630.00, 652.50, 675.00, 697.50, 720.00, 742.50, 765.00, 787.50, 810.00, 832.50, 855.00, 877.50, 900.00, 922.50, 945.00, 967.50, 990.00, 1012.50, 1035.00, 1057.50, 1080.00, 1102.50, 1125.00};

        // Find the appropriate contribution based on the salary
        double sssContribution = 0;
        for (int i = 0; i < compensationRanges.length; i++) {
            if(salary <= compensationRanges[i]) {
                sssContribution = contributions[i];
                break;
            }
        }

        // Set a calculated SSS deduction
        return sssDeduction;
    }

    public double calculatePagIbigDeduction(double salary) {
        double[] salaryRanges = {10000, 10000.01, 60000};
        double[] employeeContributionRates = {0.01,0.02};
        double[] employerContributionRates = {0.02, 0.02};

        double totalContributionRate = 0;
        for (int i = 0; i < salaryRanges.length - 1; i++) {
            if (salary >= salaryRanges[i] && salary < salaryRanges[i + 1]) {
                totalContributionRate = employeeContributionRates[i] + employerContributionRates[i];
                break;
            }
        }

        double pagIbigDeduction = salary * totalContributionRate;

        // Set the calculated Pag-ibig Deduction
        return pagIbigDeduction;
    }

    public double calculatePhilhealthDeduction(double salary) {
        double[] salaryRanges = {10000, 60000};
        double[] premiumRates = {0.03, 0.03};
        double[] monthlyPremiumCaps = {300, 1800};

        double philhealthDeduction = 0;
        for (int i = 0; i < salaryRanges.length; i++) {
            if (salary <= salaryRanges[i]) {
                philhealthDeduction = Math.min(salary * premiumRates[i], monthlyPremiumCaps[i]);
                break;
            }
        }

        // Set the calculated Philhealth deduction
        return philhealthDeduction;
    }

    public double calculateIncomeTaxDeduction(double salary) {
        double[] salaryRanges = {20832, 20833, 33333, 66667, 166667, 666667};
        double[] taxRates = {0, 0.20, 0.25, 0.30, 0.32, 0.35};
        double[] fixedTaxAmounts = {0, 2500, 10833, 40533.33, 200833.33};

        double incomeTaxDeduction = 0;

        for (int i = 0; i < salaryRanges.length -1; i++) {
            if (salary <= salaryRanges[i]) {
                break; // No withholding tax
            } else if (salary <= salaryRanges[i + 1]) {
                incomeTaxDeduction = fixedTaxAmounts[i] + (salary - salaryRanges[i]) * taxRates[i];
                break;
            }
        }

        // Set the calculated Income Tax deduction
        return incomeTaxDeduction;
    }

    public double getNetWage() {
        double totalDeductions = sssDeduction + pagIbigDeduction + philhealthDeduction + incomeTaxDeduction;
        return weeklySalary - totalDeductions;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPosition() {
        return position;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }
}
