package orar.services;

import orar.gui.model.impl.GeneralModel;
import orar.gui.model.impl.PersonModel;

public interface OrarSevices {

	void save(GeneralModel model);

	GeneralModel loadModel();

	PersonModel genereteOrar(GeneralModel generalModel);
}
