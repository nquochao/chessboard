package oliviaproject.ui.demo;
//Java program to show Example of CardLayout.
//in java. Importing different Package.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//class extends JFrame
public class CardLayoutDemo extends JFrame {

	// Initialization the value of
	// current card is 1 .
	private int currentCard = 1;

	// Declaration of objects
	// of CardLayout class.
	private CardLayout cl;

	public CardLayoutDemo()
	{

		// Function to set visibility of JFrame
		setTitle("Card Layout Example");

		// Function to set visibility of JFrame
		setSize(300, 150);

		// Creating Object of "Jpanel" class
		JPanel cardPanel = new JPanel();

		// Initialization of object "c1"
		// of CardLayout class.
		cl = new CardLayout();

		// set the layout
		cardPanel.setLayout(cl);

		// Initialization of object
		// "jp1" of JPanel class.
		JPanel jp1 = new JPanel();

		// Initialization of object
		// "jp2" of CardLayout class.
		JPanel jp2 = new JPanel();

		// Initialization of object
		// "jp3" of CardLayout class.
		JPanel jp3 = new JPanel();

		// Initialization of object
		// "jp4" of CardLayout class.
		JPanel jp4 = new JPanel();

		// Initialization of object
		// "jl1" of JLabel class.
		JLabel jl1 = new JLabel("Card1");

		// Initialization of object
		// "jl2" of JLabel class.
		JLabel jl2 = new JLabel("Card2");

		// Initialization of object
		// "jl3" of JLabel class.
		JLabel jl3 = new JLabel("Card3");

		// Initialization of object
		// "jl4" of JLabel class.
		JLabel jl4 = new JLabel("Card4");

		// Adding JPanel "jp1" on JFrame.
		jp1.add(jl1);

		// Adding JPanel "jp2" on JFrame.
		jp2.add(jl2);

		// Adding JPanel "jp3" on JFrame.
		jp3.add(jl3);

		// Adding JPanel "jp4" on JFrame.
		jp4.add(jl4);

		// Adding the cardPanel on "jp1"
		cardPanel.add(jp1, "1");

		// Adding the cardPanel on "jp2"
		cardPanel.add(jp2, "2");

		// Adding the cardPanel on "jp3"
		cardPanel.add(jp3, "3");

		// Adding the cardPanel on "jp4"
		cardPanel.add(jp4, "4");

		// Creating Object of "JPanel" class
		JPanel buttonPanel = new JPanel();

		// Initialization of object
		// "firstbtn" of JButton class.
		JButton firstBtn = new JButton("First");

		// Initialization of object
		// "nextbtn" of JButton class.
		JButton nextBtn = new JButton("Next");

		// Initialization of object
		// "previousbtn" of JButton class.
		JButton previousBtn = new JButton("Previous");

		// Initialization of object
		// "lastbtn" of JButton class.
		JButton lastBtn = new JButton("Last");

		// Adding JButton "firstbtn" on JFrame.
		buttonPanel.add(firstBtn);

		// Adding JButton "nextbtn" on JFrame.
		buttonPanel.add(nextBtn);

		// Adding JButton "previousbtn" on JFrame.
		buttonPanel.add(previousBtn);

		// Adding JButton "lastbtn" on JFrame.
		buttonPanel.add(lastBtn);

		// add firstbtn in ActionListener
		firstBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				// used first c1 CardLayout
				cl.first(cardPanel);

				// value of currentcard is 1
				currentCard = 1;
			}
		});

		// add lastbtn in ActionListener
		lastBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				// used last c1 CardLayout
				cl.last(cardPanel);

				// value of currentcard is 4
				currentCard = 4;
			}
		});

		// add nextbtn in ActionListener
		nextBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				// if condition apply
				if (currentCard < 4)
				{
					
					// increment the value of currentcard by 1
					currentCard += 1;

					// show the value of currentcard
					cl.show(cardPanel, "" + (currentCard));
				}
			}
		});

		// add previousbtn in ActionListener
		previousBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// if condition apply
				if (currentCard > 1) {

					// decrement the value
					// of currentcard by 1
					currentCard -= 1;

					// show the value of currentcard
					cl.show(cardPanel, "" + (currentCard));
				}
			}
		});

		// used to get content pane
		getContentPane().add(cardPanel, BorderLayout.NORTH);

		// used to get content pane
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	// Main Method
	public static void main(String[] args)
	{

		// Creating Object of CardLayoutDemo class.
		CardLayoutDemo cl = new CardLayoutDemo();

		// Function to set default operation of JFrame.
		cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Function to set visibility of JFrame.
		cl.setVisible(true);
	}
}
