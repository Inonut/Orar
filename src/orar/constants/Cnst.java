package orar.constants;

import javafx.scene.paint.Color;

public class Cnst {
	
	public static final String URL = "C:/Users/Danescu/git/Orar/orarDB.txt";
//dddd
	public static String getColorFormat(Color color){
		return String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
	}
	
	public static String getDayName(int currentDay) {
		switch (currentDay) {
		case 0:	return "Luni";
		case 1:	return "Marti";
		case 2:	return "Miercuri";
		case 3:	return "Joi";
		case 4:	return "Vineri";
		}
		return null;
	}
}
