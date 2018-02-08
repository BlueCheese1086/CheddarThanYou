/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1086.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Robot extends TimedRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	TalonSRX talonFrontRight = new TalonSRX(1);
	TalonSRX talonBackRight = new TalonSRX(2);
	Jaguar jaguarFrontLeft = new Jaguar(3);
	Jaguar jaguarBackLeft = new Jaguar(4);
	
	Joystick leftStick = new Joystick(0);
    Joystick rightStick = new Joystick(1);
	
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
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
		jaguarFrontLeft.set(speedLeft);
		jaguarBackLeft.set(speedLeft);
	}
	
	@Override
	public void teleopPeriodic() {
		if(leftStick.getRawButton(1)) {
			drive(leftStick.getX(), rightStick.getY());
		}
		else {
			drive(0, 0);
		}
	}


	@Override
	public void testPeriodic() {
	}
}
