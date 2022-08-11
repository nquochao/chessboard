package oliviaproject.ui.demo;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * All necessary utilities for displaying images, and converting between
 * 2D arrays and BufferedImages.
 * 
 * DO NOT MODIFY THIS CLASS.
 * 
 * @author Benjamin Smith
 * @contact bbsmith1@mix.wvu.edu
 * @version 0.7
 * @since 2018-03-18
 */
public class ImageUtils {

  // Frame to display on screen.
  private JFrame frame;
  // Tabbed panes to switch between
  private JTabbedPane tabbedPane;

  /**
   * Constructor sets up the frame and pane to add to.
   */
  ImageUtils() {
    // Create a new frame to display on screen.
    frame = new JFrame("Project Images");

    // The exit application default window close operation.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set up TabbedPanes to switch between images.
    tabbedPane = new JTabbedPane();

    // Set the Content pane
    frame.setContentPane(tabbedPane);
  }
  
  /**
   * Loads in a 2D Color array (an image) from the specified filepath.
   * @param filepath to the image.
   * @return the 2D Color array.
   */
  public Color[][] loadImage(String filepath) {
    // Load in the image.
    BufferedImage buffImg = loadBufferedImage(filepath);
    // Convert that image to the 2D array of colors and return it.
    Color[][] colorImg = convertTo2DFromBuffered(buffImg);
    return colorImg;
  }

  /**
   * Adds a tab to the frame which displays a given image.
   * @param img the image to be displayed on the tab.
   * @param tabName the name to be given to the tab.
   */
  public void addImage(Color[][] img, String tabName) {
    // Convert the 2D Color array to BufferedImage
    BufferedImage buffImg = convertToBufferedFrom2D(img);

    // Create icon for the image itself.
    ImageIcon icon = new ImageIcon(buffImg);
    icon.getImage().flush();

    // Create icon to be displayed on the tab, scaled to 32x32.
    ImageIcon tabIcon = new ImageIcon(
        buffImg.getScaledInstance(32, 32, Image.SCALE_SMOOTH));

    // Create a label, and add the icon to it.
    JLabel label = new JLabel();
    label.setIcon(icon);

    // Add the tab to the pane.
    tabbedPane.addTab(tabName, tabIcon, label);
  }
  
  /**
   * Packs the frame, sets the preferred size, and makes it visible.
   */
  public void display() {
    // Pack the frame.
    frame.pack();

    // Sets the size of the frame to the preferred size of the images.
    frame.setMinimumSize(frame.getPreferredSize());

    // Makes the frame visible.
    frame.setVisible(true);
  }
  
  /**
   * Clone an array into a new one. This is done because if you do
   * Color[][] tmp = orig;
   * and then edit tmp, the edits persist in orig as well. Thus, need to go
   * value by value to get a true clone of an array.
   * 
   * NOTE: this is a static method, not an instance method.
   * 
   * @param orig the array to copy from.
   * @return the new array that was copied.
   */
  public static Color[][] cloneArray(Color[][] orig) {
    // Create array that is the copy
    Color[][] copy = new Color[orig.length][orig[0].length];
    // Go through each value copying it over.
    for (int i = 0; i < orig.length; i++) {
      for (int j = 0; j < orig[i].length; j++) {
        copy[i][j] = orig[i][j];
      }
    }
    // Return the copy.
    return copy;
  }
  
  /*
   * -----------------
   *  PRIVATE METHODS
   * -----------------
   * 
   * These are used as helpers for the public methods you are using.
   * You do not need to (and can not because private) call any of these.
   *
   */
  
  /**
   * Loads in a BufferedImage from the specified path to be processed.
   * @param filepath The path to the file to read.
   * @return a BufferedImage if able to be read, NULL otherwise.
   */
  private static BufferedImage loadBufferedImage(String filepath) {
    // A BufferedImage initialization.
    BufferedImage img = null;

    // Try to read an image from the specified path.
    try {
      // Read the (image) File the path directs to.
      img = ImageIO.read(new File(filepath));
    } catch (IOException e) {
      System.out.println("Could not load the image, please ensure the filepath"
          + " was properly specified.");
      e.printStackTrace();
      System.exit(1);
    }

    // Return the BufferedImage. If nothing was read, img contains NULL.
    return img;
  }

  /**
   * Converts a 2D array of Colors into a BufferedImage to display
   * @param img the 2d array of Colors
   * @return the BufferedImage capable of being displayed
   */
  private static BufferedImage convertToBufferedFrom2D(Color[][] img) {
    // Create new BufferedImage of specified width and height
    int width = img.length;
    int height = img[0].length;
    BufferedImage bufImg = new BufferedImage(width, height, 1);

    // Set the RGB value of each pixel in the BufferedImage
    for (int x = 0; x < width; x++) {
      for(int y = 0; y < height; y++) {
        bufImg.setRGB(x,  y, img[x][y].getRGB());
      }
    }

    // Return the BufferedImage
    return bufImg;
  }

  /**
   * Converts a BufferedImage to a multidem array of Colors
   * @param img the BufferedImage to convert
   * @return the 2d array of Colors
   */
  private static Color[][] convertTo2DFromBuffered(BufferedImage img) {
    // Get width and height to make new 2d array
    int width = img.getWidth();
    int height = img.getHeight();
    Color[][] result = new Color[width][height];

    // Iterate through the array, adding new Colors from the intRGB values.
    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        // Get the integer RGB, and separate it into individual components.
        // (BufferedImage saves RGB as a single integer value).
        int intRGB = img.getRGB(row, col);
        int red = (intRGB >> 16)&255;
        int green = (intRGB >> 8)&255;
        int blue = intRGB&255;
        // Set the pixel to the Color.
        result[row][col] = new Color(red, green, blue);
      }
    }
    
    // Return array
    return result;
  }

}