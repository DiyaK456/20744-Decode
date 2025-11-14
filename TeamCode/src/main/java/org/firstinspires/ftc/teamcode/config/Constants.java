package org.firstinspires.ftc.teamcode.config;

import com.acmerobotics.dashboard.config.Config;
//
@Config
public class Constants {
    public static double FarShootSpeed = 147, MedShootSpeed = 112;
    public static double ShootSpeed = MedShootSpeed; // In degrees
    public static double RightDoorsClosed = 0.9, RightDoorsOpen = 0.8;
    public static double LeftDoorsClosed = 0.96, LeftDoorsOpen = 0.82;
    public static double TestShooterPower = 0.489;
    public static int ShooterErrorRange = 5; // Plus or minus this amount
    public static double BottomShooterIncrease = 1.4;


    public static double kP = 0.00025;
    public static double kI = 0.0008;
    public static double kD = 0.006;

    @Config
    public static class shooterMotor {
        public static double P = 96, I = 3, D = 0;
    }
}
