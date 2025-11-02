package org.firstinspires.ftc.teamcode.unitTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.MotorVelocityController;

@TeleOp
public class motorVelocityPIDTester extends LinearOpMode {
    FtcDashboard dashboard = FtcDashboard.getInstance();
    MotorVelocityController motor;
    @Override
    public void runOpMode() {
        motor = new MotorVelocityController(hardwareMap, HardwareNames.shooterMotor);

        telemetry = new MultipleTelemetry(telemetry,dashboard.getTelemetry());
        waitForStart();
        if (opModeIsActive()) {
            //motor.setTargetVelocity(45);

            while (opModeIsActive()) {
                motor.update();
                telemetry.addData("Controller DeltaTime", motor.deltaTimeSeconds());
                telemetry.addData("Motor Position Ticks", motor.getPosTicks());
                telemetry.addData("Current Speed Ticks", motor.getVelocityTicks());
                telemetry.addData("Current Speed Deg", motor.getVelocityDegrees());
                telemetry.addData("Current Speed Deg(Built in)", motor.getRawVelocityDeg());
                telemetry.update();
            }
        }
    }
}
