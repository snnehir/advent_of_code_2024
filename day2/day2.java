package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day2 {
    public static void main(String[] args) {
        String filePath = "day2/input.txt";
        int safeReportsPart1 = 0;
        int safeReportsPart2 = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> levels = new ArrayList<>(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
                safeReportsPart1 += isReportSafePart1(levels) ? 1 : 0;
                safeReportsPart2 += isReportSafePart2(levels, false) ? 1 : 0;
            }
            System.out.println("[Part 1] Safe reports: " + safeReportsPart1);
            System.out.println("[Part 2] Safe reports: " + safeReportsPart2);
        } catch (IOException e) {
            System.out.println("Input file not found");
        }
    }

    private static boolean isReportSafePart1(List<Integer> levels) {
        boolean isDesc = levels.size() > 1 && levels.get(1) - levels.get(0) < 0;

        for(int i = 0; i<levels.size()-1; i++) {
            int difference = levels.get(i+1) - levels.get(i);
            if(isNotValid(difference, isDesc)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isReportSafePart2(List<Integer> levels, boolean isAnyLevelRemoved) {
        boolean isDesc = levels.size() > 1 && levels.get(1) - levels.get(0) < 0;

        for(int i = 0; i<levels.size()-1; i++) {
            int difference = levels.get(i+1) - levels.get(i);
            if(isNotValid(difference, isDesc)) {
                if(isAnyLevelRemoved){
                    return false;
                }
                List<Integer> currentLevelRemovedList = removeAtIndexAndReturnList(levels, i);
                List<Integer> nextLevelRemovedList = removeAtIndexAndReturnList(levels, i+1);
                boolean isSafe = isReportSafePart2(currentLevelRemovedList, true) || isReportSafePart2(nextLevelRemovedList, true);

                if(i == 1){
                    List<Integer> firstLevelRemovedList = removeAtIndexAndReturnList(levels, 0);
                    isSafe = isSafe || isReportSafePart2(firstLevelRemovedList, true);
                }

                return isSafe;
            }
        }
        return true;
    }

    private static List<Integer> removeAtIndexAndReturnList(List<Integer> numbers, int index) {
        ArrayList<Integer> newList = new ArrayList<>(numbers);
        newList.remove(index);
        return newList;
    }

    private static boolean isNotValid(int difference, boolean isDesc) {
        boolean isCurrentDesc = difference < 0;
        return Math.abs(difference) < 1 || Math.abs(difference) > 3 || isCurrentDesc != isDesc;
    }
}
