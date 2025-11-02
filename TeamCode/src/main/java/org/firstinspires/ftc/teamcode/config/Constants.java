package org.firstinspires.ftc.teamcode.config;

import com.acmerobotics.dashboard.config.Config;
//
@Config
public class Constants {
    public static double ShootSpeed = 103; // In degrees
    public static double FarShootSpeed = 120, MedShootSpeed = 103;
    public static double RightDoorsClosed = 0.9, RightDoorsOpen = 0.8;
    public static double LeftDoorsClosed = 0.9, LeftDoorsOpen = 0.78;
    public static double TestShooterPower = 0.489;
    public static int ShooterErrorRange = 5; // Plus or minus this amount


    public static double kP = 0.00025;
    public static double kI = 0.0008;
    public static double kD = 0.006;
}
