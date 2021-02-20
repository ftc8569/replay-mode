package com.roboknights;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedWriter writer = null;
        ArrayList<String> wayPoints = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        File input = new File(args[0]);
        try {
            FileWriter write = new FileWriter(args[1]);
            writer = new BufferedWriter(write);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                wayPoints.add(data);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int counter = 1;

        for(String str : wayPoints) {

            List<String> coords = Arrays.asList(str.split(",[ ]*"));
            if(counter == 1) {
                output.append("TODO: set start position to be the same as in your teleop \n");
                output.append("Pose2d startPos = new Pose2d(X, X, Math.toRadians(X); \n \n");
                output.append("drive.setPoseEstimate(startPos); \n");
                output.append(String.format("Trajectory traj%s = new TrajectoryBuilder(startPos) \n" +
                        "   .lineToLinearHeading(new Pose2d(%s, %s, %s)) \n" +
                        "   .build(); \n", counter, coords.get(0), coords.get(1), coords.get(2)));
            } else output.append(String.format("Trajectory traj%s = new TrajectoryBuilder(traj%s.end()) \n" +
                    "   .lineToLinearHeading(new Pose2d(%s, %s, %s)) \n" +
                    "   .build(); \n", counter, counter - 1, coords.get(0), coords.get(1), coords.get(2)));

            counter++;
        }

        output.append("\n\n");

        for(int i = 1; i <= wayPoints.size(); i++) {
            output.append(String.format("drive.followTrajectory(traj%s); \n", i));
        }

        assert writer != null;
        writer.write(output.toString());
        writer.close();

    }

}
