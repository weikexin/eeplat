package com.exedosoft.plat.agent.config;

/**
 * 系?的全局配置程序。 包括Agent?行的地址，端口等。
 * @author  Administrator
 */

public class HumletGlobals {

	/**
	 * 服?器名称
	 * @uml.property  name="serverName"
	 */
	private static String serverName;

	/**
	 * 服?器端口
	 * @uml.property  name="port"
	 */
	private static String port;

	/**
	 * CommanSerlet服?器能接受的最大客?端?求?列?度。
	 * @uml.property  name="cmdSvrBlockMax"
	 */
	private static String cmdSvrBlockMax;
	
	/**
	 * inpool 最大?列?度
	 * @uml.property  name="inQueueMax"
	 */
	private static String inQueueMax;
	
	/**
	 * 最多?理的消息数
	 * @uml.property  name="processMax"
	 */
	private static String processMax;	
		
	/**
	 * 客?端?接超???。
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
