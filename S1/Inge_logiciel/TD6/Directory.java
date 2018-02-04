package inge_TD6;

import java.util.ArrayList;
import java.util.Collection;

public class Directory extends AbstractElement{

	private Collection<AbstractElement> elements;
	
	public Directory(String name) {
		super(4,name);
		// TODO Auto-generated constructor stub
	}

	
	public int nbElement(){
		return elements.size();
	}

	public void ls(){
		for(AbstractElement element : elements){
			System.out.println(element.getAddress());
		}
	}
	
	public Collection<String> find(String name){
		Collection<String> elements_find = new ArrayList<String>();
		for(AbstractElement element: elements){
			if(name.equals(element.getAddress()))
				elements_find.add(element.getAddress());
		}
		return elements_find;
	}
	
	public Collection<String> findR(String name){
		Collection<String> elements_find = new ArrayList<String>();
		return findRAux(name,elements_find);
		
	}
	
	public Collection<String> findRAux(String name, Collection<String> elements_find){
		for(AbstractElement element: elements){
			if(name.equals(element.getAddress()))
				if(element instanceof Directory){
					((Directory) element).findRAux(name, elements_find);
				}
				elements_find.add(element.getAddress());
		}
		return elements_find;
	}
	

}
