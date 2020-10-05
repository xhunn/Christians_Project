package com.deadlineMe;

public class Main {

    public static void main(String[] args) {
        while (true){
            System.out.println("\n\n\n\tWELCOME TO DEADLINE.ME");

            // Menu Start and answer variables
            int menuAnswer = deadlineMethods.menuStart();
            int taskAnswer = -1;

            // Loop (taskAnswer: 0 - REPEAT TASK (ADD), 1 - REPEAT TASK (CHECK), 2 - RETURN TO MAIN MENU)
            while (taskAnswer != 2){
                // Task chosen (menuAnswer: 0 - ADD DEADLINE, menuAnswer: 1 CHECK DEADLINE)
                if (menuAnswer == 0)
                    taskAnswer = deadlineMethods.addDeadline();
                else if (menuAnswer == 1)
                    taskAnswer = deadlineMethods.checkDeadline();
            }
        }
    }
}
