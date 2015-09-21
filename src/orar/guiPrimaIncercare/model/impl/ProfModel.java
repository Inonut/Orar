package orar.guiPrimaIncercare.model.impl;

import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ProfModel {

	private String name;
	private String code;
	private Color color;
	private ActivityModel activity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public ActivityModel getActivity() {
		return activity;
	}
	public void setActivity(ActivityModel activity) {
		this.activity = activity;
	}
	
	@Override
	public String toString() {
		return this.name + " (" + this.code+ ")";
	}
}
