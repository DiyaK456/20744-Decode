package org.firstinspires.ftc.teamcode.opModes.TeleOps;
import android.widget.Button;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.config.HardwareNames;
import org.firstinspires.ftc.teamcode.hardware.DualClawController;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.SimpleDrive;
import org.firstinspires.ftc.teamcode.util.ButtonBlock;

@TeleOp
public class fullDriverTest extends OpMode {
    SimpleDrive drive;
    Shooter shooter;
    DcMotor intake;
    DualClawController doors;

    ButtonBlock doorControl, intakeControl, shooterControl;
    boolean intaking = false;
    double intakePower = -1;
    boolean shooting = false;

    @Override
    public void init() {
        drive = new SimpleDrive(hardwareMap);
        shooter = new Shooter(hardwareMap);
        intake = hardwareMap.get(DcMotor.class, HardwareNames.intakeMotor);
        doors = new DualClawController(hardwareMap, HardwareNames.leftDoor, Constants.DoorsOpen,Constants.DoorsClosed,HardwareNames.rightDoor, Constants.DoorsOpen,Constants.DoorsClosed);

        doorControl = new ButtonBlock()
                .onTrue(() -> doors.open())
                .onFalse(() -> doors.close());
        intakeControl = new ButtonBlock()
                .onTrue(() -> toggleIntake());
        shooterControl = new ButtonBlock()
                .onTrue(() -> toggleShooter());
    }

    @Override
    public void start() {
        doors.open();
    }

    @Override
    public void loop() {
        drive.Set(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
        doorControl.update(shooter.UpdateShootReady());

        if (shooter.Shooting()) {
            intake.setPower(intakePower);
            intaking = true;
        } else {
            intakeControl.update(gamepad1.left_bumper);
        }

        shooterControl.update(gamepad1.right_bumper);

        telemetry.addData("Doors State", doors.getState());
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
