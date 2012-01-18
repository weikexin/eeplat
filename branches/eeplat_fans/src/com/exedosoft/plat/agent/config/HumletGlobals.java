package com.exedosoft.plat.agent.config;

/**
 * 宯?揑慡嬊攝抲掱彉丅 曪妵Agent?峴揑抧毈丆抂岥摍丅
 * @author  Administrator
 */

public class HumletGlobals {

	/**
	 * 暈?婍柤徧
	 * @uml.property  name="serverName"
	 */
	private static String serverName;

	/**
	 * 暈?婍抂岥
	 * @uml.property  name="port"
	 */
	private static String port;

	/**
	 * CommanSerlet暈?婍擻愙庴揑嵟戝媞?抂?媮?楍?搙丅
	 * @uml.property  name="cmdSvrBlockMax"
	 */
	private static String cmdSvrBlockMax;
	
	/**
	 * inpool 嵟戝?楍?搙
	 * @uml.property  name="inQueueMax"
	 */
	private static String inQueueMax;
	
	/**
	 * 嵟懡?棟揑徚懅悢
	 * @uml.property  name="processMax"
	 */
	private static String processMax;	
		
	/**
	 * 媞?抂?愙挻???丅
	 * @uml.property  name="timeOut"
	 */
	private static String timeOut;

	static {

		serverName = "127.0.0.1";
		port = "315";
		timeOut = "1000";
		cmdSvrBlockMax = "10";
		processMax = "10";
	}

	/**
	 * @return
	 * @uml.property  name="serverName"
	 */
	public static String getServerName() {
		return serverName;
	}

	/**
	 * @return
	 * @uml.property  name="port"
	 */
	public static int getPort() {
		return Integer.parseInt(port);
	}

	/**
	 * @return
	 * @uml.property  name="timeOut"
	 */
	public static int getTimeOut() {
		return Integer.parseInt(timeOut);
	}
	
	/**
	 * @return
	 * @uml.property  name="cmdSvrBlockMax"
	 */
	public static int getCmdSvrBlockMax(){
		return Integer.parseInt(cmdSvrBlockMax);		
	}

	/**
	 * @return
	 * @uml.property  name="processMax"
	 */
	public static int getProcessMax() {
		return Integer.parseInt(processMax);
	}
	
	/**
	 * @return
	 * @uml.property  name="inQueueMax"
	 */
	public static int getInQueueMax(){
		return Integer.parseInt(inQueueMax);
	}

	
	

}
