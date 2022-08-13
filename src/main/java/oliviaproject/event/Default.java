package oliviaproject.event;

import java.awt.Color;

import oliviaproject.hibernate.entities.UserName;

public class Default {
static UserName userName;

public static UserName getUserName() {
	return userName;
}

public static void setUserName(UserName userName) {
	Default.userName = userName;
}

public static Color findColor(Color color, Color defaultColor) {
	return(color==null)?color:defaultColor;
}

}
