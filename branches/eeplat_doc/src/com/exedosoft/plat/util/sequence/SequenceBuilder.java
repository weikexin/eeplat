package com.exedosoft.plat.util.sequence;

//DOCodeItem Defined
//public static final int ITEM_TYPE_SEQUENCE = 2;
//public static final int ITEM_TYPE_SEQUENCE_SIMPLE = 22;
//public static final int ITEM_TYPE_SEQUENCE_YEAR = 25;
//public static final int ITEM_TYPE_SEQUENCE_DEPT = 27;

public interface SequenceBuilder {
	
	String getString(String codeItemID, String aDeptCode);
	
	Long getLong(String codeItemID, String aDeptCode);

}
