package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "LM0Auto")
public class LM0Auto extends LinearOpMode {

    private DcMotor shooter;
    private DcMotor frontright;
    private DcMotor shooter2;
    private DcMotor intake;
    private DcMotor backleft;
    private DcMotor backright;
    private DcMotor frontleft;

    private void ShooterandIntake() {
    }

    @Override
    public void runOpMode() {
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");
        intake = hardwareMap.get(DcMotor.class, "intake");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");

        shooter2.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            shooter.setPower(1);
            shooter2.setPower(1);
            sleep(1000);
            intake.setPower(1);
            sleep(1200);
            backleft.setPower(-0.5);
            backright.setPower(0.5);
            frontleft.setPower(-0.5);
            frontright.setPower(0.5);
            sleep(300);
        }
    }
}