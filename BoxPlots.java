import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BoxPlots {
    double evenOrOdd(List<Double> a){
        if (a.size() % 2 == 0)
           return calculatingMedForEvenLength(a);
        else
            return calculatingMedForOddLength(a);
    }
    double calculatingMedForEvenLength(List<Double> a){
        double sum = a.get((a.size() / 2) - 1) + a.get((a.size() / 2));
        return sum / 2;
    }
    double calculatingMedForOddLength(List<Double> a){
        int med = a.size() / 2;
        return a.get(med);
    }
    List<Double> subList(List<Double> a, int first, int last){
        List<Double> sub = new ArrayList<>();
        for (int i = first; i < last; i++) {
            sub.add(a.get(i));
        }
        return sub;
    }

    public static void main(String[] args) {
        double med, q1, q3;
        List<Double> a = new ArrayList<>();
        BoxPlots plots = new BoxPlots();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of your data: ");
        int n = input.nextInt();
        System.out.println("Enter your data: ");
        for (int i = 0; i < n; i++) {
            a.add(input.nextDouble());
        }

        Collections.sort(a);
        List<Double> lowerThanMed = new ArrayList<>();
        List<Double> upperThanMed = new ArrayList<>();
        int middle = n / 2;
        if (n % 2 == 0)
            lowerThanMed = plots.subList(a, 0, middle - 1);
        else
            lowerThanMed = plots.subList(a, 0, middle);
        try {
            upperThanMed = plots.subList(a, middle + 1, n);
            med = plots.evenOrOdd(a);
            q1 = plots.evenOrOdd(lowerThanMed);
            q3 = plots.evenOrOdd(upperThanMed);
            System.out.println("Minimum: " + a.getFirst());
            System.out.println("Q1: " + q1);
            System.out.println("Med: " + med);
            System.out.println("Q3: " + q3);
            System.out.println("Maximum: " + a.getLast());
        }
        catch (Exception e) {
            System.out.println("The number of date is less than required");
        }
    }
}