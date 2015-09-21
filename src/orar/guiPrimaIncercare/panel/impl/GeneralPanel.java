package orar.guiPrimaIncercare.panel.impl;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import orar.guiPrimaIncercare.model.impl.ProfModel;
import orar.guiPrimaIncercare.panel.Panel;
import orar.guiPrimaIncercare.presenter.impl.GeneralPresenter;

public class GeneralPanel extends BorderPane implements Panel{

	private GeneralPresenter presenter;
	private ListView<ProfModel> profList;
	private Button addProf;
	private Button prevDay;
	private Button nextDay;
	private Label dayLable;
	private Button addColumn;
	
	public GeneralPanel() {
		
		profList = new ListView<ProfModel>();
		addProf = new Button("Add");
		prevDay = new Button("<-");
		nextDay = new Button("->");
		dayLable = new Label("text");
		addColumn = new Button("AddColumn");
		
		GridPane profPanel = new GridPane();
		profPanel.add(new Label("Profesori"), 0, 0);
		profPanel.add(profList,0,1);
		profPanel.add(addProf,0,2);
		
		
		BorderPane activityPanel = new BorderPane();
		GridPane controlActivitiPanel = new GridPane();
		controlActivitiPanel.setAlignment(Pos.CENTER);
		prevDay.setMaxWidth(Double.MAX_VALUE);
		dayLable.setMaxWidth(Double.MAX_VALUE);
		nextDay.setMaxWidth(Double.MAX_VALUE);
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHalignment(HPos.RIGHT);
		controlActivitiPanel.getColumnConstraints().add(column1); 

		ColumnConstraints column2 = new ColumnConstraints();
		column2.setHalignment(HPos.LEFT);
		controlActivitiPanel.getColumnConstraints().add(column2);
		controlActivitiPanel.add(prevDay,0,0);
		controlActivitiPanel.add(dayLable,1,0);
		controlActivitiPanel.add(nextDay,2,0);
		controlActivitiPanel.add(addColumn, 0, 1);
		
		activityPanel.setTop(controlActivitiPanel);
		
		this.setLeft(profPanel);
		this.setCenter(activityPanel);
		
	}
	
	@Override
	public void updateModelFromView() {
		
	}

	@Override
	public void updateViewFromModel() {
		
	}

	public GeneralPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(GeneralPresenter presenter) {
		this.presenter = presenter;
	}
	
}
