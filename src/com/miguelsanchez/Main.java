package com.miguelsanchez;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner (System.in);

    public static void main(String[] args) {
        int decision;
        boolean quit = false;
        showMenuZCalc();
        while (!quit) {
            System.out.print("\nYour decision: ");
            decision = scanner.nextInt();
            switch (decision) {
                case 1:
                    BigDecimal prob = probZ(getZ());
                    System.out.println("Probability of an event being smaller than Z: " + prob);
                    break;
                case 2:
                    prob = (new BigDecimal(1)).subtract(probZ(getZ()));
                    System.out.println("Probability of an event being larger than Z: " + prob);
                    break;
                case 3:
                    prob = probBetween();
                    System.out.println("Probability of an event being between two Z: " + prob);
                    break;
                case 4:
                    settingsMenu();
                    break;
                case 5:
                    showMenuZCalc();
                    break;
                default:
                    System.out.println("Impossible value, please add one of the following");
                    showMenuZCalc();
                    break;
                case 0:
                    quit=true;
            }
        }
    }

    private static void showMenuZCalc () {
        System.out.println("Calculate the probability of an event being:");
        System.out.println("1. Smaller than Z");
        System.out.println("2. Greater than Z");
        System.out.println("3. Between Z1 and Z2");
        System.out.println("4. Settings");
        System.out.println("5. Show this menu");
        System.out.println("0. Quit");
    }

    private static double getZ () {
        System.out.println("Value of Z: ");
        return scanner.nextDouble();
    }

    private static BigDecimal probZ (double Z) {
        if ((Operations.mean != 0) || (Operations.variance != 1) || (Operations.deviation != 1)) {
            double Zdef = (Z-Operations.mean)/Operations.deviation;
            return Operations.finalResult(Zdef);
        }
        return Operations.finalResult(Z);
    }

    private static BigDecimal probBetween () {
        System.out.print("Origin ");
        double Zo = getZ();
        System.out.print("Extreme ");
        double Ze = getZ();
        BigDecimal probZe = probZ(Ze);
        BigDecimal probZo = probZ(Zo);
        return probZe.subtract(probZo);
    }

    private static void settingsMenu () {
        System.out.println("What do you want to modify:");
        System.out.println("1. Maximum value of N in error function's sigma");
        System.out.println("2. Quantity of decimals");
        System.out.println("3. Value of the mean, the variance and the standard deviation");
        System.out.print("\nYour decision: ");
        int decision = scanner.nextInt();
        switch (decision) {
            case 1:
                System.out.println("Previous maximum value of N in error function's sigma: " + (Operations.N-1));
                System.out.print("New maximum value of N (it must be an integer): ");
                Operations.N = scanner.nextInt()+1;
                System.out.println("Maximum value of N changed successfully");
                break;
            case 2:
                System.out.println("Previous quantity of decimals: " + Operations.D);
                System.out.print("New quantity of decimals (must be an integer): ");
                Operations.D = scanner.nextInt();
                System.out.println("Quantity of decimals changed successfully");
                break;
            case 3:
                System.out.println("What do you want to change:");
                System.out.println("1. Mean and variance (μ and σ²)");
                System.out.println("2. Mean and standard deviation (μ and σ)");
                System.out.print("Your decision: ");
                int decision2 = scanner.nextInt();
                if ((decision2 == 1) || (decision2 == 2)) {
                    System.out.println("Previous value of the mean: " + Operations.mean);
                    System.out.print("New value of the mean: ");
                    Operations.mean = scanner.nextDouble();
                    if (decision2 == 1) {
                        System.out.println("Previous value of the variance: " + Operations.variance);
                        System.out.print("New value of the variance: ");
                        Operations.variance = scanner.nextDouble();
                        Operations.deviation = Math.sqrt(Operations.variance);
                    } else {
                        System.out.println("Previous value of the standard deviation: " + Operations.deviation);
                        System.out.print("New value of the standard deviation: ");
                        Operations.deviation = scanner.nextDouble();
                        Operations.variance = Math.pow(Operations.deviation, 2);
                    }
                    System.out.println("Values changed successfully");
                }else{
                    System.out.println("Please add a possible value");
                }
                break;
        }
    }
}
