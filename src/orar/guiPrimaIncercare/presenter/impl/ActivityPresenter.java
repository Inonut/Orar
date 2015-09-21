package orar.guiPrimaIncercare.presenter.impl;

import orar.guiPrimaIncercare.model.impl.ActivityModel;
import orar.guiPrimaIncercare.panel.impl.ActivityPanel;

public class ActivityPresenter {

	private ActivityPanel panel;
	private ActivityModel model;
	
	public ActivityPresenter(ActivityPanel panel) {
		this.panel = panel;
	}
	
	
	public ActivityPanel getPanel() {
		return panel;
	}
	public void setPanel(ActivityPanel panel) {
		this.panel = panel;
	}
	public ActivityModel getModel() {
		return model;
	}
	public void setModel(ActivityModel model) {
		this.model = model;
	}
	
	
}
