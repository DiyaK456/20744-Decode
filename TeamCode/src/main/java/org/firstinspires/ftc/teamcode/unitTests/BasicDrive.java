package org.firstinspires.ftc.teamcode.unitTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (group = "UnitTest")
public class BasicDrive extends OpMode {

    DcMotor frontleft, frontright, backleft, backright;

    @Override
    public void init() {
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        frontright.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.REVERSE);

        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }
    double x=0,y=0,pivot=0;
    @Override
    public void loop() {
        x=gamepad1.left_stick_x;
        y=-gamepad1.left_stick_y;
        pivot = gamepad1.right_stick_x;
        frontleft.setPower(y+x-pivot);
        backleft.setPower(y-x-pivot);
        frontright.setPower(y-x+pivot);
        backright.setPower(y+x+pivot);
    }
}
