package orar.db;

import orar.gui.model.impl.GeneralModel;

public interface OrarDao {

	void save(GeneralModel generalModel);
	GeneralModel loadModel();
}
