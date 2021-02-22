package org.firstinspires.ftc.teamcode.Development.ET.SlimChassisV3;

import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PreProduction.ScrimmageTeleOp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@TeleOp(name = "Dev: ReplayMode", group = "Development")
public class ReplayMode extends ScrimmageTeleOp {

    StringBuilder waypoints = new StringBuilder();

    BufferedWriter writer;
    @Override
    public void init() {
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy_hh:mm:ss");
        String fileName = Environment.getExternalStorageDirectory().getPath() + "/waypoints/" + dateFormat.format(date) + "/";

        try {
            FileWriter write = new FileWriter(fileName);
            writer = new BufferedWriter(write);
        } catch (IOException e) {
            e.printStackTrace();
            telemetry.addData("EXCEPTION", e.toString());
        }

        super.init();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void loop() {
        super.loop();

        if(gamepad1.start || gamepad2.start) {
            waypoints.append(String.format("%f, %f, %f \n", drive.getPoseEstimate().getX(),
                    drive.getPoseEstimate().getY(), drive.getPoseEstimate().getHeading()));
        }
    }

    @Override
    public void stop() {
        try {
            writer.write(waypoints.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            telemetry.addData("EXCEPTION", e.toString());
        }
    }
}
