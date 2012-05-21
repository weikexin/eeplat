package com.exedosoft.wf;

import com.exedosoft.wf.wfi.NodeInstance;

/**
 * isAccess 参数采用NodeInstance 而不是 BOInstance
 * 主要是isAccess 范围 true 或 false
 * 
 * 有两个作用:
 * 1, 判断NodeInstance 的可见性，当NodeInstance的权限验证类别为java时<br/>
 * 2, 当NodeInstance类型为Self(11)时，判断自连接是否还进行下去，为false时，自连接停止，继续往下走，但是自连接的关系需要自己维护
 * 
 * @author wkx
 *
 */
public interface WFAccess {
	
	boolean isAccess(NodeInstance ni);

}
