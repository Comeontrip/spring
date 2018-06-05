package com.rock.learning.java8;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ListenerTest {

	public static void main(String[] args) {
		JButton testButton = new JButton("Test Button");
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click Detected by Anon Class");
			}
		});
		
		testButton.addActionListener(e -> System.out.println("Click Detected by Lambda Listenser"));

		JFrame frame = new JFrame("Listenser Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(testButton, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(Boolean.TRUE);
	}

}
