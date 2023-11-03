package com.javasource.task_01.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindNumber{
    static final String NUMBER_PATTERN = "\\d+";
    static final String PATH_PATTERN = "[\\\\/\\w\\d_.-]+";

    public static void main(String[] args) {
        String inputSourceFromArgs0 = args[0];
        String inputSourceFromArgs1 = args[1];

        List<Integer> numbersFromClient = readNumbers(inputSourceFromArgs0);

        List<Integer> numbersEvenOrOdd = filterNumbers(numbersFromClient);

        writeNumbers(inputSourceFromArgs1, numbersEvenOrOdd);
    }

    private static List<Integer> readNumbers(String source) {
        List<Integer> numbers = new ArrayList<>();
        Scanner scanner = null;

        try {
            if (source.matches(NUMBER_PATTERN)) {
                scanner = new Scanner(System.in);
            } else if (source.matches(PATH_PATTERN)) {
                scanner = new Scanner(new File(source));
            }

            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                numbers.add(num);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + source);
            return null;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return numbers;
    }

    private static void writeNumbers(String destination, List<Integer> numbers) {
        if (destination == null) {
            for (int num : numbers) System.out.println(num);
        } else if (destination.matches(PATH_PATTERN)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(destination))) {
                for (int num : numbers) {
                    writer.println(num);
                }
            } catch (IOException e) {
                System.out.println("Failed to write to the output file: " + destination);
            }
        }
    }

    private static List<Integer> filterNumbers(List<Integer> numbers) {
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        boolean evenCount = numbers.size() % 2 == 0;

        for (int num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            } else {
                oddNumbers.add(num);
            }
        }
        if (evenCount) return evenNumbers;
        else return oddNumbers;
    }
}
