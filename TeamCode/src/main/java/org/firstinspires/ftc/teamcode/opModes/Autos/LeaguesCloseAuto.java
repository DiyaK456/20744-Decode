package org.firstinspires.ftc.teamcode.opModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.DualClawController;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.SimpleDrive;

@Autonomous
public class LeaguesCloseAuto extends LinearOpMode {

    private Shooter shooter;
    private
    DualClawController doors;
    private DcMotor intake;
    private SimpleDrive drive;

    private void ShooterandIntake() {
    }

    @Override
    public void runOpMode() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        shooter = new Shooter(hardwareMap);
        drive = new SimpleDrive(hardwareMap);

        doors = new DualClawController(hardwareMap,
                HardwareNames.leftDoor, Constants.LeftDoorsOpen,Constants.LeftDoorsClosed,
                HardwareNames.rightDoor, Constants.RightDoorsOpen,Constants.RightDoorsClosed);

        intake.setDirection(DcMotor.Direction.REVERSE);
        doors.close();
        waitForStart();
        if (opModeIsActive()) {
            int spinUpTime = 4000, doorsOpenTime = 125;


            drive.Set(0,0.5,0);
            sleep(1600);
            drive.Set(0,0,0);

            Constants.ShootSpeed = Constants.MedShootSpeed;
            shooter.StartShoot();
            intake.setPower(1);

            for (int i = 0; i<3; i++) {
                sleep(spinUpTime);
                doors.open();
                sleep(doorsOpenTime);
                doors.close();
            }
            intake.setPower(0);
        }
    }
}