import java.awt.Color;

public class Vehicle {
	private static int topSpeed;
	private static int length;
	private static String name;
	private static int width;

	Vehicle(String name, int topSpeed, int width, int length) {
		Vehicle.topSpeed = topSpeed;
		Vehicle.length = length;
		Vehicle.width = width;
		Vehicle.name = name;
		try {
			if (length > 120 || width > 60) {

				throw new InvalidParametersEntered();
			}
		} catch (InvalidParametersEntered e) {
			// TODO Auto-generated catch block
			
			System.out.println("ERROR: INVALID PARAMETERS ENTERED");
			e.printStackTrace();
		}

	}

	public void setTopSpeed(int n) {
		topSpeed = n;
	}

	public void setWidth(int n) {
		try {
			if (length > 120 || width > 60) {

				throw new InvalidParametersEntered();
			}
		} catch (InvalidParametersEntered e) {
			// TODO Auto-generated catch block
			
			System.out.println("ERROR: INVALID PARAMETERS ENTERED");
			e.printStackTrace();
		}
		width = n;
	}

	public void setName(String n) {
		name = n;
	}

	public void setLength(int n) {
		try {
			if (length > 120 || width > 60) {

				throw new InvalidParametersEntered();
			}
		} catch (InvalidParametersEntered e) {
			// TODO Auto-generated catch block
			
			System.out.println("ERROR: INVALID PARAMETERS ENTERED");
			e.printStackTrace();
	
		}
		length = n;
	}

	public static int getTopSpeed() {
		return topSpeed;
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}
}
