package oliviaproject.ui.dashboard.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SelectorImageRectangle {
String name;
Rectangle rectangle;
BufferedImage image;
public SelectorImageRectangle(Rectangle rectangle, String name, BufferedImage img) {
this.rectangle=rectangle;
this.name=name;
this.image=img;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public BufferedImage getImage() {
	return image;
}
public void setImage(BufferedImage image) {
	this.image = image;
}

public Rectangle getRectangle() {
	return rectangle;
}
public void setRectangle(Rectangle rectangle) {
	this.rectangle = rectangle;
}
}
