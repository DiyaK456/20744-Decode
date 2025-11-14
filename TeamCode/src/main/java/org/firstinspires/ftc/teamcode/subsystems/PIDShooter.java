package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.config.Constants.kD;
import static org.firstinspires.ftc.teamcode.config.Constants.kI;
import static org.firstinspires.ftc.teamcode.config.Constants.kP;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;

public class PIDShooter {
    DcMotorEx motor, motor2;
    double targetVel = 0; // measured in degrees
    //double errorRange = 15; // Plus or minus this amount
    double error;
    boolean shootReady = false;

    private PIDController pid;
    private double targetVelocity = 0.0; // target in degrees/sec
    private double currentVelocityTicks = 0.0; // ticks/sec
    private double currentVelocityDegrees = 0.0; // deg/sec
    private int lastPosition = 0;
    private boolean isBusy = false;
    private double ticksPerRev = 28; // example for Neverest 40 motor

    private ElapsedTime timer = new ElapsedTime();


    public PIDShooter(HardwareMap hw) { // Gang what is ts 🥀
        pid = new PIDController(kP,kI,kD);

        motor = hw.get(DcMotorEx.class, HardwareNames.shooterMotor);
        motor2 = hw.get(DcMotorEx.class, HardwareNames.shooterMotor2);

        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        timer.reset();
        lastPosition = motor.getCurrentPosition();
    }
//
    double deltaTime = 0;
    public double deltaTimeSeconds() {
        return deltaTime;
    }


    public double getRawVelocityDeg() {
        return motor.getVelocity(AngleUnit.DEGREES);
    }


    public void SetVelocity(double velocity) {

        pid.setPID(kP, kI, kD);

        targetVel = velocity;
//        motor.setVelocity(targetVel, AngleUnit.DEGREES);
//        motor2.setVelocity(targetVel, AngleUnit.DEGREES);

        double power = pid.calculate(getRawVelocityDeg(), targetVel);
        motor.setPower(power);
        motor2.setPower(power);
    }

    public void StartShoot() {SetVelocity(Constants.ShootSpeed);}

    public void Stop() {SetVelocity(0);motor.setPower(0);motor2.setPower(0);}
    public double GetTargetVelocity() {return targetVel;}
    public double GetMotorTargetVel() {return (motor.getTargetPosition() + motor2.getTargetPosition())/2d;}
    public double GetVelocity() {return Math.abs(motor.getVelocity(AngleUnit.DEGREES) + motor2.getVelocity(AngleUnit.DEGREES))/2d;}
    public double GetError() {return error = GetVelocity()-GetTargetVelocity();}
    public boolean UpdateShootReady() {return shootReady = Math.abs(GetError()) < Constants.ShooterErrorRange && GetVelocity() > targetVel/1.5;}
    public boolean ShootReady() {return shootReady;}
    public boolean Shooting() {return GetVelocity() > 0;}
}
