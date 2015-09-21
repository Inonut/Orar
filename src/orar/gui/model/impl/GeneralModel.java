package orar.gui.model.impl;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import orar.gui.model.Model;

public class GeneralModel implements Model {

	private List<String> colNames;
	private ObservableList<PersonModel> personData;

	public List<String> getColNames() {
		return colNames;
	}

	public void setColNames(List<String> colNames) {
		this.colNames = colNames;
	}

	public ObservableList<PersonModel> getPersonData() {
		return personData;
	}

	public void setPersonData(ObservableList<PersonModel> personData) {
		this.personData = personData;
	}
}
