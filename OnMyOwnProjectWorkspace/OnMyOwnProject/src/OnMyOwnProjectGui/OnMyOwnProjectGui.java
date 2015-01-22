package OnMyOwnProjectGui;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import OnMyOwnProjectCalculate.CalulateProgram;
import OnMyOwnProjectSchoolSelectionProgram.OnMyOwnProjectSchoolSelectionProgram;
public class OnMyOwnProjectGui extends JFrame
{
	public OnMyOwnProjectGui()
	{
		JFrame theFrame = new JFrame();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthOfScreen = gd.getDisplayMode().getWidth();
		int heightOfScreen = gd.getDisplayMode().getHeight();
		theFrame.setTitle("On My Own Project");
		theFrame.setSize(widthOfScreen-50,heightOfScreen-50);
		
		JPanel panel1 = new JPanel();
		
		JButton firstProgramButton = new JButton("School Selecting Program");
		panel1.add(firstProgramButton);
		
		//JButton secondProgramButton = new JButton("Caluclating Program");
		//panel1.add(secondProgramButton);
		theFrame.getContentPane().add(panel1);
		firstProgramButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				FirstProjectAddRemoveScreen firstProgram = new FirstProjectAddRemoveScreen();
				
				
			}
		});
		//theFrame.getContentPane().add(firstProgramButton);
		/*secondProgramButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				CalulateProgram secondProgram = new CalulateProgram();
			}
		});
		theFrame.getContentPane().add(secondProgramButton);*/
		//theFrame.setLocation(200,200);
		theFrame.pack();
		theFrame.setVisible(true);
		
		theFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}
