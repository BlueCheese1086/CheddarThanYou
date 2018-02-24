/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1086.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	TalonSRX talonFrontRight;
	TalonSRX talonBackRight;
	Jaguar jaguarDrive;
	
	Joystick leftStick;
    Joystick rightStick;
    
    Compressor c1;
    
    Solenoid shifter;
    Solenoid intake;
    
	
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		jaguarDrive = new Jaguar(0);
		talonBackRight = new TalonSRX(1);
		talonFrontRight = new TalonSRX(2);
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		c1 = new Compressor(0);
		shifter = new Solenoid(0);
		intake = new Solenoid(1);
		c1.setClosedLoopControl(true);
		shifter.set(true);
	}


	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// m_autoSelected = SmartDashboard.getString("Auto Selector",
		// 		kDefaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}


	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}
	
	public void drive(double speedLeft, double speedRight) {
		talonFrontRight.set(ControlMode.PercentOutput, speedRight);
		talonBackRight.set(ControlMode.PercentOutput, speedRight);
		jaguarDrive.set(speedLeft);
	}
	
	@Override
	public void teleopPeriodic() {
		if(leftStick.getRawButton(1)) {
			drive(leftStick.getY(), rightStick.getY());
		}
		else {
			drive(0, 0);
		}
		if(rightStick.getRawButton(1)) {
			intake.set(false);
		} else {
			intake.set(true);
		}
	}


	@Override
	public void testPeriodic() {
	}
}
