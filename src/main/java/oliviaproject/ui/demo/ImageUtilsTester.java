package oliviaproject.ui.demo;

import java.awt.Color;
import java.util.Scanner;
public class ImageUtilsTester {
public static void main(String[] args)
{
//	Initialize scanner
	Scanner input = new Scanner(System.in);
//	New utility
	ImageUtils utils = new ImageUtils();
	
//	asking user for image location input
	String filePath = "";
	
	System.out.println("Insert the name of the file path you would like to use.");
	System.out.println("The filepath below works for a default image");
	System.out.println("resources/LennaCV.png");
	
	filePath = "resources/LennaCV.png";
	
//	Load Image
	Color[][] orig = utils.loadImage(filePath);
	
//	Display original
	utils.addImage(orig, "Original Image.");
	
//	Ask user if they want to perform custom color function

System.out.println("Do you wish to perform the custom color funciton? (yes or no)");

String customChoice = input.next();

//   If they want to replace a color with a custom color.
if(customChoice.equalsIgnoreCase("yes")) {
	
//	Replace Color function method
	Color[][] customColor = colorReplacer(orig);
	
//	custom color replacement picture to the gallery
	utils.addImage(customColor, "Custom Color Replacement");
}

//Intensify Red

Color [][] intensifyRed = processRed(orig);

//	Intensify Green

Color [][] intensifyGreen = processGreen(orig);

//	Intensify Blue

Color [][] intensifyBlue = processBlue(orig);
//	Make image grayscale
	
	Color[][] grayscale = processGrayscale(orig);
	
	utils.addImage(intensifyRed, "Intensify Red");
	utils.addImage(intensifyGreen, "Intensify Green");
	utils.addImage(intensifyBlue, "Intensify Blue");
	utils.addImage(grayscale, "Grayscale");

//	Display images
	utils.display();
}

//	Method to intensify red

public static Color[][]processRed(Color[][] img) {
//	Clone matrix to not replace original
	Color[][] tmp = ImageUtils.cloneArray(img);
	
//	Runs through entire matrix
	for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
					
//					fetches values of each pixel
					Color pixel = tmp[i][j];
					int r = pixel.getRed();
					int g = pixel.getGreen();
					int b = pixel.getBlue();
					
//					adds 50 to red value
					int rNew = r+50;
					
//					if rNew > 255, make 255
					
					if (rNew >255) {
						rNew = 255;
					}
					tmp[i][j] = new Color (rNew, g, b);
					
				
			}
		}
	return tmp;
}

//Method to intensify green

public static Color[][]processGreen(Color[][] img) {
//Clone matrix to not replace original
Color[][] tmp = ImageUtils.cloneArray(img);

//Runs through entire matrix
for( int i = 0; i < tmp.length; i++)
	{
		for( int j = 0; j< tmp[i].length;j++) {
			
				
//				fetches values of each pixel
				Color pixel = tmp[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				
//				adds 50 to green value
				int gNew = g+50;
				
//				if gNew > 255, make 255
				
				if (gNew >255) {
					gNew = 255;
				}
				tmp[i][j] = new Color (r, gNew, b);
				
			
		}
	}
return tmp;
}

//Method to intensify blue

public static Color[][]processBlue(Color[][] img) {
//Clone matrix to not replace original
Color[][] tmp = ImageUtils.cloneArray(img);

//Runs through entire matrix
for( int i = 0; i < tmp.length; i++)
	{
		for( int j = 0; j< tmp[i].length;j++) {
			
				
//				fetches values of each pixel
				Color pixel = tmp[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				
//				adds 50 to blue value
				int bNew = g+50;
				
//				if bNew > 255, make 255
				
				if (bNew >255) {
					bNew = 255;
				}
				tmp[i][j] = new Color (r, g, bNew);
				
			
		}
	}
return tmp;
}

//	Method to make image grayscale

public static Color[][] processGrayscale(Color[][] img) {
//	Clone matrix to not replace original
	Color[][] tmp = ImageUtils.cloneArray(img);
	
//	Runs through entire matrix
	for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
					
//					fetches values of each pixel
					Color pixel = tmp[i][j];
					int r = pixel.getRed();
					int g = pixel.getGreen();
					int b = pixel.getBlue();
					
//					takes average of color values
					int grayNum = (r + g + b)/3;
//					outputs average into picuture to make grayscale
					tmp[i][j] = new Color (grayNum, grayNum, grayNum);
					
				
			}
		}
	return tmp;
}

//	Method to replace all colors of a certain instance with an inputed color
public static Color[][] colorReplacer(Color [][] img) {
	Scanner input = new Scanner(System.in);
	Color[][] tmp = ImageUtils.cloneArray(img);
	
//	Gives user choice for custom or feather
	
	System.out.println("Do you want to change a specific color, or accent the feather?");
	System.out.println("(specific or feather)");
	
	String target = input.next();
	
	int rReplace = 0;
	int gReplace = 0;
	int bReplace = 0;
	if(target.equalsIgnoreCase("specific")) {
//	Asks for values of color to be replaced
	System.out.println("Enter the red value of the pixel you want to replace from 0 to 255");
	
	rReplace = input.nextInt();
	
	System.out.println("Enter the green value of the pixel you want to replace from 0 to 255");
	
	gReplace = input.nextInt();
	
	System.out.println("Enter the blue value of the color you want to replace from 0 to 255");
	
	bReplace = input.nextInt();
	}
	else if (target.equalsIgnoreCase("feather")) {
		rReplace = 156;
		gReplace = 118;
		bReplace = 169;
	}
	
	else {
		System.out.println("Please enter a valid input next time");
	}
//	Asks to use custom color or preset color as replacement
	
	System.out.println("Would you like to use a preset or custom color to replace?");
	System.out.println("(preset or custom)");
	String choice = input.next().toLowerCase();
	

//		sets color range
	int range = 14;
	
//	If preset color is desired
	String newColorName = null;
	
	if (choice.equalsIgnoreCase("preset"))
	{
//		Accept input for new color name
		
		System.out.println("Input the preset color you would like to use (ex. black, blue, cyan, darkgray, gray, magenta, orange, pink, red, white, yellow");
		newColorName = input.next().toLowerCase();
		Color c;
//		
//		if statement to set new color
		if(newColorName.equalsIgnoreCase("black")) {
			c = Color.BLACK;
		}
		
		else if(newColorName.equalsIgnoreCase("blue")) {
			c = Color.BLUE;
		}
		else if(newColorName.equalsIgnoreCase("cyan")) {
			c = Color.CYAN;
		}
		else if(newColorName.equalsIgnoreCase("darkgray")) {
				c = Color.darkGray;
		}
		else if(newColorName.equalsIgnoreCase("gray")) {
					c = Color.gray;
		}
		
		else if(newColorName.equalsIgnoreCase("magenta")) {
			c = Color.magenta;
}
		else if(newColorName.equalsIgnoreCase("orange")) {
			c = Color.orange;
}
		else if(newColorName.equalsIgnoreCase("pink")) {
			c = Color.pink;
}
		else if(newColorName.equalsIgnoreCase("red")) {
			c = Color.red;
}
		else if(newColorName.equalsIgnoreCase("white")) {
			c = Color.white;
}
		else if(newColorName.equalsIgnoreCase("yellow")) {
			c = Color.yellow;
}
			
//			Default color
		else {
			System.out.println("Preset color not detected. I hope you like black.");
			c = Color.black;
	}
//		for loop runs through entire picture
		for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
				Color pixel = tmp[i][j];
//				Gets color information about current pixel
				int rCurrent = pixel.getRed();
				int gCurrent = pixel.getGreen();
				int bCurrent = pixel.getBlue();

//				If color is close to color you want to replace
				if(rCurrent-range<=rReplace && rReplace<=rCurrent+range 
						&& gCurrent-range<=gReplace && gReplace<=gCurrent+range 
						&& bCurrent-range<=bReplace && bReplace<=bCurrent+range ) {

//					input new color into output
					tmp[i][j] = c;
				}
					
					
					
				
			}
		}
		
	}
	
	else if (choice.equalsIgnoreCase("custom")) {
		int rNew = 0;
		int gNew = 0;
		int bNew = 0;
		
		System.out.println("Input the custom value of red from 0 to 255:");
		
		rNew = input.nextInt();
		
		System.out.println("Input the custom value of green from 0 to 255");
		
		gNew = input.nextInt();
		
		System.out.println("Input the custom value of blue from 0 to 255");
		
		bNew = input.nextInt();
		
		for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
					Color pixel = tmp[i][j];
//				Gets color information about current pixel
				int rCurrent = pixel.getRed();
				int gCurrent = pixel.getGreen();
				int bCurrent = pixel.getBlue();

//				If color is close to color you want to replace
				if(rCurrent-range<=rReplace && rReplace<=rCurrent+range 
						&& gCurrent-range<=gReplace && gReplace<=gCurrent+range 
						&& bCurrent-range<=bReplace && bReplace<=bCurrent+range ) {
//					
//					replace old color with new color
					tmp[i][j] = new Color (rNew,gNew,bNew);
				}
										
				
			}
		}
		
	}
	else {
		System.out.println("invalid entry. I'm printing the original image");
		
	}
	
	


	return tmp;
}
}
