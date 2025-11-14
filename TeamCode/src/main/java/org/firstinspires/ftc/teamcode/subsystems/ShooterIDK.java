package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;

public class ShooterIDK {
    DcMotorEx motor, motor2;
    double targetVel = 0; // measured in degrees
    //double errorRange = 15; // Plus or minus this amount
    double error;
    boolean shootReady = false;
    public ShooterIDK(HardwareMap hw) { // What is the purpose of this
        motor = hw.get(DcMotorEx.class, HardwareNames.shooterMotor);
        motor2 = hw.get(DcMotorEx.class, HardwareNames.shooterMotor2);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void SetVelocity(double velocity) {
        targetVel = velocity;
        motor.setVelocity(targetVel, AngleUnit.DEGREES);
        motor2.setVelocity(targetVel, AngleUnit.DEGREES);
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
