package orar.constants;

import javafx.scene.paint.Color;

public enum Constants {
	
	NUMBER_OF_HOURS(12),NUMBER_OF_DAY(5);
	
	private int val;
	Constants(int val){
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
}
