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
public class ShooterJoystickPower extends LinearOpMode {
    DcMotorEx motor, motor2;
    DcMotorEx intakeMotor;
    ButtonBlock dpadDown,dpadUp, toggleIntake;
    float targetSpeed = 45;
    int lastPos = 0;

    boolean intaking = false;
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, HardwareNames.shooterMotor);
        motor2 = hardwareMap.get(DcMotorEx.class, HardwareNames.shooterMotor2);
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);

        //dpadDown = new ButtonBlock().onTrue(() -> {targetSpeed/=2;motor.setVelocity(targetSpeed, AngleUnit.DEGREES);});
        //dpadUp = new ButtonBlock().onTrue(() -> {targetSpeed*=2;motor.setVelocity(targetSpeed, AngleUnit.DEGREES);});
        toggleIntake = new ButtonBlock().onTrue(() -> toggleIntake());
        waitForStart();
        if (opModeIsActive()) {
            //motor.setVelocity(targetSpeed,AngleUnit.DEGREES);
            while (opModeIsActive()) {
                motor.setPower(gamepad1.right_stick_y);
                motor2.setPower(gamepad1.right_stick_y);
                toggleIntake.update(gamepad1.right_bumper);
                //dpadDown.update(gamepad1.dpad_down);dpadUp.update(gamepad1.dpad_up);
                telemetry.addData("Var Target Speed",targetSpeed);
                telemetry.addData("Motor Current", motor.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("Motor Power", motor.getPower());
                telemetry.addData("Motor Velocity", motor.getVelocity());
                telemetry.addData("Motor Velocity (Deg)", motor.getVelocity(AngleUnit.DEGREES));
                telemetry.addData("Motor Pos", motor.getCurrentPosition());
                telemetry.addData("Motor Delta Pos", motor.getCurrentPosition()-lastPos);
                telemetry.update();
                lastPos = motor.getCurrentPosition();
            }

        }
    }
    public void toggleIntake() {
        if (intaking) {
            intaking = false;
            intakeMotor.setPower(0);
        } else {
            intaking = true;
            intakeMotor.setPower(-1);
        }
    }
}
