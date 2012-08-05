package morlok8k.minecraft.landgenerator;

import java.util.regex.*;

/**
 * Coordinates are in the form of [x,y,z] or (X,Z)<br>
 * <br>
 * x-axis (longitude): the distance east (positive) or west (negative) of the origin point<br>
 * z-axis (latitude): the distance south (positive) or north (negative) of the origin point<br>
 * y-axis (elevation): how high or low (from 0 to 255 (previously 128), with 64 being sea level) <br>
 * The origin point: When both x and z are both zero. (elevation is irrelevant)<br>
 */
public class Coordinates {
	//FyI: int's (Integer's) are good enough for Minecraft.  They have a range of -2,147,483,648 to 2,147,483,647
	//		Minecraft starts failing around (+/-) 12,550,820 and ends at either (+/-) 30,000,000 or (+/-) 32,000,000 (depending on the version).
	// See: http://www.minecraftwiki.net/wiki/Far_Lands for more info.
    //If you need more, just use longs which should be just as fast on 64-bit (i.e. most modern) machines.

	private int x = 0;
	private int y = 0;
	private int z = 0;

	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void setX(int x) {
		x = x;
	}

	public void setY(int y) {
		y = y;
	}

	public void setZ(int z) {
		z = z;
	}

    /**
     * Parses a Coordinates object from a String. Leading and trailing garbage is ignored (FIXME).
     * @param s A short- or long-form coordinate string as described at the two toString() methods
     * @throws IllegalArgumentException if s does not match a short or long form coordinate
     */
	public static Coordinates parseString(String s) {
        Matcher shortForm = Pattern.compile("\\((\\d+),(\\d+)\\)").matcher(s);
        if(shortForm.matches()){
            return new Coordinates(Interger.parseInt(shortForm.group(1)), 64, Integer.parseInt(shortForm.group(2)));
        }
        Matcher normalForm = Pattern.compile("\\[(\\d+),(\\d+),(\\d+)\\]").matcher(s);
        if(normalForm.matches()){
            return new Coordinates(Interger.parseInt(shortForm.group(1)), Integer.parseInt(shortForm.group(2)), Integer.parseInt(shortForm.group(3)));
        }
        throw new InvalidArgumentException("Invalid coordinate format: "+s);
	}

    /**
     * @return A string representation of the form [x,y,z]
	 */
	@Override
	public String toString() {
		return ("[" + x + "," + y + "," + z + "]");

	}

    /**
     * @return A short string representation containing just X and Z values (no height value) of the form (x,z)
     */
	public String toStringShort() {
        return ("(" + x + "," + z + ")");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
            return false;
		if(!obj instanceof Coordinates)
            return false;

		Coordinates c = (Coordinates)obj;
		if (x == c.getX() && y == c.getY() && z == c.getZ())
            return true;
        return false;
}
