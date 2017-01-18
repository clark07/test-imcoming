package com.cs.test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotDemo {

	public static void main(String[] args) throws Exception {
		
		for (int i = 0; i < 5; i++) {
			repeat();
			Thread.sleep(1000);
		}
	}

	private static void repeat() throws AWTException {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		System.out.println(screenSize);
				
				Robot robot = new Robot();
				
				robot.mouseMove(1400, 750);
				robot.delay(50);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				robot.delay(50);
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				robot.delay(50);
				
				
				for (int i = 0; i < 5; i++) {
					robot.mouseMove(1470, 790);
					robot.delay(50);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
				}
	}

}
