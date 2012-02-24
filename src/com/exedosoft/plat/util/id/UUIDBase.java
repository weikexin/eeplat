//$Id: UUIDGenerator.java,v 1.6 2003/06/17 09:32:36 oneovthafew Exp $
package com.exedosoft.plat.util.id;

import java.net.InetAddress;




/**
 * The base class for identifier generators that use a UUID algorithm. This class implements the algorithm, subclasses define the identifier  format.
 * @see UUIDHex
 * @see UUIDStringGenerator
 * @author  Gavin King
 */

public abstract class UUIDBase {
	
	/**
	 * @uml.property  name="iP"
	 */
	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = BytesHelper.toInt( InetAddress.getLocalHost().getAddress() );
		}
		catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	/**
	 * @uml.property  name="jVM"
	 */
	private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );
	
	public UUIDBase() {
	}
	
	/**
	 * Unique across JVMs on this machine (unless they load this class in the same quater second - very unlikely)
	 * @uml.property  name="jVM"
	 */
	protected int getJVM() {
		return JVM;
	}
	
	/**
	 * Unique in a millisecond for this JVM instance (unless there
	 * are > Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized(UUIDBase.class) {
			if (counter<0) counter=0;
			return counter++;
		}
	}
	
	/**
	 * Unique in a local network
	 * @uml.property  name="iP"
	 */
	protected int getIP() {
		return IP;
	}
	
	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) ( System.currentTimeMillis() >>> 32 );
	}
	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}
	
	
}


final class BytesHelper {
	
	private BytesHelper() {}
	
	public static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	
	public static short toShort( byte[] bytes ) {
		return (short) ( ( ( - (short) Byte.MIN_VALUE + (short) bytes[0] ) << 8  ) - (short) Byte.MIN_VALUE + (short) bytes[1] );
	}
	
	public static byte[] toBytes(int value) {
		byte[] result = new byte[4];
		for (int i=3; i>=0; i--) {
			result[i] = (byte) ( ( 0xFFl & value ) + Byte.MIN_VALUE );
			value >>>= 8;
		}
		return result;
	}
	
	public static byte[] toBytes(short value) {
		byte[] result = new byte[2];
		for (int i=1; i>=0; i--) {
			result[i] = (byte) ( ( 0xFFl & value )  + Byte.MIN_VALUE );
			value >>>= 8;
		}
		return result;
	}
	
	public static void main( String[] args ) {
		System.out.println( 0 +"=="+ BytesHelper.toInt( BytesHelper.toBytes(0) ) );
		System.out.println( 1 +"=="+ BytesHelper.toInt( BytesHelper.toBytes(1) ) );
		System.out.println( -1 +"=="+ BytesHelper.toInt( BytesHelper.toBytes(-1) ) );
		System.out.println( Integer.MIN_VALUE +"=="+ BytesHelper.toInt( BytesHelper.toBytes(Integer.MIN_VALUE) ) );
		System.out.println( Integer.MAX_VALUE +"=="+ BytesHelper.toInt( BytesHelper.toBytes(Integer.MAX_VALUE) ) );
		System.out.println( Integer.MIN_VALUE / 2 +"=="+ BytesHelper.toInt( BytesHelper.toBytes(Integer.MIN_VALUE / 2) ) );
		System.out.println( Integer.MAX_VALUE / 2 +"=="+ BytesHelper.toInt( BytesHelper.toBytes(Integer.MAX_VALUE / 2) ) );
	}
	
}





