package org.firstinspires.ftc.teamcode.hardware;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MotorVelocityController {

    private DcMotorEx motor;
    private PIDController pid;
    private double targetVelocity = 0.0; // target in degrees/sec
    private double currentVelocityTicks = 0.0; // ticks/sec
    private double currentVelocityDegrees = 0.0; // deg/sec
    private int lastPosition = 0;
    private boolean isBusy = false;
    private double ticksPerRev = 28; // example for Neverest 40 motor

    private ElapsedTime timer = new ElapsedTime();

    public MotorVelocityController(HardwareMap hw, String motorName) {
        motor = hw.get(DcMotorEx.class, motorName);
        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        PIDFCoefficients rawCoef = motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        pid = new PIDController(rawCoef.p,rawCoef.i,rawCoef.d);

        timer.reset();
        lastPosition = motor.getCurrentPosition();
    }

    public void setTargetVelocity(double targetDegPerSec) {
        targetVelocity = targetDegPerSec;
        isBusy = true;
    }

    public void stop() {
        motor.setPower(0);
        targetVelocity = 0.0;
        isBusy = false;
    }

//    public double getVelocityDegrees() {
//        return currentVelocityDegrees;
//    }

    public double getVelocityDegrees() {
        return motor.getVelocity(AngleUnit.DEGREES);
    }

    public double getVelocityTicks() {
        return currentVelocityTicks;
    }

    public int getPosTicks() {
        return motor.getCurrentPosition();
    }

    public void setTicksPerRevolution(double ticksPerRev) {
        this.ticksPerRev = ticksPerRev;
    }

    double deltaTime = 0;
    public double deltaTimeSeconds() {
        return deltaTime;
    }

    public void update() {
        deltaTime = timer.seconds(); // time since last update
        timer.reset();

        if (deltaTime <= 0) return; // avoid divide-by-zero

        int currentPosition = motor.getCurrentPosition();
        int deltaPosition = currentPosition - lastPosition;
        lastPosition = currentPosition;

        // ticks per second
        currentVelocityTicks = deltaPosition / deltaTime;

        // convert ticks/sec → degrees/sec
        currentVelocityDegrees = (currentVelocityTicks / ticksPerRev) * 360.0;

        // Apply target velocity logic (e.g., open-loop control)
        if (isBusy) {
            double power = pid.calculate(getVelocityDegrees(), targetVelocity);
            motor.setPower(power);
        }
    }

//    private double targetVelocityToPower(double targetDegPerSec) {
//        // Example: assume max velocity corresponds to 360 deg/sec
//        double maxVelocityDegPerSec = 360.0;
//        double power = targetDegPerSec / maxVelocityDegPerSec;
//        return Math.max(-1.0, Math.min(1.0, power)); // clamp to [-1, 1]
//    }

    public boolean isBusy() {
        return isBusy;
    }
}
