package inge_TD6;

public class File extends Leaf{

	private String content;
	
	
	public File(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public int nbElement(){
		return content.length(); 
	}
	
	public void cat(){
		System.out.println(content);
	}

}
