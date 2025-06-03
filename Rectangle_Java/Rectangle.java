public class Rectangle {
	// Object Variables
	
	private double width ;
	private double height ;
	
	// Object Setters
	
	public void setWidth (double rWidth){
		width = rWidth ;
	}
	
	public void setHeight (double rHeight){
		height = rHeight ;
	}
	
	// Object Getters
	
	public double getWidth (){
		return width ;
	}
	
	public double getHeight(){
		return height ;
	}
	
	// Object Methods
	
	public double getPerimeter () {
		return (getHeight()*2) + (getWidth()*2) ;
	}
	
	public double getArea () {
		return getHeight()*getWidth() ;
	}
	
}