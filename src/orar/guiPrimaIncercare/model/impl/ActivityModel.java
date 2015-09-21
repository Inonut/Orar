package orar.guiPrimaIncercare.model.impl;

import java.util.List;

import javafx.scene.control.TextField;

public class ActivityModel {

	private List<TextField> hours;
	private List<MatrixModel> matrix;
	public List<TextField> getHours() {
		return hours;
	}
	public void setHours(List<TextField> hours) {
		this.hours = hours;
	}
	public List<MatrixModel> getMatrix() {
		return matrix;
	}
	public void setMatrix(List<MatrixModel> matrix) {
		this.matrix = matrix;
	}
}
