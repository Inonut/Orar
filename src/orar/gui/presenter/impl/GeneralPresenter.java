package orar.gui.presenter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import orar.gui.model.impl.ColumnModel;
import orar.gui.model.impl.GeneralModel;
import orar.gui.model.impl.PersonModel;
import orar.gui.panel.impl.GeneralPanel;
import orar.gui.panel.impl.SquarePanel;
import orar.gui.presenter.Presenter;

public class GeneralPresenter implements Presenter{
	
	private GeneralPanel panel;
	private GeneralModel model;
	
	public GeneralPanel getPanel() {
		return panel;
	}

	public void setPanel(GeneralPanel panel) {
		this.panel = panel;
	}

	public GeneralModel getModel() {
		return model;
	}

	public void setModel(GeneralModel model) {
		this.model = model;
	}


	public void addColumn(String name) {
		panel.addColumn(name);
		panel.updateModelFromView();
		panel.updateViewFromModel();
		if(model.getPersonData() != null){
			for(PersonModel personModel : model.getPersonData()){
				for(int i=0;i<personModel.getActivity().length;i++){
					for(int j=0;j<personModel.getActivity()[i].length;j++){
						if(personModel.getActivity()[i][j] == null){
							personModel.getActivity()[i][j] = new ColumnModel();
							personModel.getActivity()[i][j].setCells(new ArrayList<SquarePanel>());
						}
						SquarePanel squarePanel = new SquarePanel();
						squarePanel.setColor(personModel.getColor());
						personModel.getActivity()[i][j].getCells().add(squarePanel);
					}
				}
			}
		}
		
		/*panel.addColumn(name);
		if(model.getColNames() == null){
			model.setColNames(new ArrayList<String>());
		}
		model.getColNames().add(name);*/
	}

	public void addNewPerson(PersonModel personModel) {
		
		if(model.getPersonData() == null){
			model.setPersonData(FXCollections.observableArrayList());
			panel.getPersonTable().setItems(model.getPersonData());
		}
		
		personModel.setColor(new Color(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(), 0));
		if(model.getColNames() != null){
			/*int i=0,j=0;
			for(ColumnModel[] day : personModel.getActivity()){
				for(ColumnModel row : day ){
					List<SquarePanel> cells =  new ArrayList<SquarePanel>();
					for(String colName : model.getColNames()){
						SquarePanel squarePanel = new SquarePanel();
						squarePanel.setColor(personModel.getColor());
						cells.add(squarePanel);
					}
					personModel.getActivity()[i][j] = new ColumnModel();
					personModel.getActivity()[i][j++].setCells(cells);
				}
				i++;
			}*/
			for(int i=0;i<personModel.getActivity().length;i++){
				for(int j=0;j<personModel.getActivity()[i].length;j++){
					List<SquarePanel> cells =  new ArrayList<SquarePanel>();
					for(String colName : model.getColNames()){
						SquarePanel squarePanel = new SquarePanel();
						squarePanel.setColor(personModel.getColor());
						cells.add(squarePanel);
					}
					personModel.getActivity()[i][j] = new ColumnModel();
					personModel.getActivity()[i][j].setCells(cells);
				}
			}
		}
		model.getPersonData().add(personModel);
	}

	public void displayActivity(PersonModel personModel,int day) {
		panel.updateViewFromModel();
		panel.displayActivity(personModel.getActivity()[day]);
	}
	
}
