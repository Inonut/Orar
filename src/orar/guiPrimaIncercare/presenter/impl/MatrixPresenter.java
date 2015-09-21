package orar.guiPrimaIncercare.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import orar.gui.panel.impl.SquarePanel;
import orar.guiPrimaIncercare.model.impl.ColumnModel;
import orar.guiPrimaIncercare.model.impl.MatrixModel;
import orar.guiPrimaIncercare.panel.impl.MatrixPanel;
import orar.guiPrimaIncercare.presenter.Presenter;
import orar.guiPrimaIncercare.view.CurrentData;

public class MatrixPresenter implements Presenter {
	
	private MatrixPanel panel;
	private MatrixModel model;
	private CurrentData view;
	
	public MatrixPresenter(MatrixPanel panel) {
		this.panel = panel;
	}

	public void addColumn(int pos) {
		/*int pos = model.getColumns().size()+1;
		
		ColumnModel column = new ColumnModel();
		column.setName(name);
		panel.addCell(new SquarePanel(new Label(name)),pos,0);

		TextField hours = new TextField();
		hours.setPrefWidth(50);
		panel.addCell(hours, pos, 1);
		column.setHours(hours);
		
		SquarePanel squarePanel;
		List<SquarePanel> cells = new ArrayList<SquarePanel>();
		for(int i=0;i<12;i++){
			squarePanel = new SquarePanel();
			squarePanel.setColor(model.getColor());
			panel.addCell(squarePanel,pos,i+2);
			cells.add(squarePanel);
		}
		column.setColumn(cells);
		model.getColumns().add(column);*/
		
		ColumnModel column = new ColumnModel();
		SquarePanel squarePanel;
		List<SquarePanel> cells = new ArrayList<SquarePanel>();
		for(int i=0;i<12;i++){
			squarePanel = new SquarePanel();
			squarePanel.setColor(view.getColor());
			panel.addCell(squarePanel,pos,i+2);
			cells.add(squarePanel);
		}
		column.setCells(cells);
		if(model.getColumns() == null){
			model.setColumns(new ArrayList<ColumnModel>());
		}
		model.getColumns().add(column);
		
	}

	public MatrixPanel getPanel() {
		return panel;
	}

	public void setPanel(MatrixPanel panel) {
		this.panel = panel;
	}

	public MatrixModel getModel() {
		return model;
	}

	public void setModel(MatrixModel model) {
		this.model = model;
	}

	public void initModel() {
		/*model.setColor(new Color(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(), 0));
		model.setHeight(12);
		model.setColumns(new ArrayList<ColumnModel>());*/
	}

	public CurrentData getView() {
		return view;
	}

	public void setView(CurrentData view) {
		this.view = view;
	}
	
	
}
