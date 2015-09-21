package orar.gui.model.impl;

import java.util.List;

import javafx.scene.control.TextField;
import orar.gui.panel.impl.SquarePanel;

public class ColumnModel {

	private List<SquarePanel> cells;

	public List<SquarePanel> getCells() {
		return cells;
	}

	public void setCells(List<SquarePanel> cells) {
		this.cells = cells;
	}
}
