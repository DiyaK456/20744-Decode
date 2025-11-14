package org.firstinspires.ftc.teamcode.opModes.TeleOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.DualClawController;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.SimpleDrive;
import org.firstinspires.ftc.teamcode.util.ButtonBlock;
import org.firstinspires.ftc.teamcode.util.OpModeType;

@TeleOp (name = "TeleOp", group="!Priority")
public class TeleOpV1 extends OpMode {
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Robot robot;
    SimpleDrive drive;
    Shooter shooter;
    DcMotor intake;
//    DualClawController doors;

    ButtonBlock doorControl, intakeControl, shooterControl, doorsToggle;
    boolean intaking = false;
    boolean outtaking = false;
    double intakePower = 1;
    boolean shooting = false;

    @Override
    public void init() {
        drive = new SimpleDrive(hardwareMap);
        shooter = new Shooter(hardwareMap);
        intake = hardwareMap.get(DcMotor.class, HardwareNames.intakeMotor);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        robot = new Robot(hardwareMap, OpModeType.TELEOP);

        doorControl = new ButtonBlock()
                .onTrue(() -> robot.doors.open())
                .onFalse(() -> robot.doors.close());
        doorsToggle = new ButtonBlock()
                .onTrue(() -> robot.doors.toggle());
        intakeControl = new ButtonBlock()
                .onTrue(this::toggleIntake);
        shooterControl = new ButtonBlock()
                .onTrue(this::toggleShooter);

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        robot.OnInit();
    }

    @Override
    public void start() {
        robot.OnStart();
//        intake.setPower(intakePower*0.6);
    }

    @Override
    public void loop() {
        shooter.UpdateShootReady();
        drive.Set(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
//        if (gamepad1.b) {
//            doors.open();
//            doorControl.ResetLock();
//        } else if (gamepad1.y) {
//            doors.close();
//            doorControl.ResetLock();
//        } else {
//            doorControl.update(shooter.UpdateShootReady() && gamepad1.a);
//        }
        if (shooter.ShootReady()) gamepad1.rumble(100);

        if (gamepad1.a) {
            robot.doors.open();
        } else {
            robot.doors.close();
        }

        if (gamepad1.dpad_down) {
            Constants.ShootSpeed = Constants.MedShootSpeed;
        } else if (gamepad1.dpad_up) {
            Constants.ShootSpeed = Constants.FarShootSpeed;
        }
        //doorsToggle.update(gamepad1.a);

//        if (gamepad1.left_bumper && !outtaking) {
//            outtaking = true;
//        } else if (!gamepad1.left_bumper) {
//            outtaking = false;
//        }

//        if (outtaking)
//            intake.setPower(-intakePower*0.6);
//        else if (shooter.Shooting()) {
//            intake.setPower(intakePower);
//            intaking = true;
//        } else {
//            intakeControl.update(gamepad1.right_bumper);
//        }
        if (gamepad1.right_bumper) {
            intake.setPower(intakePower);
        } else if (gamepad1.left_bumper) {
            intake.setPower(intakePower * -0.6);
        } else {
            intake.setPower(intakePower*0.6);
        }

        shooterControl.update(gamepad1.right_trigger > 0.1);

        telemetry.addData("Doors State", robot.doors.getState());
        telemetry.addData("Shooter Vel", shooter.GetVelocity());
        telemetry.addData("Shooter Target Vel", shooter.GetTargetVelocity());
        telemetry.update();
    }
    public void toggleIntake() {
        if (intaking) {
            intaking = false;
            intake.setPower(0);
        } else {
            intaking = true;
            intake.setPower(intakePower);
        }
    }
    public void toggleShooter() {
        if (shooting) {
            shooting = false;
            shooter.Stop();
        } else {
            shooting = true;
            shooter.StartShoot();
        }
    }
}
