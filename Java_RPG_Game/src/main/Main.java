package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setResizable(false); //Restrict ability to resize the screen by user
		window.setTitle("RPG_Game");
		
		GameWindow gameWindow = new GameWindow();
		window.add(gameWindow);
		window.pack();
		
		window.setLocationRelativeTo(null); //Open window in the middle of the screen
		window.setVisible(true); //Make the window visible
		
		gameWindow.startGameThread(); 
	}
}
