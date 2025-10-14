//package org.firstinspires.ftc.teamcode.unitTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
//import org.firstinspires.ftc.teamcode.config.HardwareNames;
//import org.firstinspires.ftc.teamcode.util.ButtonBlock;
//
//@TeleOp (group = "UnitTest")
//public class ShooterPower extends LinearOpMode {
//    DcMotorEx motor, motor2;
//    ButtonBlock dpadDown,dpadUp;
//    float targetSpeed = 45;
//    int lastPos = 0;
//    @Override
//    public void runOpMode() {
//        motor = hardwareMap.get(DcMotorEx.class, "shooter");
//        motor2 = hardwareMap.get(DcMotorEx.class, "shooter2");
//        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//
//        //dpadDown = new ButtonBlock().onTrue(() -> {targetSpeed/=2;motor.setVelocity(targetSpeed, AngleUnit.DEGREES);});
//        //dpadUp = new ButtonBlock().onTrue(() -> {targetSpeed*=2;motor.setVelocity(targetSpeed, AngleUnit.DEGREES);});
//        waitForStart();
//        if (opModeIsActive()) {
//            //motor.setVelocity(targetSpeed,AngleUnit.DEGREES);
//            while (opModeIsActive()) {
//                motor.setPower(gamepad1.right_stick_y);
//                motor2.setPower(-gamepad1.right_stick_y);
//                //dpadDown.update(gamepad1.dpad_down);dpadUp.update(gamepad1.dpad_up);
//                telemetry.addData("Var Target Speed",targetSpeed);
//                telemetry.addData("Motor Current", motor.getCurrent(CurrentUnit.AMPS));
//                telemetry.addData("Motor Velocity", motor.getVelocity());
//                telemetry.addData("Motor Velocity (Deg)", motor.getVelocity(AngleUnit.DEGREES));
//                telemetry.addData("Motor Pos", motor.getCurrentPosition());
//                telemetry.addData("Motor Delta Pos", motor.getCurrentPosition()-lastPos);
//                telemetry.update();
//                lastPos = motor.getCurrentPosition();
//            }
//
//        }
//    }
//}