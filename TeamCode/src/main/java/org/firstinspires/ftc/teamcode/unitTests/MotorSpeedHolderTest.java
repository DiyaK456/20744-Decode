package org.firstinspires.ftc.teamcode.unitTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.util.ButtonBlock;

@TeleOp (group = "UnitTest")
public class MotorSpeedHolderTest extends LinearOpMode {
    DcMotorEx motor, motor2;
    ButtonBlock dpadDown,dpadUp;
    double targetSpeed = 45;
    double setToSpeed = 0;
    int lastPos = 0;
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, HardwareNames.shooterMotor);
//        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor2 = hardwareMap.get(DcMotorEx.class, HardwareNames.shooterMotor2);
//        motor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);


        dpadDown = new ButtonBlock().onTrue(this::decreaseVel);
        dpadUp = new ButtonBlock().onTrue(this::increaseVel);
        waitForStart();
        if (opModeIsActive()) {
            setVelocity(targetSpeed,AngleUnit.DEGREES);
            while (opModeIsActive()) {
//                motor.setPower(gamepad1.right_stick_y);
                dpadDown.update(gamepad1.dpad_down);dpadUp.update(gamepad1.dpad_up);
                telemetry.addData("Var Target Speed",targetSpeed);
                //telemetry.addData("Motor Current", motor.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("Motor Power", motor.getPower());
                telemetry.addData("Motor Velocity", motor.getVelocity());
                telemetry.addData("Motor Velocity (Deg)", motor.getVelocity(AngleUnit.DEGREES));
                telemetry.addData("Motor Pos", motor.getCurrentPosition());
                telemetry.addData("Motor Delta Pos", motor.getCurrentPosition()-lastPos);
                telemetry.addData("Set To Speed", setToSpeed);
                telemetry.update();
                lastPos = motor.getCurrentPosition();
            }

        }
    }

    public void setVelocity(double rate, AngleUnit unit) {
        setToSpeed = rate;
        motor.setVelocity(rate, unit);
        motor2.setVelocity(rate, unit);
    }
    public void setVelocity(double rate) {
        setVelocity(rate, AngleUnit.DEGREES);
    }
    public void decreaseVel() {
        targetSpeed /=2;
        setVelocity(targetSpeed);
    }
    public void increaseVel() {
        targetSpeed *=2;
        setVelocity(targetSpeed);
    }
}
