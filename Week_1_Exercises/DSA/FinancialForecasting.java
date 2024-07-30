public class FinancialForecasting {
    public static double computeForecast(double principal, double interestRate, int timePeriods) {
        if (timePeriods == 0) {
            return principal;
        }
        return computeForecast(principal * (1 + interestRate), interestRate, timePeriods - 1);
    }

    public static void main(String[] args) {
        double principal = 1000.0;
        double interestRate = 0.05; // 5% growth rate
        int timePeriods = 10;
        double futureValue = computeForecast(principal, interestRate, timePeriods);
        System.out.println("Projected Future Value: $" + futureValue);
    }
}
