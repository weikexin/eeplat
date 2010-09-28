package com.exedosoft.wf;

import com.exedosoft.wf.wfi.NodeInstance;

/**
 * isAccess 参数采用NodeInstance 而不是 BOInstance
 * 主要是isAccess 可以方便判断前驱 后继等
 * @author anolesoft
 *
 */
public interface WFAccess {
	
	boolean isAccess(NodeInstance ni);

}
