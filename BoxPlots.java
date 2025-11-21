import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BoxPlots {
    double calculatingMedForEvenLength(List<Double> a){
        double sum = a.get((a.size() / 2) - 1) + a.get((a.size() / 2));
        return sum / 2;
    }
    double calculatingMedForOddLength(List<Double> a){
        int med = a.size() / 2;
        return a.get(med);
    }

    public static void main(String[] args) {
        double med,q1,q3;
        BoxPlots plots = new BoxPlots();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of your data: ");
        int n = input.nextInt();
        List<Double> a = new ArrayList<>();
        List<Double> lowerThanMed = new ArrayList<>();
        List<Double> upperThanMed = new ArrayList<>();
        if (n == 0 || n == 1 || n == 2) {
            System.out.println("The number of date is less than required");
        }
        else {
            System.out.println("Enter your data: ");
            for (int i = 0; i < n; i++) {
                a.add(input.nextDouble());
            }
            Collections.sort(a);
            if (n % 2 == 0) {
                med = plots.calculatingMedForEvenLength(a);
                if (n == 4) {
                    q1 = a.getFirst();
                    q3 = a.getLast();
                } else {
                    int half = (n / 2) - 1;
                    for (int i = 0; i < half; i++) {
                        lowerThanMed.add(a.get(i));
                    }
                    q1 = plots.calculatingMedForEvenLength(lowerThanMed);
                    for (int i = half + 2; i < a.size(); i++) {
                        upperThanMed.add(a.get(i));
                    }
                    q3 = plots.calculatingMedForEvenLength(upperThanMed);
                }
            } else {
                med = plots.calculatingMedForOddLength(a);
                if (n == 3) {
                    q1 = a.getFirst();
                    q3 = a.getLast();
                } else {
                    int half = (n / 2);
                    for (int i = 0; i < half; i++) {
                        lowerThanMed.add(a.get(i));
                    }
                    q1 = plots.calculatingMedForEvenLength(lowerThanMed);
                    for (int i = half + 1; i < a.size(); i++) {
                        upperThanMed.add(a.get(i));
                    }
                    q3 = plots.calculatingMedForEvenLength(upperThanMed);
                }
            }
            System.out.println("Minimum: " + a.getFirst());
            System.out.println("Q1: " + q1);
            System.out.println("Med: " + med);
            System.out.println("Q3: " + q3);
            System.out.println("Maximum: " + a.getLast());
        }
    }
}
