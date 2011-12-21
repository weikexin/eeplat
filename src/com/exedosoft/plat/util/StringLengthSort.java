package com.exedosoft.plat.util;

public class StringLengthSort implements Comparable {
	
	
	private String src;
	
	
	public StringLengthSort(String aSrc){
		this.src = aSrc;	
				
	}
	
	public String getString(){
		
		return this.src;
	}
	
	public int getStringLength(){
		
		if(this.src!=null){
			return this.src.length();
		}
		return 0;
	}
	
	
	

	public int compareTo(Object arg0) {
		
		
		if(!(arg0 instanceof StringLengthSort)){
			return 0;
			
		}
		StringLengthSort sls = (StringLengthSort)arg0;
		if(src!=null){
			
			if(sls.getStringLength() > this.getStringLength()){
				
				return 1;
			}else{
				return -1;
			}
			
		}
		// TODO 自动生成方法存根
		return 0;
	}

}
