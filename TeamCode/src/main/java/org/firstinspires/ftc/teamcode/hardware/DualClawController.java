package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class DualClawController {
    ClawController claw1, claw2;
    public DualClawController(HardwareMap hardwareMap,
           String device1Name, double openPos, double closedPos,
           String device2Name, double openPos2, double closedPos2) {
        claw1 = new ClawController(hardwareMap, device1Name, openPos, closedPos);
        claw2 = new ClawController(hardwareMap, device2Name, openPos2, closedPos2);
    }
    public void open() {claw1.open();claw2.open();}
    public void midOpen() {claw1.midOpen();claw2.midOpen();}
    public void close() {claw1.close();claw2.close();}
    public void toggle() {claw1.toggle();claw2.toggle();}
    public ClawController.position getState() {return claw1.currentPosition;}

}
