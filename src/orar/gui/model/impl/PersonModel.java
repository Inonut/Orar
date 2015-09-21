package orar.gui.model.impl;

import java.awt.Point;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point3D;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import orar.constants.Constants;
import orar.gui.model.Model;
import orar.gui.panel.impl.SquarePanel;

public class PersonModel implements Model {

	private StringProperty name;
	private StringProperty code;
	private StringProperty colorProp;
	private Color color;
	private ColumnModel[][] activity;
	private List<TextField> norma;
	private int condition;
	private Point3D currentPoint;
	
	public PersonModel(String name, String code) {
		this.name = new SimpleStringProperty(name);
		this.code = new SimpleStringProperty(code);
		activity = new ColumnModel[Constants.NUMBER_OF_DAY.getVal()][Constants.NUMBER_OF_HOURS.getVal()];
		currentPoint = new Point3D(0,0,-1);
	}

	public StringProperty getNamePropriety() {
		return name;
	}

	public void setNamePropriety(StringProperty name) {
		this.name = name;
	}

	public StringProperty getCodePropriety() {
		return code;
	}

	public void setCodePropriety(StringProperty code) {
		this.code = code;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getCode() {
		return code.get();
	}

	public void setCode(String code) {
		this.code.set(code);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public StringProperty getColorProp() {
		return colorProp;
	}

	public void setColorProp(StringProperty colorProp) {
		this.colorProp = colorProp;
	}

	public ColumnModel[][] getActivity() {
		return activity;
	}

	public void setActivity(ColumnModel[][] activity) {
		this.activity = activity;
	}

	public List<TextField> getNorma() {
		return norma;
	}

	public void setNorma(List<TextField> norma) {
		this.norma = norma;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public Point3D getNextPoint() {
		
		int i=0;
		int j=0;
		int k=0;
		for(;i<activity.length;i++){
			for(;j<activity[i].length;j++){
				for(;k<activity[i][j].getCells().size();k++){
					int x = 0;
				    try{
				    	x = Integer.parseInt(getNorma().get(k).getText());
				    }catch(Exception e){
				    	x = 0;
				    }
					if(activity[i][j].getCells().get(k).isClicked() && x>0){
						if(!new Point3D(i, j, k).equals(this.currentPoint)){
							this.currentPoint = new Point3D(i, j, k);
							return this.currentPoint;
						}
					}
				}
				k=0;
			}
			j=0;
		}
		
		return null;
	}

	public Point3D getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(Point3D currentPoint) {
		this.currentPoint = currentPoint;
	}
	
	public SquarePanel get(int i,int j,int k){
		return this.activity[i][j].getCells().get(k);
	}
	
	public int getNrDay(){
		return activity.length;
	}
	public int getNrHoure(){
		return activity[0].length;
	}
	public int getNrClasses(){
		return activity[0][0].getCells().size();
	}
}
