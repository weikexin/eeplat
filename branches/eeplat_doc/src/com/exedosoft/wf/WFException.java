package com.exedosoft.wf;
/**
 *
 * <p>Title: </p>
 * <p>工作流引擎异常，工作流运行或交互过程中可能出现的异常、错误。</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class WFException extends Exception {

  public WFException() {
   super("工作流引擎异常");
  }

  public WFException(String message) {
    super(message);
  }

  public WFException(String message, Throwable cause) {
    super(message, cause);
  }

  public WFException(Throwable cause) {
    super(cause);
  }
  public static void main(String[] args) {
    WFException WFException1 = new WFException();
  }
}
