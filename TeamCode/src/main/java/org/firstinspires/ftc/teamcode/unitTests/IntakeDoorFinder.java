package org.firstinspires.ftc.teamcode.unitTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.DualServoFinder;

@TeleOp
public class IntakeDoorFinder extends OpMode {
    DualServoFinder intakeDoor;
    @Override
    public void init() {
        intakeDoor = new DualServoFinder(hardwareMap, HardwareNames.leftDoor, HardwareNames.rightDoor);
    }
    @Override
    public void loop() {
        intakeDoor.update(gamepad1, telemetry, getRuntime());
        telemetry.update();
    }
}
