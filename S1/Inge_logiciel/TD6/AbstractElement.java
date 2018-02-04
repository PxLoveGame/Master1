package inge_TD6;

public abstract class AbstractElement {
	
	private int basicSize;
	private String address;
	
	
	public AbstractElement(int basicSize, String name){
		this.basicSize = basicSize;
		this.address = name;
	}
	
	public int getSize(){
		return basicSize;
	}
	public void setSize(int s){
		basicSize = s;
	}
	

	public String getAddress(){
		return address;
	}
	public void setAddress(String n){
		address = n;
	}
	
	public abstract int nbElement();
}
