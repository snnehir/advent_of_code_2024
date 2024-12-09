package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day1 {
    public static void main(String[] args) {
        String filePath = "day1/input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Integer> numbersInLeft = new ArrayList<>();
            List<Integer> numbersInRight = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                List<Integer> numbers = new ArrayList<>(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
                numbersInLeft.add(numbers.get(0));
                numbersInRight.add(numbers.get(1));
            }
            numbersInLeft.sort(Integer::compareTo);
            numbersInRight.sort(Integer::compareTo);

            System.out.println("[Part 1] Total distance: " + getResultPart1(numbersInLeft, numbersInRight));
            System.out.println("[Part 2] Similarity score: " + getResultPart2(numbersInLeft, numbersInRight));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getResultPart1(List<Integer> numbersInLeft, List<Integer> numbersInRight) {
        int totalDistance = 0;
        for (int i = 0; i < numbersInLeft.size(); i++) {
            totalDistance += Math.abs(numbersInRight.get(i) - numbersInLeft.get(i));
        }
        return totalDistance;
    }
    private static int getResultPart2(List<Integer> numbersInLeft, List<Integer> numbersInRight) {
        int similarityScore = 0;
        for (int i = 0; i < numbersInLeft.size(); i++) {
            int frequency = Collections.frequency(numbersInLeft, numbersInRight.get(i));
            similarityScore += (frequency * numbersInRight.get(i));
        }
        return similarityScore;
    }
}
