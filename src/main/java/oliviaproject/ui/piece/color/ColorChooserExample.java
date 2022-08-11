package oliviaproject.ui.piece.color;
//Java program to implement JColorChooser
//class using ActionListener
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ColorChooserExample extends
	JFrame implements ActionListener {

	// create a button
	JButton b = new JButton("color");

	Container c = getContentPane();

	// Constructor
	ColorChooserExample()
	{

		// set Layout
		c.setLayout(new FlowLayout());

		// add Listener
		b.addActionListener(this);

		// add button to the Container
		c.add(b);
	}

	public void actionPerformed(ActionEvent e)
	{

		Color initialcolor = Color.RED;

		// color chooser Dialog Box
		Color color = JColorChooser.showDialog(this,
					"Select a color", initialcolor);

		// set Background color of the Container
		c.setBackground(color);
	}

	// Main Method
	public static void main(String[] args)
	{

		ColorChooserExample ch = new ColorChooserExample();
		ch.setSize(400, 400);
		ch.setVisible(true);
		ch.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
