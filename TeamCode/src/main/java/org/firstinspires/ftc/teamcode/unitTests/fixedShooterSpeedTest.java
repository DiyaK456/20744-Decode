package org.firstinspires.ftc.teamcode.unitTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;

@TeleOp
public class fixedShooterSpeedTest extends LinearOpMode {


    @Override
    public void runOpMode() {
        DcMotor motor1 = hardwareMap.get(DcMotor.class, HardwareNames.shooterMotor);
        DcMotor motor2 = hardwareMap.get(DcMotor.class, HardwareNames.shooterMotor2);
        DcMotor intake = hardwareMap.get(DcMotor.class, HardwareNames.intakeMotor);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a) {
                motor1.setPower(Constants.TestShooterPower);
                motor2.setPower(Constants.TestShooterPower);
            } else {
                motor1.setPower(0);
                motor2.setPower(0);
            }

            if (gamepad1.x) {
                intake.setPower(-1);
            } else {
                intake.setPower(0);
            }

            telemetry.addLine("Hold A to run Shooter");
            telemetry.addLine("Hold X to run Intake");
            telemetry.update();
        }
    }
}
