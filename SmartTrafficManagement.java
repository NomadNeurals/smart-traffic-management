package hackathon;

import java.util.Scanner;

public class SmartTrafficManagement {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            String[] roads = {"North", "South", "East", "West"};
            int[] count = new int[4];

            System.out.print("Enter vehicle count for North: ");
            count[0] = sc.nextInt();

            System.out.print("Enter vehicle count for South: ");
            count[1] = sc.nextInt();

            System.out.print("Enter vehicle count for East: ");
            count[2] = sc.nextInt();

            System.out.print("Enter vehicle count for West: ");
            count[3] = sc.nextInt();

            // If no vehicles at all
            int total = count[0] + count[1] + count[2] + count[3];
            if (total == 0) {
                System.out.println("\nNo vehicles detected. All signals stay Red.");
                return;
            }

            // Fixed slot times by priority (highest to lowest)
            int[] slots = {40, 30, 20, 10}; // total = 100s
            int[] time = new int[4];        // final times per road
            int[] order = new int[4];       // order[0] = index of highest-density road

            boolean[] used = new boolean[4];
            for (int s = 0; s < 4; s++) {
                int bestIdx = -1, bestVal = -1;
                for (int i = 0; i < 4; i++) {
                    if (!used[i] && count[i] > bestVal) {
                        bestVal = count[i];
                        bestIdx = i;
                    }
                }
                used[bestIdx] = true;
                order[s] = bestIdx;
                time[bestIdx] = slots[s];
            }

            // Output
            System.out.println("\n🚦 Smart Traffic Signal Timings (Total cycle: 100s) 🚦");
            for (int i = 0; i < 4; i++) {
                System.out.println(roads[i] + " → " + time[i] + "s Green  (" + count[i] + " vehicles)");
            }

            System.out.println("\nPriority / Cycle order:");
            for (int s = 0; s < 4; s++) {
                int i = order[s];
                System.out.println((s + 1) + ". " + roads[i] + " (" + count[i] + " vehicles) → " + slots[s] + "s");
            }
        }
    }
}