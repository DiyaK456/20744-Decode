package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.HardwareNames;

public class SimpleDrive {
    DcMotor frontleft, frontright, backleft, backright;
    public SimpleDrive(HardwareMap hardwareMap) {
        frontleft = hardwareMap.get(DcMotor.class, HardwareNames.DT.fl);
        frontright = hardwareMap.get(DcMotor.class,  HardwareNames.DT.fr);
        backleft = hardwareMap.get(DcMotor.class,  HardwareNames.DT.bl);
        backright = hardwareMap.get(DcMotor.class,  HardwareNames.DT.br);
        frontright.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.REVERSE);

        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void Set(double x,double y, double pivot) {
        frontleft.setPower(y-x-pivot);
        backleft.setPower(y+x-pivot);
        frontright.setPower(y-x+pivot);
        backright.setPower(y+x+pivot);
    }
}
