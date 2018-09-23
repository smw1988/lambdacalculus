package menq.lex;

import java.util.ArrayList;

public class MenqList extends MenqObject {

	private ArrayList<MenqObject> objectList;
	
	public MenqList() {
		this.objectList = new ArrayList<>();
	}
	
	public void addObject(MenqObject object) {
		this.objectList.add(object);
	}
	
	Iterable<MenqObject> getObjects() {
		return this.objectList;
	}
	
}
