import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BoxPlots {
    double calculateQ1(List<Double> inputs) {
        int element = (int) ((inputs.size() + 1) * (0.25) + 0.75);
        return inputs.get(element - 1) + ((inputs.get(element) - inputs.get(element - 1)) * 0.25);
    }

    double calculateQ3(List<Double> inputs) {
        int element = (int) ((inputs.size() + 1) * (0.75) + 0.5);
        return inputs.get(element - 2) + ((inputs.get(element - 1) - inputs.get(element - 2)) * 0.75);
    }

    double calculateLowOutLier(double q1, double q3) {
        double iqr = q3 - q1;
        return q1 - (1.5 * iqr);
    }

    double calculateHighOutLier(double q1, double q3) {
        double iqr = q3 - q1;
        return q3 + (1.5 * iqr);
    }

    double calculatingMedForEvenLength(List<Double> inputs) {
        double sum = inputs.get((inputs.size() / 2) - 1) + inputs.get((inputs.size() / 2));
        return sum / 2;
    }

    double calculatingMedForOddLength(List<Double> inputs) {
        return inputs.get(inputs.size() / 2);
    }

    double min(List<Double> inputs) {
        double lowOut = calculateLowOutLier(calculateQ1(inputs), calculateQ3(inputs));
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) > lowOut) {
                return inputs.get(i);
            }
        }
        return inputs.getFirst();
    }

    double max(List<Double> inputs) {
        double highOut = calculateHighOutLier(calculateQ1(inputs), calculateQ3(inputs));
        double max = inputs.getLast();
        if (inputs.getLast() < highOut)
            return inputs.getLast();
        else {
            for (int i = inputs.size() / 2; i < inputs.size(); i++) {
                if (inputs.get(i) < highOut)
                    max = inputs.get(i);
                else
                    return max;
            }
        }
        return -1;
    }

    void invalidInputs() {
        System.out.println("invalid input");
    }

    List input() {
        int n = 0;
        List<Double> inputs = new ArrayList<>();
        BoxPlots plots = new BoxPlots();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of your data: ");
        try {
            n = input.nextInt();
            if (n <= 2) {
                invalidInputs();
                return inputs;
            }
                else {
                System.out.println("Enter your data: ");
                for (int i = 0; i < n; i++) {
                    inputs.add(input.nextDouble());
                }
            }
        }
        catch (Exception e) {
            plots.invalidInputs();
        }
        Collections.sort(inputs);
        return inputs;
    }

    void boxPlots(List<Double> inputs) {
        double med, q1, q3;
        int n = inputs.size();
        BoxPlots plots = new BoxPlots();
            if (n % 2 == 0)
                med = plots.calculatingMedForEvenLength(inputs);
            else
                med = plots.calculatingMedForOddLength(inputs);
            q1 = plots.calculateQ1(inputs);
            q3 = plots.calculateQ3(inputs);
            double lowOutLier = plots.calculateLowOutLier(q1, q3);
            double highOutLier = plots.calculateHighOutLier(q1, q3);
            System.out.println("Minimum: " + plots.min(inputs));
            System.out.println("Q1: " + q1);
            System.out.println("Med: " + med);
            System.out.println("Q3: " + q3);
            System.out.println("Maximum: " + plots.max(inputs));
            System.out.println("Low Outlier: " + lowOutLier);
            System.out.println("High Outlier: " + highOutLier);
    }


    public static void main(String[] args) {
        BoxPlots plots = new BoxPlots();
        List<Double> inputs= plots.input();
        if(inputs.size() > 2)
            plots.boxPlots(inputs);
    }
}
