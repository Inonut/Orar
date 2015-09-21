package orar.guiPrimaIncercare.model.impl;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import orar.gui.panel.impl.SquarePanel;
import orar.guiPrimaIncercare.model.Model;

public class MatrixModel implements Model{

	private List<ColumnModel> columns;

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}
}
