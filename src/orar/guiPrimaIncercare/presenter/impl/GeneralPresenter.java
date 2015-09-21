package orar.guiPrimaIncercare.presenter.impl;

import orar.guiPrimaIncercare.model.impl.GeneralModel;
import orar.guiPrimaIncercare.panel.impl.GeneralPanel;
import orar.guiPrimaIncercare.presenter.Presenter;

public class GeneralPresenter implements Presenter {

	private GeneralModel model;
	private GeneralPanel panel;
	public GeneralModel getModel() {
		return model;
	}
	public void setModel(GeneralModel model) {
		this.model = model;
	}
	public GeneralPanel getPanel() {
		return panel;
	}
	public void setPanel(GeneralPanel panel) {
		this.panel = panel;
	}
	
	
}
