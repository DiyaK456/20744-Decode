package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;

public class Shooter {
    DcMotorEx motor;
    double targetVel = 0; // measured in degrees
    double errorRange = 50; // Plus or minus this amount
    double error;
    boolean shootReady = false;
    public Shooter(HardwareMap hw) {
        motor = hw.get(DcMotorEx.class, HardwareNames.shooterMotor);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
    public void SetVelocity(double velocity) {
        targetVel = velocity;
        motor.setVelocity(targetVel);
    }
    public void StartShoot() {SetVelocity(Constants.ShootSpeed);}
    public void Stop() {SetVelocity(0);motor.setPower(0);}
    public double GetTargetVelocity() {return targetVel;}
    public double GetVelocity() {return motor.getVelocity();}
    public double GetError() {return error = GetVelocity()-GetTargetVelocity();}
    public boolean UpdateShootReady() {return shootReady = Math.abs(GetError()) < errorRange && GetVelocity() > 0;}
    public boolean ShootReady() {return shootReady;}
}
