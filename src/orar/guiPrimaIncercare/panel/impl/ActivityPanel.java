package orar.guiPrimaIncercare.panel.impl;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import orar.guiPrimaIncercare.panel.Panel;
import orar.guiPrimaIncercare.presenter.impl.ActivityPresenter;

public class ActivityPanel extends BorderPane implements Panel {
	
	private MatrixPanel matrixPanel;
	private ActivityPresenter presenter;
	
	public ActivityPanel() {
		matrixPanel = new MatrixPanel();
		presenter = new ActivityPresenter(this);
		
		
	}

	@Override
	public void updateModelFromView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateViewFromModel() {
		// TODO Auto-generated method stub

	}

}
