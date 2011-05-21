// **********************************************************************
//
// Copyright (c) 2003-2004 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 2.0.0

package Erp;

public final class _ScanSrvDelM extends Ice._ObjectDelM implements _ScanSrvDel
{
    public int
    isExist(String serialno, java.util.Map __ctx)
	throws IceInternal.NonRepeatable
    {
	IceInternal.Outgoing __out = getOutgoing("isExist", Ice.OperationMode.Normal, __ctx);
	try
	{
	    try
	    {
		IceInternal.BasicStream __os = __out.os();
		__os.writeString(serialno);
	    }
	    catch(Ice.LocalException __ex)
	    {
		__out.abort(__ex);
	    }
	    boolean __ok = __out.invoke();
	    try
	    {
		IceInternal.BasicStream __is = __out.is();
		if(!__ok)
		{
		    try
		    {
			__is.throwException();
		    }
		    catch(Ice.UserException __ex)
		    {
			throw new Ice.UnknownUserException();
		    }
		}
		int __ret;
		__ret = __is.readInt();
		return __ret;
	    }
	    catch(Ice.LocalException __ex)
	    {
		throw new IceInternal.NonRepeatable(__ex);
	    }
	}
	finally
	{
	    reclaimOutgoing(__out);
	}
    }

    public int
    saveImageFile(String fname, String serialno, byte[] data, String info, java.util.Map __ctx)
	throws IceInternal.NonRepeatable
    {
	IceInternal.Outgoing __out = getOutgoing("saveImageFile", Ice.OperationMode.Normal, __ctx);
	try
	{
	    try
	    {
		IceInternal.BasicStream __os = __out.os();
		__os.writeString(fname);
		__os.writeString(serialno);
		ByteSeqHelper.write(__os, data);
		__os.writeString(info);
	    }
	    catch(Ice.LocalException __ex)
	    {
		__out.abort(__ex);
	    }
	    boolean __ok = __out.invoke();
	    try
	    {
		IceInternal.BasicStream __is = __out.is();
		if(!__ok)
		{
		    try
		    {
			__is.throwException();
		    }
		    catch(Ice.UserException __ex)
		    {
			throw new Ice.UnknownUserException();
		    }
		}
		int __ret;
		__ret = __is.readInt();
		return __ret;
	    }
	    catch(Ice.LocalException __ex)
	    {
		throw new IceInternal.NonRepeatable(__ex);
	    }
	}
	finally
	{
	    reclaimOutgoing(__out);
	}
    }
}
