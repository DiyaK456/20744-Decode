package org.firstinspires.ftc.teamcode.opModes.Autos;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.DualClawController;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.SimpleDrive;

@Autonomous
public class LeaguesFarAuto extends LinearOpMode {

    private Shooter shooter;
    private
    DualClawController doors;
    private DcMotor intake;
    private IMU imu;
    private SimpleDrive drive;

    private void ShooterandIntake() {
    }

    @Override
    public void runOpMode() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        shooter = new Shooter(hardwareMap);
        drive = new SimpleDrive(hardwareMap);
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP)));

        doors = new DualClawController(hardwareMap,
                HardwareNames.leftDoor, Constants.LeftDoorsOpen,Constants.LeftDoorsClosed,
                HardwareNames.rightDoor, Constants.RightDoorsOpen,Constants.RightDoorsClosed);

        intake.setDirection(DcMotor.Direction.REVERSE);
        doors.close();
        waitForStart();
        if (opModeIsActive()) {
            int spinUpTime = 4000, doorsOpenTime = 125;
            Constants.ShootSpeed = Constants.FarShootSpeed;
            shooter.StartShoot();
            intake.setPower(1);

            for (int i = 0; i<3; i++) {
                ElapsedTime timer = new ElapsedTime();
                while (!shooter.UpdateShootReady() && timer.seconds() < 4);
                doors.open();
                sleep(doorsOpenTime);
                doors.close();
            }

            pivot(30,0.4);
            sleep(500);
            move_fb(0.5,0.5);
            sleep(500);
            move_fb(-0.5,0.5);
            pivot(-30,0.4);

            for (int i = 0; i<3; i++) {
                ElapsedTime timer = new ElapsedTime();
                while (!shooter.UpdateShootReady() && timer.seconds() < 4);
                doors.open();
                sleep(doorsOpenTime);
                doors.close();
            }

            intake.setPower(0);
            sleep(250);
            move_fb(0.5,-0.5);
        }
    }
    private void pivot(double targetDegrees, double speed) {
        // turns at 'speed' speed till turned 'targetDegrees' degrees

        double rot = imu.getRobotYawPitchRollAngles().getYaw();
        double error = degreesError(rot,targetDegrees);
        drive.Set(0,0,speed * (error /Math.abs(error)) );
        while (error < 3) {
            rot = imu.getRobotYawPitchRollAngles().getYaw();
            error = degreesError(rot,targetDegrees);
            telemetry.addData("turning error", error);
            telemetry.update();
        }
        drive.Set(0,0,0);
    }
    private double degreesError(double current, double target) {
        double error = current-target;
        if (error > 180) error = target-current;
        return error;
    }
    private void move_fb(double time, double speed) {
        drive.Set(0,speed,0);
        sleep((int)time*1000L);
        drive.Set(0,0,0);
    }
}