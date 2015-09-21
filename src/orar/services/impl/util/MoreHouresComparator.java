package orar.services.impl.util;

import java.util.Comparator;

import javafx.geometry.Point3D;
import orar.gui.model.impl.PersonModel;

public class MoreHouresComparator implements Comparator<PersonModel> {
	
	private Point3D point;

	public MoreHouresComparator(Point3D point) {
		this.point = point;
	}

	@Override
	public int compare(PersonModel o1, PersonModel o2) {
		int y = 0;
		try{
			y = Integer.parseInt(o2.getNorma().get((int)point.getZ()).getText());
		}catch(Exception e){
			y = 0;
		}
	    int x = 0;
	    try{
	    	x = Integer.parseInt(o1.getNorma().get((int)point.getZ()).getText());
	    }catch(Exception e){
	    	x = 0;
	    }
	    
	    return y-x;
	}
	
	
}
