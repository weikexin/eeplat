package com.exedosoft.wf;


import com.exedosoft.wf.pt.ProcessTemplate;
import com.exedosoft.wf.wfi.NodeInstance;
import com.exedosoft.wf.wfi.ProcessInstance;

import java.util.Collection;

/**
 * 要做到流程和界面分离
 * @author Administrator
 *
 */

public interface WFEngine {

  /**
   * 根据过程模板和参数启动一个工作流实例。
   * @param pt 工作流模板
   * @param wfiParameters 启动工作流机所需要的参数
   * @return 工作流实例
   */
  ProcessInstance startProcess(ProcessTemplate pt) throws WFException;
  
  
  /**
   * 启动工作流实例，但是第一个节点并不提交
   * @param pt
   * @return
   * @throws WFException
   */
  NodeInstance initProcess(ProcessTemplate pt) throws WFException;
  

  /**
   * 可以发起的流程
   * @param pi
   * @return
   * @throws WFException
   */
  Collection getProcesses() throws WFException;

  /**
   * 恢复挂起/重新执行该流程。
   * @param pi 流程实例
   * @return
   * @throws WFException
   */
  ProcessInstance reStartProcess(ProcessInstance pi) throws WFException;

  /**
   * 根据工作流模板返回由该模板生成的所有实例
   * @param pt 工作流模板
   * @return 实例集合
   */
  Collection getProcessInstances(ProcessTemplate pt) throws WFException;

  /**
   * 根据工作流模板唯一主键加载工作流模板
   * @param processUID  工作流模板唯一主键
   * @return 工作流模板
   */
  ProcessTemplate loadProcessTemplate(String ptUID) throws WFException;


  /**
   * 根据工作流实例唯一主键加载工作流实例
   * @param piUID
   * @return
   */
  ProcessInstance loadProcessInstance(String piUID) throws WFException;

  /**
   * 无条件杀死一个工作流实例。
   * @param piUID 工作流实例唯一标示。
   * @throws WFException
   */
  void killProcessInstance(String piUID) throws WFException;

  /**
   * 挂起一个工作流实例。
   * @param piUID  工作流实例唯一标示
   * @throws WFException
   */
  ProcessInstance hangUpProcessInstance(String piUID) throws WFException;

//  /** 如果多个分支??
//   * 获得给定工作流实例的最后一个未执行的Node
//   * @param piUID 工作流实例UID
//   * @return 工作流实例
//   * @throws WFException 工作流异常
//   */
//  NodeInstance  getLastFreeNodeInstance(Long piUID) throws WFException;
//
//
//  /**
//   * 获得给定工作流实例的最后一个未执行的Node
//   * @param piUID 工作流实例UID
//   * @return 工作流实例
//   * @throws WFException 工作流异常
//   */
//  ActionInstance  getLastFreeNodeInstance(Long piUID) throws WFException;
//


  /**
   * 从某个NodeInstance 重新开始执行
   * @param nodeUID
   * @return
   * @throws WFException
   */
  NodeInstance reStartFromNodeInstance(String nodUID) throws WFException;


  /**
   * 挂起某个nodeInstance
   * @param piUID
   * @return
   * @throws WFException
   */
  NodeInstance hangUpNodeInstance(String nodeUID) throws WFException;


  /**
   * 在给定节点实例ni前面插入一个NodeInstance
   * @throws WFException
   */
  NodeInstance insertBeforNodeInstance(NodeInstance ni, NodeInstance insertNi) throws
      WFException;

  /**
   * 在给定节点实例ni后面插入一个NodeInstance
   * @throws WFException
   */
  NodeInstance insertAfterNodeInstance(NodeInstance ni, NodeInstance insertNi) throws
      WFException;


  /**
   *删除给定的动作实例
   * @throws WFException
   */
  void deleteNodeInstance(NodeInstance ni) throws WFException;
  
  

  /**
   * 待办，根据员工唯一标示，获取该员工将待办工作的集合
   * @param 员工唯一标示
   */
  Collection getMyPending() throws WFException;

  /**
   * 待办，根据员工唯一标示，获取该员工将待办工作的集合
   * @param 员工唯一标示
   * @param 工作流模板唯一标示
   */
  Collection getMyPending(String processTemplateUID) throws
      WFException;



  /*
   * 已办，根据员工唯一标示，获取该员工已办工作的集合
   * @param 员工唯一标示
   */
  Collection getHaveDone() throws WFException;

  /**
   * 已办，根据员工唯一标示和工作流模板，获取该员工在该工作流程中已办工作的集合
   * @param 员工唯一标示
   * @param 过程模板唯一标示
   */
  Collection getHaveDone(String processTemplateUID) throws
      WFException;


  /**
   * 办结 ，根据员工唯一标示，获取该员工办结工作集合
   * @param 员工唯一标示
   */
  Collection getMyResult() throws WFException;

  /**
   * 办结，根据员工唯一标示和工作流模板唯一标示，获取该员工办结工作的集合
   * @param 员工唯一标示
   */
  Collection getMyResult(String processTemplateUID) throws
      WFException;

}
