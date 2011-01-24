package com.exedosoft.wf;
import com.exedosoft.wf.wfi.WFEngineImpl;

public class WFEngineFactory {
  private WFEngineFactory() {
  }
  public static void main(String[] args) {
    WFEngineFactory WFEngineFactory1 = new WFEngineFactory();
  }
  public static WFEngine getWFEngine(){
    return new WFEngineImpl();
  }

}
