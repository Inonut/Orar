package orar.services.impl.util;

import java.util.Comparator;

import orar.gui.model.impl.PersonModel;

public class MoreConditionComparator  implements Comparator<PersonModel>{

	@Override
	public int compare(PersonModel o1, PersonModel o2) {
		return o2.getCondition()-o1.getCondition();
	}
	
}