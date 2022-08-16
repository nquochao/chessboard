package oliviaproject.ui.selection.tile.color.demo;

import java.awt.Color;
import java.awt.Font;

public class Character {
String value;
Color color;
Font font;
public Character(String value,
Color color,
Font font) {
	this.value=value;
	this.color=color;
	this.font=font;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}
public Font getFont() {
	return font;
}
public void setFont(Font font) {
	this.font = font;
}
}
