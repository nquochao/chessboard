package oliviaproject.ui.selection.tile.color.demo;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveObject {
	static Logger log=LoggerFactory.getLogger(MoveObject.class);
int x, y, pasx, pasy, minx, maxx, miny,maxy;
Color[] colors;
public int getMinx() {
	return minx;
}
public void setMinx(int minx) {
	this.minx = minx;
}
public int getMaxx() {
	return maxx;
}
public void setMaxx(int maxx) {
	this.maxx = maxx;
}
public int getMiny() {
	return miny;
}
public void setMiny(int miny) {
	this.miny = miny;
}
public int getMaxy() {
	return maxy;
}
public void setMaxy(int maxy) {
	this.maxy = maxy;
}
String label;
Color color;
Font font ;
public Font getFont() {
	return font;
}
public void setFont(Font font) {
	this.font = font;
}
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public MoveObject(int x, int y, int pasx, int pasy, String label, Color color, Color[] colors, int minX,int maxX, int minY,  int maxY, Font font) {
	this.x=x;
	this.y=y;
	this.pasx=pasx;
	this.pasy=pasy;
	this.label=label;
	this.color=color;
	this.minx=minX;
	this.maxx=maxX;
	this.miny=minY;
	this.maxy=maxY;
    this.font = font;
    this.colors=colors;

}
public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

public int getPasx() {
	return pasx;
}

public void setPasx(int pasx) {
	this.pasx = pasx;
}

public int getPasy() {
	return pasy;
}

public void setPasy(int pasy) {
	this.pasy = pasy;
}
public int getXMove() {
	x+=pasx;
	manageXBound(x);
	return x;
}
private int manageXBound(int x) {
	if(x>=maxx) {
		x=maxx;
		pasx=-pasx;
	}
	if(x<minx) {
		x=minx;;
		pasx=-pasx;
	}
	return x;
	}

public int getYMove() {
	y+=pasy;
	y=manageYBound(y);
	return y;
}
private Map<Integer, Character> manageLabel() {
	Map <Integer, Character>result=new HashMap<>();
	for(int i =0; i<label.length();i++) {
        int colorint=(i%colors.length);

		result.put(Integer.valueOf(i), new Character(label.charAt(i)+"",colors[colorint],font));
	}
return result;
}
private int manageYBound(int y) {
if(y>=maxy) {
	y=maxy;
	pasy=-pasy;
}
if(y<miny) {
	y=miny;
	pasy=-pasy;
}
return y;
}
}
