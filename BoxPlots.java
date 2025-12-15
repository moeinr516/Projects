import java.util.*;
public class BoxPlots {
    private double calculateQ1(List<Double> inputs) {
        double element =  (inputs.size() + 1) * (0.25) + 0.5;
        int distance = (int) element;
        double p = element - distance;
        return inputs.get(distance - 1) + ((inputs.get(distance) - inputs.get(distance - 1)) * p);
    }

    private double calculateQ3(List<Double> inputs) {
        double element =  inputs.size() * (0.75) + 0.25;
        int distance = (int) element;
        double p = element - distance;
        return inputs.get(distance - 1) + ((inputs.get(distance) - inputs.get(distance - 1)) * p);
    }

    private double calculateLowOutLier(double q1, double q3) {
        double iqr = q3 - q1;
        return q1 - (1.5 * iqr);
    }

    private double calculateHighOutLier(double q1, double q3) {
        double iqr = q3 - q1;
        return q3 + (1.5 * iqr);
    }

   private double calculatingMedForEvenLength(List<Double> inputs) {
        double sum = inputs.get((inputs.size() / 2) - 1) + inputs.get((inputs.size() / 2));
        return sum / 2;
    }

    private double calculatingMedForOddLength(List<Double> inputs) {
        return inputs.get(inputs.size() / 2);
    }

    private double min(List<Double> inputs) {
        double lowOut = calculateLowOutLier(calculateQ1(inputs), calculateQ3(inputs));
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) >= lowOut) {
                return inputs.get(i);
            }
        }
        return inputs.getFirst();
    }

    private double max(List<Double> inputs) {
        double highOut = calculateHighOutLier(calculateQ1(inputs), calculateQ3(inputs));
        double max = inputs.getLast();
        if (inputs.getLast() <= highOut)
            return inputs.getLast();
        else {
            for (int i = inputs.size() / 2; i < inputs.size(); i++) {
                if (inputs.get(i) <= highOut)
                    max = inputs.get(i);
                else
                    return max;
            }
        }
        return -1;
    }

    public void invalidInputs() {
        System.out.println("invalid input");
    }

    public ArrayList input() {
        int n = 0;
        ArrayList<Double> inputs = new ArrayList<>();
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

    protected void boxPlots(List<Double> inputs) {
        double med, q1, q3;
        int n = inputs.size();
        BoxPlots plots = new BoxPlots();
        HashMap <String,Double> boxplots = new HashMap<String,Double>();
            if (n % 2 == 0)
                med = plots.calculatingMedForEvenLength(inputs);
            else
                med = plots.calculatingMedForOddLength(inputs);
            q1 = plots.calculateQ1(inputs);
            q3 = plots.calculateQ3(inputs);
            double lowOutLier = plots.calculateLowOutLier(q1, q3);
            double highOutLier = plots.calculateHighOutLier(q1, q3);
            boxplots.put("Low Outlier ", lowOutLier);
            boxplots.put("Minimum ", plots.min(inputs));
            boxplots.put("Q1 ", q1);
            boxplots.put("med ", med);
            boxplots.put("Q3 ", q3);
            boxplots.put("Maximum ", plots.max(inputs));
            boxplots.put("High Outlier ", highOutLier);
        System.out.println(boxplots);
    }


    public static void main(String[] args) {
        BoxPlots plots = new BoxPlots();
        List<Double> inputs= plots.input();
        if(inputs.size() > 2)
            plots.boxPlots(inputs);
    }
}
