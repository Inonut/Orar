package orar.guiPrimaIncercare.model.impl;

import java.util.List;

public class GeneralModel {

	private List<String> colName;
	private List<ProfModel> profModels;
	public List<String> getColName() {
		return colName;
	}
	public void setColName(List<String> colName) {
		this.colName = colName;
	}
	public List<ProfModel> getProfModels() {
		return profModels;
	}
	public void setProfModels(List<ProfModel> profModels) {
		this.profModels = profModels;
	}
}
