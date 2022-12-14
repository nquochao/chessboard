package oliviaproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oliviaproject.event.Default;
import oliviaproject.hibernate.dao.UserNameSQL;
import oliviaproject.hibernate.entities.UserName;
import oliviaproject.ui.dashboard.OliviaFrame;
@Component
public class LoginDialog extends JFrame implements ActionListener {
	
	@Autowired
	OliviaFrame frame;
   JPanel panel;
   JLabel user_label, password_label, message;
   JTextField userName_text;
   JPasswordField password_text;
   JButton submit, cancel;
   LoginDialog() {
      // Username Label
      user_label = new JLabel();
      user_label.setText("User Name :");
      userName_text = new JTextField();
      // Password Label
      password_label = new JLabel();
      password_label.setText("Password :");
      password_text = new JPasswordField();
      // Submit
      submit = new JButton("SUBMIT");
      panel = new JPanel(new GridLayout(3, 1));
      panel.add(user_label);
      panel.add(userName_text);
      panel.add(password_label);
      panel.add(password_text);
      message = new JLabel();
      panel.add(message);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle("Please Login Here !");
      setSize(450,350);
      setVisible(true);
      userName_text.setText("olivia");
      password_text.setText("olivia");   }
   public static void main(String[] args) {
      new LoginDialog();
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
      String userName = userName_text.getText();
      String password = password_text.getText();
      UserName u=new UserName();
      u.setUserName(userName);
      u.setPassword(password);
      UserNameSQL mu = new UserNameSQL();
		mu.init();
      u=mu.get(u);
      if(u!=null) {
    	  message.setText(" Hello " + userName + "");
    	  Default.setUserName(u);

    	  this.setVisible(false);
    	   SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                try {
						frame.init();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
    	   
      }

   }
}