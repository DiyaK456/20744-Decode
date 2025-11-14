package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.DualClawController;
import org.firstinspires.ftc.teamcode.util.OpModeType;

public class Robot {
    HardwareMap hardwareMap;
    public OpModeType opModeType;
    public DualClawController doors;

    public Robot(HardwareMap hardwareMap, OpModeType opModeType) {this.hardwareMap = hardwareMap;this.opModeType = opModeType;}
    public void OnInit() {
        doors = new DualClawController(hardwareMap,
                HardwareNames.leftDoor, Constants.LeftDoorsOpen,Constants.LeftDoorsClosed,
                HardwareNames.rightDoor, Constants.RightDoorsOpen,Constants.RightDoorsClosed);

        if (opModeType == OpModeType.AUTO) {
            doors.close();
        }
    }
    public void OnStart() {
        if (opModeType == OpModeType.TELEOP) {
            doors.close();
        }
    }
    public void Loop() {

    }
}
