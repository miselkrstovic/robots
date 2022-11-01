package com.github;

import com.github.engines.RobotEngine;

public class Main {

	public static void main(String[] args) throws Exception {
		RobotEngine robotWalkerPath1 = new RobotEngine();
		robotWalkerPath1.load("robot.02.in.txt", "robot.02.out.txt");
		robotWalkerPath1.run();
		
		RobotEngine robotWalkerPath2 = new RobotEngine();
		robotWalkerPath2.load("robot.03.in.txt", "robot.03.out.txt");
		robotWalkerPath2.run();
		
		RobotEngine robotWalkerPath3 = new RobotEngine();
		robotWalkerPath3.load("robot.04.in.txt", "robot.04.out.txt");
		robotWalkerPath3.run();
	}
	
}
