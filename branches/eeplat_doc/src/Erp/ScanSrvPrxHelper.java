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

public final class ScanSrvPrxHelper extends Ice.ObjectPrxHelperBase implements ScanSrvPrx
{
    public int
    isExist(String serialno)
    {
	return isExist(serialno, __defaultContext());
    }

    public int
    isExist(String serialno, java.util.Map __ctx)
    {
	int __cnt = 0;
	while(true)
	{
	    try
	    {
		__checkTwowayOnly("isExist");
		Ice._ObjectDel __delBase = __getDelegate();
		_ScanSrvDel __del = (_ScanSrvDel)__delBase;
		return __del.isExist(serialno, __ctx);
	    }
	    catch(IceInternal.NonRepeatable __ex)
	    {
		__rethrowException(__ex.get());
	    }
	    catch(Ice.LocalException __ex)
	    {
		__cnt = __handleException(__ex, __cnt);
	    }
	}
    }

    public int
    saveImageFile(String fname, String serialno, byte[] data, String info)
    {
	return saveImageFile(fname, serialno, data, info, __defaultContext());
    }

    public int
    saveImageFile(String fname, String serialno, byte[] data, String info, java.util.Map __ctx)
    {
	int __cnt = 0;
	while(true)
	{
	    try
	    {
		__checkTwowayOnly("saveImageFile");
		Ice._ObjectDel __delBase = __getDelegate();
		_ScanSrvDel __del = (_ScanSrvDel)__delBase;
		return __del.saveImageFile(fname, serialno, data, info, __ctx);
	    }
	    catch(IceInternal.NonRepeatable __ex)
	    {
		__rethrowException(__ex.get());
	    }
	    catch(Ice.LocalException __ex)
	    {
		__cnt = __handleException(__ex, __cnt);
	    }
	}
    }

    public static ScanSrvPrx
    checkedCast(Ice.ObjectPrx b)
    {
	ScanSrvPrx d = null;
	if(b != null)
	{
	    try
	    {
		d = (ScanSrvPrx)b;
	    }
	    catch(ClassCastException ex)
	    {
		if(b.ice_isA("::Erp::ScanSrv"))
		{
		    ScanSrvPrxHelper h = new ScanSrvPrxHelper();
		    h.__copyFrom(b);
		    d = h;
		}
	    }
	}
	return d;
    }

    public static ScanSrvPrx
    checkedCast(Ice.ObjectPrx b, String f)
    {
	ScanSrvPrx d = null;
	if(b != null)
	{
	    Ice.ObjectPrx bb = b.ice_newFacet(f);
	    try
	    {
		if(bb.ice_isA("::Erp::ScanSrv"))
		{
		    ScanSrvPrxHelper h = new ScanSrvPrxHelper();
		    h.__copyFrom(bb);
		    d = h;
		}
	    }
	    catch(Ice.FacetNotExistException ex)
	    {
	    }
	}
	return d;
    }

    public static ScanSrvPrx
    uncheckedCast(Ice.ObjectPrx b)
    {
	ScanSrvPrx d = null;
	if(b != null)
	{
	    ScanSrvPrxHelper h = new ScanSrvPrxHelper();
	    h.__copyFrom(b);
	    d = h;
	}
	return d;
    }

    public static ScanSrvPrx
    uncheckedCast(Ice.ObjectPrx b, String f)
    {
	ScanSrvPrx d = null;
	if(b != null)
	{
	    Ice.ObjectPrx bb = b.ice_newFacet(f);
	    ScanSrvPrxHelper h = new ScanSrvPrxHelper();
	    h.__copyFrom(bb);
	    d = h;
	}
	return d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
	return new _ScanSrvDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
	return new _ScanSrvDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, ScanSrvPrx v)
    {
	__os.writeProxy(v);
    }

    public static ScanSrvPrx
    __read(IceInternal.BasicStream __is)
    {
	Ice.ObjectPrx proxy = __is.readProxy();
	if(proxy != null)
	{
	    ScanSrvPrxHelper result = new ScanSrvPrxHelper();
	    result.__copyFrom(proxy);
	    return result;
	}
	return null;
    }
}
