package com.deadlineMe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class deadlineMethods {
    // Scanner and Important Variables
    public static Scanner scanner = new Scanner(System.in);
    public static int menuAnswer = 0;
    public static ArrayList<String> dataName = new ArrayList<String>();
    public static ArrayList<Integer> dataMonth = new ArrayList<Integer>();
    public static ArrayList<Integer> dataDay = new ArrayList<Integer>();
    public static ArrayList<Integer> dataHour = new ArrayList<Integer>();
    public static ArrayList<String> dataStatus = new ArrayList<String>();

    // Methods | Functions
    public static int menuStart(){
        /*
        The start menu (This is where the user is greeted by the system to choose which task will be done)
         */
        System.out.println("\n\t\t[ TASKS ]");
        System.out.println("\t> \"ADD\" DEADLINE");
        System.out.println("\t> \"CHECK\" DEADLINE(S)");
        System.out.println("\t> \"END\" PROGRAM");

        String value = "";

        while (true) {
            System.out.print("\nPlease enter a task: ");
            value = scanner.next();
            value.toLowerCase();
            if (value.equals("add"))
                return menuAnswer = 0;
            else if (value.equals("check"))
                return menuAnswer = 1;
            else if (value.equals("end"))
                System.exit(0);
            else
                System.out.println("[ERROR] Please enter \"ADD\", \"CHECK\" or \"END\" only.");
        }
    }

    public static int addDeadline(){
        /*
        The add deadline task (This is where the user adds a deadline for the system to monitor)
         */
        System.out.println("\n\t\t[ADD DEADLINE]");
        System.out.print("> SUBJECT NAME: ");
        String input = scanner.nextLine();
        String name = scanner.nextLine();

        int month;
        int day;
        int hour;

        while (true){
            System.out.print("> DEADLINE-MONTH: ");
            month = scanner.nextByte();
            if (month >= 1 && month <= 12)
                break;
            else
                System.out.println("Please enter a month properly (1:January - 12:December)");
        }
        while (true){
            System.out.print("> DEADLINE-DAY: ");
            day = scanner.nextByte();
            if (day >= 1 && day <= 31)
                break;
            else
                System.out.println("Please enter a day properly (1-31)");
        }
        while (true){
            System.out.print("> DEADLINE-HOUR (24Hrs Format): ");
            hour = scanner.nextByte();
            if (hour >= 1 && hour <= 24)
                break;
            else
                System.out.println("Please enter the hour properly (1:1am - 24:12am)");
        }

        //Adding given data to its corresponding array.
        dataName.add(name.toUpperCase().trim());
        dataMonth.add(month);
        dataDay.add(day);
        dataHour.add(hour);
        dataStatus.add("PENDING");

        System.out.println("\n[FINISH ADDING]");

        while (true){
            System.out.print("\nDo you wish to add more? (Y/N): ");
            String taskAddAnswer = scanner.next();
            taskAddAnswer.toLowerCase();
            if (taskAddAnswer.equals("y"))
                return 0;
            else if (taskAddAnswer.equals("n"))
                return 2;
            else
                System.out.println("[ERROR] Please enter either (Y: Yes or N: No)");
        }
    }

    public static int checkDeadline(){
        /*
        The check deadline task (This is where the user can interact and monitor the deadline/s)
         */
        // DATES - Months with their total hours
        int january = 744;
        int february = 672;
        int leap_february = 696;
        int march = 744;
        int april = 720;
        int may = 744;
        int june = 720;
        int july = 744;
        int august = 744;
        int september = 720;
        int october = 744;
        int november = 720;
        int december = 744;

        ArrayList<Integer> NORM_YEAR = new ArrayList<Integer>();
        NORM_YEAR.add(january);
        NORM_YEAR.add(february);
        NORM_YEAR.add(march);
        NORM_YEAR.add(april);
        NORM_YEAR.add(may);
        NORM_YEAR.add(june);
        NORM_YEAR.add(july);
        NORM_YEAR.add(august);
        NORM_YEAR.add(september);
        NORM_YEAR.add(october);
        NORM_YEAR.add(november);
        NORM_YEAR.add(december);
        ArrayList<Integer> LEAP_YEAR = new ArrayList<Integer>();
        LEAP_YEAR.add(january);
        LEAP_YEAR.add(leap_february);
        LEAP_YEAR.add(march);
        LEAP_YEAR.add(april);
        LEAP_YEAR.add(may);
        LEAP_YEAR.add(june);
        LEAP_YEAR.add(july);
        LEAP_YEAR.add(august);
        LEAP_YEAR.add(september);
        LEAP_YEAR.add(october);
        LEAP_YEAR.add(november);
        LEAP_YEAR.add(december);

        // OTHER VARIABLES
        final int HOURS_IN_A_DAY = 24;
        final int MINUTES_IN_A_HOUR = 60;
        int deadline = 0;
        int time_difference;
        int output_month = 0;
        int output_day;
        int output_hour;
        int output_minutes;
        int calculations = 0;

        Calendar present = Calendar.getInstance();
        int present_month = present.get(Calendar.MONTH);
        int present_day = present.get(Calendar.DAY_OF_MONTH);
        int present_hour = present.get(Calendar.HOUR_OF_DAY);
        int present_minutes = present.get(Calendar.MINUTE);
        int present_time = 0;

        // MANUAL CHECK IF LEAP YEAR
        String input_LeapYear = "";
        while (true){
            System.out.print("\n[MANUAL CHECK] Is it a leap year? (Y/N): ");
            input_LeapYear = scanner.next();
            if (input_LeapYear.toLowerCase().equals("y") || input_LeapYear.toLowerCase().equals("yes") ||
                    input_LeapYear.toLowerCase().equals("n") || input_LeapYear.toLowerCase().equals("no")){
                break;
            }
            else
                System.out.println("[ERROR] Please enter either (Y: Yes or N: No)");
        }

        System.out.println("\n\t\t\t\t[CHECK DEADLINE(S)]");
        System.out.println(">SUBJECT<\t\t\t>DEADLINE<\t\t\t\t\t\t\t>STATUS<");

        for (int i = 0; i < dataName.size(); i++){
            // CALCULATIONS
            // Data Month will be deducted by 1 for proper indexing
            if (input_LeapYear.toLowerCase().equals("y") || input_LeapYear.toLowerCase().equals("yes")) {
                for (int j = 0; j < (dataMonth.get(i) - 1); j++) {
                    deadline = deadline + LEAP_YEAR.get(j);
                }
                deadline = deadline + (dataDay.get(i) * HOURS_IN_A_DAY);
                deadline = deadline + dataHour.get(i);

                for (int j = 0; j < present_month; j++) {
                    present_time = present_time + LEAP_YEAR.get(j);
                }
                present_time = present_time + (present_day * HOURS_IN_A_DAY);
                present_time = present_time + present_hour;

                time_difference = deadline - present_time;
                for (int j = (present_month + 1); j < (dataMonth.get(i) - 1); j++) {
                    calculations = calculations + LEAP_YEAR.get(j);
                    output_month = output_month += 1;
                }

                output_day = (time_difference - calculations) / HOURS_IN_A_DAY;
                output_hour = ((time_difference - calculations) % HOURS_IN_A_DAY) - 1; //AN HOUR LATE
                output_minutes = MINUTES_IN_A_HOUR - present_minutes;

                if (output_hour <= -1 && dataStatus.get(i).equals("PENDING"))
                    System.out.println(dataName.get(i) + "\t\t\t\t---:---:---" + "\t\t\t\t\t\t\tOVERDUE");
                else
                    System.out.println(dataName.get(i) + "\t\tMONTHS:" + output_month + "|DAYS:" +
                            output_day + "|HOURS:" + output_hour + "|MINS:" + output_minutes + "\t\t\t\t" +
                            dataStatus.get(i));

                deadline = 0;
                output_month = 0;
                calculations = 0;
                present_time = 0;

            }
            else if (input_LeapYear.toLowerCase().equals("n") || input_LeapYear.toLowerCase().equals("no")) {
                for (int j = 0; j < (dataMonth.get(i) - 1); j++) {
                    deadline = deadline + NORM_YEAR.get(j);
                }
                deadline = deadline + (dataDay.get(i) * HOURS_IN_A_DAY);
                deadline = deadline + dataHour.get(i);

                for (int j = 0; j < present_month; j++) {
                    present_time = present_time + NORM_YEAR.get(j);
                }
                present_time = present_time + (present_day * HOURS_IN_A_DAY);
                present_time = present_time + present_hour;

                time_difference = deadline - present_time;
                for (int j = (present_month + 1); j < (dataMonth.get(i) - 1); j++) {
                    calculations = calculations + NORM_YEAR.get(j);
                    output_month = output_month += 1;
                }

                output_day = (time_difference - calculations) / HOURS_IN_A_DAY;
                output_hour = ((time_difference - calculations) % HOURS_IN_A_DAY) - 1; //AN HOUR LATE
                output_minutes = MINUTES_IN_A_HOUR - present_minutes;

                if (output_hour <= -1 && dataStatus.get(i).equals("PENDING"))
                    System.out.println(dataName.get(i) + "\t\t\t\t---:---:---" + "\t\t\t\t\t\t\tOVERDUE");
                else
                    System.out.println(dataName.get(i) + "\t\tMONTHS:" + output_month + "|DAYS:" +
                            output_day + "|HOURS:" + output_hour + "|MINS:" + output_minutes + "\t\t\t\t" +
                            dataStatus.get(i));

                deadline = 0;
                output_month = 0;
                calculations = 0;
                present_time = 0;
            }
        }

        // Alteration of subject status
        byte finished = 1;
        while(finished == 1){
            System.out.print("\nDo you wish to alter the status of the subjects? (Y/N): ");
            String alter = scanner.next();
            if (alter.toLowerCase().equals("y")){

                int num = 1;
                for (int i = 0; i < dataName.size(); i++) {
                    System.out.println(num + i + ".\t" + dataName.get(i));
                }
                int pick;
                while (true){
                    System.out.print("\nWhich subject do you wish to alter? (Enter number): ");
                    pick = scanner.nextInt();
                    pick = pick - 1;
                    try {
                        dataName.get(pick);
                        break;
                    } catch (Exception e) {
                        System.out.println("[ERROR] Subject does not exist");
                    }
                }
                while (true){
                    System.out.print("Do you wish to change the current status of " + dataName.get(pick) +
                            "? (Y/N): ");
                    String change = scanner.next();
                    if (change.toLowerCase().equals("y") && dataStatus.get(pick).equals("PENDING")){
                        dataStatus.set(pick, "FINISHED");
                        finished = 0;
                        break;
                    }
                    else if (change.toLowerCase().equals("y") && dataStatus.get(pick).equals("FINISHED")){
                        dataStatus.set(pick, "PENDING");
                        finished = 0;
                        break;
                    } else if (change.toLowerCase().equals("n")){
                        finished = 0;
                        break;
                    } else
                        System.out.println("[ERROR] Please enter either (Y: Yes or N: No)");
                }
            } else if (alter.toLowerCase().equals("n")){
                return 2;
            } else
                System.out.println("[ERROR] Please enter either (Y: Yes or N: No)");
        }
        while (true){
            System.out.print("Do you wish to repeat task? (Y/N): ");
            String taskCheckAnswer = scanner.next();
            if (taskCheckAnswer.toLowerCase().equals("y"))
                return 1;
            else if (taskCheckAnswer.toLowerCase().equals("n"))
                return 2;
            else
                System.out.println("[ERROR] Please enter either (Y: Yes or N: No)");
        }
    }
}
