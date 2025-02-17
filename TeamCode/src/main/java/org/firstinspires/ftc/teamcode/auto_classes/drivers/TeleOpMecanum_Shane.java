package org.firstinspires.ftc.teamcode.auto_classes.drivers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.DriverConfiguration;
import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog.Analog.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Analog_Action.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary.Binary.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.*;

@TeleOp(name="Driver Shane", group="9108") //fix this
public class TeleOpMecanum_Shane extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot;

    //Initialized by: Initialization Button (i think)
    public void init() {
        Robot = new Robot(gamepad1, gamepad2, telemetry, hardwareMap);
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize();
        /*STARTDRIVER1*/
        Robot.Driver1 = new DriverConfiguration(Robot, gamepad1);
        Robot.Driver1.assignDebounce(500);
        Robot.Driver1.assignAnalog(left_stick_x, drivex);
        Robot.Driver1.assignAnalog(left_stick_y, drivey);
        Robot.Driver1.assignSign  (left_stick_y, ButtonAnalog.SIGN.NEGATIVE);
        Robot.Driver1.assignAnalog(right_stick_x, turn);
        Robot.Driver1.assignBinary(y, faceUp);
        Robot.Driver1.assignBinary(b, faceRight);
        Robot.Driver1.assignBinary(a, faceDown);
        Robot.Driver1.assignBinary(x, faceLeft);
        Robot.Driver1.assignBinary(dpad_left, BINARY_turnLeft);
        Robot.Driver1.assignBinary(dpad_right, BINARY_turnRight);
        Robot.Driver1.assignBinary(y, swapDriveMode);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.none, resetGyro);
        Robot.Driver1.assignToggle(ButtonBinary.Binary.none, ButtonBinary.ACTUATE.TOGGLE);
        /*STARTDRIVER2*/
        Robot.Driver2 = new DriverConfiguration(Robot, gamepad2);
        Robot.Driver2.assignDebounce(500);
        Robot.Driver2.assignAnalog(left_stick_y, elevatorDrive);
        Robot.Driver2.assignSign  (left_stick_y, ButtonAnalog.SIGN.NEGATIVE);
        Robot.Driver2.assignAnalog(left_trigger, intakeGrab);
        Robot.Driver2.assignAnalog(right_trigger, intakeDrop);
        Robot.Driver2.assignBinary(a, elevatorLowerAbs);
        Robot.Driver2.assignBinary(y, elevatorRaiseAbs);
        /*ENCONFIG*/
        Robot.DCGm.setRelativeDrive(true);
        Robot.RHG.Hubs[0].setLedColor(0,255,255);
        telemetry.addData("Status","Complete");
        telemetry.update();
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        runtime.reset();
        telemetry.setAutoClear(true);
    }

    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
        Robot.run(runtime);
        Robot.IMU.composeTelemetry(telemetry);
        Robot.DCGm.composeTelemetry(telemetry);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}
