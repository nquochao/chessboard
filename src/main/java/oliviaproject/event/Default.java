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

public static Color findColor(String color, Color defaultColor) {
	return(color!=null)?Color.decode(color):defaultColor;
	
}

public static Integer findZoom(Integer chesswidth, Integer defaultValue) {
	return(chesswidth!=null)?chesswidth:defaultValue;
}

}
