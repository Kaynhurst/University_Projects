public class Main {
	public static void main (String[] args) {
		
		Rectangle shape = new Rectangle() ;
		
		shape.setHeight(5.0) ;
		shape.setWidth(4.0) ;
		
		System.out.println("The perimeter of the shape is " + shape.getPerimeter() + " and the area is " + shape.getArea()) ;
	}
}