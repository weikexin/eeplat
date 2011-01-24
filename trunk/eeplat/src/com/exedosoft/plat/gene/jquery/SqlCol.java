package com.exedosoft.plat.gene.jquery;
import java.sql.Types;

/**
 * @author  IBM
 */
public class SqlCol {
  /**
 * @uml.property  name="name"
 */
private String name;
  /**
 * @uml.property  name="dataType"
 */
private int dataType;
  /**
 * @uml.property  name="nullable"
 */
private String nullable;
  /**
 * @uml.property  name="scale"
 */
private int scale;
  /**
 * @uml.property  name="size"
 */
private int size;
  public SqlCol() {
  }
  /**
 * @return
 * @uml.property  name="name"
 */
public String getName() {
    return name;
  }
  /**
 * @param name
 * @uml.property  name="name"
 */
public void setName(String name) {
    this.name = name;
  }
  /**
 * @return
 * @uml.property  name="dataType"
 */
public int getDataType() {
	  if((dataType == Types.DECIMAL || dataType== Types.NUMERIC)
			  && this.getScale() ==0
			  ){
		  return Types.BIGINT;
	  }
    return dataType;


  }
  /**
 * @param dateType
 * @uml.property  name="dataType"
 */
public void setDataType(int dateType) {
    this.dataType = dateType;
  }
  public boolean isIdentity() {
    return false;
  }

  public boolean isDate() {
    if (this.getDataType() == Types.DATE
        || this.getDataType() == Types.TIMESTAMP) {
      return true;
    }
    return false;
  }
  public boolean isNumber(){
    if (this.getDataType() == Types.BIGINT
        || this.getDataType() == Types.DECIMAL
        || this.getDataType() == Types.DOUBLE
        || this.getDataType() == Types.FLOAT
        || this.getDataType() == Types.INTEGER
        || this.getDataType() == Types.NUMERIC
        || this.getDataType() == Types.REAL
        || this.getDataType() == Types.SMALLINT
        || this.getDataType() == Types.TINYINT
        ) {
      return true;
    }
    return false;
  }

  public boolean isAllowedNull(){

    if("NO".equals(nullable)){
      return false;
    }else{
      return true;
    }
  }


  public String toString(){
    StringBuffer buffer = new StringBuffer();
    buffer.append("ColName:")
        .append(this.getName())
        .append(";")
        .append("Col Type:")
        .append(this.getDataType())
        .append(";")
        .append("NullAble:")
        .append(this.getNullable())
        .append(";")
        .append("Is Date:")
        .append(this.isDate())
        .append(";")
        .append("Is Number:")
        .append(this.isNumber())
        .append(";")
        .append("isAllowedNull:")
        .append(this.isAllowedNull())
        .append("size:")
        .append(this.getSize())
        .append("\n");

    return buffer.toString();
  }
  /**
 * @return
 * @uml.property  name="nullable"
 */
public String getNullable() {
    return nullable;
  }
  /**
 * @param nullable
 * @uml.property  name="nullable"
 */
public void setNullable(String nullable) {
    this.nullable = nullable;
  }

  public void dealBuffer(StringBuffer strBuffer){
    strBuffer = new StringBuffer("2222");
  }

  public static void main(String[] args){
//    StringBuffer  buffer = new StringBuffer("1111");
//    SqlCol sc = new SqlCol();
//    sc.dealBuffer(buffer);
    System.out.println(System.getProperties());
    

  }
/**
 * @return
 * @uml.property  name="scale"
 */
public int getScale() {
	return scale;
}
/**
 * @param scale
 * @uml.property  name="scale"
 */
public void setScale(int scale) {
	this.scale = scale;
}
/**
 * @return
 * @uml.property  name="size"
 */
public int getSize() {
	return size;
}
/**
 * @param size
 * @uml.property  name="size"
 */
public void setSize(int size) {
	this.size = size;
}


}
