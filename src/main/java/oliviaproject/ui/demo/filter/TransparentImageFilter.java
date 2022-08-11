package oliviaproject.ui.demo.filter;

import java.awt.image.RGBImageFilter; 

public class TransparentImageFilter extends RGBImageFilter { float alphaPercent; 


public TransparentImageFilter () { this (0.75f); } 

public TransparentImageFilter (float aPercent) throws IllegalArgumentException { if ((aPercent < 0.0) || (aPercent > 1.0)) throw new IllegalArgumentException(); alphaPercent = aPercent; canFilterIndexColorModel = true; } public int filterRGB (int x, int y, int rgb) { int a = (rgb >> 24) & 0xff; a *= alphaPercent; return ((rgb & 0x00ffffff) | (a << 24)); } } 