package org.firstinspires.ftc.teamcode.unitTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.HardwareNames;

@TeleOp
public class genericMotorSpin extends LinearOpMode {

    private boolean aPressed = false;
    private DcMotor motor;
    private double power = 1;
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, HardwareNames.shooterMotor);

        waitForStart();
        if (opModeIsActive()) motor.setPower(power);
        while (opModeIsActive()) {
            if (gamepad1.a && !aPressed)
                power = -power;
            aPressed = gamepad1.a;
            motor.setPower(power);

            telemetry.addLine("Press A to switch directions");
            telemetry.update();
        }
    }
}
