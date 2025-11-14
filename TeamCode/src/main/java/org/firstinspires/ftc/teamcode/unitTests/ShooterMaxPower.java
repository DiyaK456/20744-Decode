package org.firstinspires.ftc.teamcode.unitTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.HardwareNames;

@TeleOp
public class ShooterMaxPower extends LinearOpMode {

    private boolean aPressed = false;
    private DcMotor motor, motor2;
    private double power = -1;
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, HardwareNames.shooterMotor);
        motor2 = hardwareMap.get(DcMotor.class, HardwareNames.shooterMotor2);
        motor2.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        if (opModeIsActive()) {
            motor.setPower(power);
            motor2.setPower(power);
        }
        while (opModeIsActive()) {
            if (gamepad1.a && !aPressed)
                power = -power;
            aPressed = gamepad1.a;
            motor.setPower(power);
            motor2.setPower(power);

            telemetry.addLine("Press A to switch directions");
            telemetry.update();
        }
    }
}
