package oliviaproject.ui.selection.tile.color.demo;

import java.awt.Color;
import java.util.Random;

public class DemoColorUtil {
	public static Color createPastelRandomColor() {
		Random random=new Random();
		final float hue = random.nextFloat();
		// Saturation between 0.1 and 0.3
		final float saturation = (random.nextInt(2000) + 1000) / 10000f;
		final float luminance = 0.9f;
		final Color color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}
	public static Color createRandomColor() {
		return new Color((int)(Math.random()* 0x1000000));
		
	}
	public static Color createTransparentPastelRandomColor() {
		Random random=new Random();
		final float red = random.nextFloat();
		// Saturation between 0.1 and 0.3
		final float green=  random.nextFloat();
		final float blue =  random.nextFloat();
		
		Color color= new Color(red, green, blue, 0.2f);
		return color;
	}
}
