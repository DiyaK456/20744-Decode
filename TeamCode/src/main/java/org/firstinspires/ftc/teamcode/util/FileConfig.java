package org.firstinspires.ftc.teamcode.util;

import android.util.JsonReader;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileConfig {
    private static final Gson gson = new Gson();
    public static void writeFile(String filename, String contents) {
        File writeFile = AppUtil.getInstance().getSettingsFile(filename);
        ReadWriteFile.writeFile(writeFile, contents);
    }
    public static String readFile(String filename) {
        File readFile = AppUtil.getInstance().getSettingsFile(filename);
        StringBuilder sb = new StringBuilder();
        try (Scanner scan = new Scanner(readFile)) {
            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

//    public static JSONObject ReadJson(String filename) {
//        File readFile = AppUtil.getInstance().getSettingsFile(filename);
//        ArrayList<String> contents = new ArrayList<String>();
//        Gson gson = new Gson();
//        try {
//            Scanner scan = new Scanner(readFile);
//            for (int i=0;i<readFile.length();i++) {
//                contents.add(scan.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String[] contentsArray = Arrays.copyOf(contents.toArray(), contents.toArray().length, String[].class);
//        JSONObject output = gson.fromJson(contentsArray.toString(), JSONObject.class);
//        lastReadJson = output;
//        return output;
//    }
    public static void saveJson(String filename, Object data) {
        String json = gson.toJson(data);
        writeFile(filename, json);
    }
    public static <T> T loadJson(String filename, Class<T> clazz) {
        String json = readFile(filename);
        if (json == null || json.isEmpty()) return null;
        return gson.fromJson(json,clazz);
    }
}

