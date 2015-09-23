package orar.constants;

import javafx.scene.paint.Color;

public class Cnst {
	
	public static final String URL = "C:/Users/Danescu/git/Orar/orarDB.txt";
//ddddd sa mai caut pe net de eroare
	//ok, tadaaaaaa :) )CE )avea?? incercai sa postezi la mine direct si asta nu se poate 
	// acu e un pic mai clar de ce e acel push
	// tu practic nu postezi direct la mine in repository ci in unu de test, daca eu (autorul) vreau schimbarea iii dau curs daca nu o ignor fara sa imi afevteze codulam inteles ;) acu sa vada daca a ajuns si la mine:)
	//unde ai modificat?
	//nu mai e :r e D da
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
