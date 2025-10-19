package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoFinder {
    HardwareMap hardwareMap;
    Gamepad gamepad;

    private Servo servo;
    double servoPos = 0;

    double cooldownEnd = 0;
    double cooldownTime = 0.2;

    double interval = 0.05;
    String name;
    ElapsedTime runTime = new ElapsedTime();
    public ServoFinder(HardwareMap hardwareMap, String servoName) {
        this.hardwareMap = hardwareMap;
        servo = hardwareMap.get(Servo.class, servoName);
        this.name = servoName;
        runTime.reset();
    }
    public void update(Gamepad gamepad, Telemetry telemetry) {
        this.gamepad = gamepad;
        double runTimeSec = runTime.seconds();
        if (gamepad.dpad_up && runTimeSec > cooldownEnd) {servoPos += interval; cooldownEnd = runTimeSec + cooldownTime;}
        if (gamepad.dpad_down && runTimeSec > cooldownEnd) {servoPos -= interval; cooldownEnd = runTimeSec + cooldownTime;}
        servo.setPosition(servoPos);
        //servoPos = Math2.removeDecimal(servoPos, 2);
        telemetry.addData((name+" Position"), servoPos);
    }
    public void setInterval(double interval) {
        this.interval = interval;
    }
}