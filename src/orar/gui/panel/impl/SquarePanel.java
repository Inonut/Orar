package orar.gui.panel.impl;

import java.util.Random;

import orar.constants.Cnst;
import orar.constants.Constants;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class SquarePanel extends StackPane{
	
	private boolean clicked;
	private boolean visited;
	private Color color;
	private Node obj;

	public SquarePanel() {
		this(null);
	}
	
	public SquarePanel(Node obj) {
		
		this.obj = obj;
		
		//this.color = new Color(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(), 0);
		
		this.setStyle( "-fx-border-color: black;\n"
                + "-fx-border-insets: 2;\n"
				+ "-fx-padding: 10px;\n"
                + "-fx-background-color: white;\n"
                + "-fx-border-width: 1;\n");
		
		if(obj != null){
			this.getChildren().add(obj);
		}
		
		this.setOnMouseClicked(e -> {
			if(color != null){
				if(isClicked()){
					this.clicked = false;
					this.setStyle(this.getStyle() + "-fx-background-color: white;\n");
				}else{
					this.clicked = true;
					this.setStyle(this.getStyle() + "-fx-background-color: "+ Cnst.getColorFormat(color) +";\n");
				}
			}
		});
	}
	
	public void setBackGroundColor(Color color){
		this.setStyle(this.getStyle() + "-fx-background-color: "+ Cnst.getColorFormat(color) +";\n");
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		if(color != null){
			if(!isClicked()){
				this.setStyle(this.getStyle() + "-fx-background-color: white;\n");
			}else{
				this.setStyle(this.getStyle() + "-fx-background-color: "+ Cnst.getColorFormat(color) +";\n");
			}
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Node getObj() {
		return obj;
	}

	public void setObj(Node obj) {
		this.obj = obj;
		this.getChildren().add(obj);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
