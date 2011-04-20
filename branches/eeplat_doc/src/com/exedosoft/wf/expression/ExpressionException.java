package com.exedosoft.wf.expression;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ExpressionException extends Exception {

  public ExpressionException() {
  }

  public ExpressionException(String msg)
  {
    super(msg);
  }
  public static void main(String[] args) {
    ExpressionException expressionException1 = new ExpressionException();
  }
}
