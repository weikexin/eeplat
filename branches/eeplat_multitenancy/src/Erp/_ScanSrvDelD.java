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

public final class _ScanSrvDelD extends Ice._ObjectDelD implements _ScanSrvDel
{
    public int
    isExist(String serialno, java.util.Map __ctx)
	throws IceInternal.NonRepeatable
    {
	Ice.Current __current = new Ice.Current();
	__initCurrent(__current, "isExist", Ice.OperationMode.Normal, __ctx);
	while(true)
	{
	    IceInternal.Direct __direct = new IceInternal.Direct(__current);
	    try
	    {
		ScanSrv __servant = null;
		try
		{
		    __servant = (ScanSrv)__direct.servant();
		}
		catch(ClassCastException __ex)
		{
		    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
		    __opEx.id = __current.id;
		    __opEx.facet = __current.facet;
		    __opEx.operation = __current.operation;
		    throw __opEx;
		}
		try
		{
		    return __servant.isExist(serialno, __current);
		}
		catch(Ice.LocalException __ex)
		{
		    throw new IceInternal.NonRepeatable(__ex);
		}
	    }
	    finally
	    {
		__direct.destroy();
	    }
	}
    }

    public int
    saveImageFile(String fname, String serialno, byte[] data, String info, java.util.Map __ctx)
	throws IceInternal.NonRepeatable
    {
	Ice.Current __current = new Ice.Current();
	__initCurrent(__current, "saveImageFile", Ice.OperationMode.Normal, __ctx);
	while(true)
	{
	    IceInternal.Direct __direct = new IceInternal.Direct(__current);
	    try
	    {
		ScanSrv __servant = null;
		try
		{
		    __servant = (ScanSrv)__direct.servant();
		}
		catch(ClassCastException __ex)
		{
		    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
		    __opEx.id = __current.id;
		    __opEx.facet = __current.facet;
		    __opEx.operation = __current.operation;
		    throw __opEx;
		}
		try
		{
		    return __servant.saveImageFile(fname, serialno, data, info, __current);
		}
		catch(Ice.LocalException __ex)
		{
		    throw new IceInternal.NonRepeatable(__ex);
		}
	    }
	    finally
	    {
		__direct.destroy();
	    }
	}
    }
}
