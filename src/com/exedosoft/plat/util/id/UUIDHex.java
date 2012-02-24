package com.exedosoft.plat.util.id;



/**
 * <b>uuid.hex</b><br> <br> A <tt>UUIDGenerator</tt> that returns a string of length 32, This string will consist of only hex digits. Optionally, the string may be generated with seperators between each component of the UUID.
 * @see UUIDStringGenerator
 * @author   Gavin King
 */

public class UUIDHex extends UUIDBase {

	private String sep = "";

	private static UUIDHex builder;

	private static Object lockObj = new Object();// lock object

	
	private UUIDHex(){
		
	}
	
	/**
	 * Gets only one UUIDHex object
	 * 
	 * @return
	 */
	public static UUIDHex getInstance() {

		if (builder == null) {
			synchronized (lockObj) {
				if (builder == null) {
					builder = new UUIDHex();
				}
			}
		}
		return builder;
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public String generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(
				format(getJVM())).append(sep).append(format(getHiTime()))
				.append(sep).append(format(getLoTime())).append(sep).append(
						format(getCount())).toString();
	}

	public static void main(String[] args) throws Exception {

		UUIDHex gen = new UUIDHex();
		gen.sep = "";

		for (int i = 0; i < 10; i++) {
			String id = (String) gen.generate();
			System.out.println(id);

		}
	}

}
