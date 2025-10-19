//package org.firstinspires.ftc.teamcode.unitTests;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.util.KalmanFilter1D;
//
//@TeleOp (group = "UnitTest")
//public class KalmanFilterTester extends OpMode {
//    FtcDashboard dashboard = FtcDashboard.getInstance();
//    enum VTs {linear, exponential}
//    VTs vt = VTs.exponential;
//    double exponential = 0.001, linear = 0.001;
//    double randomValue1 = 0, randomValue2 = 0;
//    double error = 2;
//
//    KalmanFilter1D kf = new KalmanFilter1D(0,1,0.1,error);
//    @Override
//    public void init(){
//        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
//    }
//
//    @Override
//    public void start() {
//
//    }
//    boolean lastA = false;
//    @Override
//    public void loop() {
//        updateVals();
//        kf.update(randomValue1,randomValue2);
//
//        if (gamepad1.a && !lastA) {
//            vt = (vt == VTs.linear) ? VTs.exponential : VTs.linear;
//        }
//        lastA = gamepad1.a;
//
//        telemetry.addData("Filter Val", kf.getState());
//        telemetry.addData("Random Value 1", randomValue1);
//        telemetry.addData("Random Value 2", randomValue2);
//        telemetry.addData("Mode", vt);
//        telemetry.update();
//    }
//
//    public void updateVals() {
//        linear += 1;
//        exponential *= 1.1;
//
//        switch (vt) {
//            case linear: randomValue1 = linear + randomValue();
//                randomValue2 = linear + randomValue(); break;
//            case exponential: randomValue1 = exponential + randomValue();
//                randomValue2 = exponential + randomValue(); break;
//        }
//        if (linear > 40) linear = 0.001;
//        if (exponential > 40) exponential = 0.001;
//    }
//    public double randomValue() {return (Math.random() * 2 * error) - error;}
//}
