package week1;
import java.util.*;
public class BMICalculator
{
    static String getBmiStatus(double bmi)
    {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }
    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.printf("%-10s %-15s %-15s %-10s %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");

        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            String status = getBmiStatus(bmi);

            System.out.printf("%-10d %-15.2f %-15.2f %-10.2f %-15s%n",
                    i + 1, heights[i], weights[i], bmi, status);
        }
    }

    public static void main(String[] args) {

        int n = 10;

        double[] heights = new double[n];
        double[] weights = new double[n];

        Random r = new Random();

        for (int i = 0; i < n; i++) {
            heights[i] = 1.50 + r.nextDouble() * 0.40; // 1.50 - 1.90 m
            weights[i] = 45 + r.nextDouble() * 65;      // 45 - 110 kg
        }

        printWellnessReport(heights, weights);
    }
}