package com.exedosoft.plat.action.wf;

public class DOTypes {

	public final static int TYPE_ACTIVITY = 1;

	public final static int TYPE_SERVICE_AUTO = 21;

	public final static int TYPE_SELF = 11;

	/**
	 * 自动的会签支持，依据DOAuthorization的getAuthConfigUsers 每个会签结果保存为xml文件。
	 * 
	 */

	public final static int TYPE_SELF_CONJUNCTION = 16;

	public final static int TYPE_START = 2;

	public final static int TYPE_END = 3;

	public final static int TYPE_AND_DECISION = 4;

	public final static int TYPE_XOR_DECISION = 5;

	/**
	 * 有分就要有合，有decision就要有conjunction,所以还不能去掉专门的会签定义。
	 */

	public final static int TYPE_AND_CONJUNCTION = 6;

	public final static int TYPE_OR_CONJUNCTION = 7;

	public final static int TYPE_SUBPROCESS = 8;

	public final static int DECISION_TYPE_SCRIPT = 1;

	public final static int DECISION_TYPE_JAVA = 2;
	
	
	
	
	
	
	
	
	/**
	 * 节点执行状态
	 */

	public final static int STATUS_FREE = 0;

	public final static int STATUS_INIT = 1;

	public final static int STATUS_RUN = 2;

	public final static int STATUS_FINISH = 3;

	public final static int STATUS_REJECT = 7;

	public final static int STATUS_HANGUP = 4;

	public final static int STATUS_RESUMING = 5;

	/**
	 * 回退
	 */
	public final static int BACK_RETURN = 1;

	/**
	 * 取回
	 */
	public final static int BACK_WITHDRAW = 2;
	
	
	

}
