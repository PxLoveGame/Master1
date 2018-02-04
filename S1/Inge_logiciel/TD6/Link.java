package inge_TD6;

public class Link extends Leaf {

	private Leaf element;
	
	public Link(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void cat(){
			element.cat();
	}
	
	public int nbElement(){
		return 1;
	}
}
