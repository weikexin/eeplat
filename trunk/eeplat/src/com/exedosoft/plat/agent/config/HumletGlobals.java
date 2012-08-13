package com.exedosoft.plat.agent.config;



public class HumletGlobals {


	private static String serverName;


	private static String port;


	private static String cmdSvrBlockMax;
	

	private static String inQueueMax;
	

	private static String processMax;	
		

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
