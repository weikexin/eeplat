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

public abstract class _ScanSrvDisp extends Ice.ObjectImpl implements ScanSrv
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
	throws java.lang.CloneNotSupportedException
    {
	throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
	"::Erp::ScanSrv",
	"::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
	return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
	return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
	return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
	return __ids;
    }

    public String
    ice_id()
    {
	return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
	return __ids[0];
    }

    public static String
    ice_staticId()
    {
	return __ids[0];
    }

    public final int
    isExist(String serialno)
    {
	
	return isExist(serialno, null);
    }

    public final int
    saveImageFile(String fname, String serialno, byte[] data, String info)
    {
	
	return saveImageFile(fname, serialno, data, info, null);
    }

    public static IceInternal.DispatchStatus
    ___isExist(ScanSrv __obj, IceInternal.Incoming __in, Ice.Current __current)
    {
	IceInternal.BasicStream __is = __in.is();
	IceInternal.BasicStream __os = __in.os();
	String serialno;
	serialno = __is.readString();
	int __ret = __obj.isExist(serialno, __current);
	__os.writeInt(__ret);
	return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___saveImageFile(ScanSrv __obj, IceInternal.Incoming __in, Ice.Current __current)
    {
	IceInternal.BasicStream __is = __in.is();
	IceInternal.BasicStream __os = __in.os();
	String fname;
	fname = __is.readString();
	String serialno;
	serialno = __is.readString();
	byte[] data;
	data = ByteSeqHelper.read(__is);
	String info;
	info = __is.readString();
	int __ret = __obj.saveImageFile(fname, serialno, data, info, __current);
	__os.writeInt(__ret);
	return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
	"ice_id",
	"ice_ids",
	"ice_isA",
	"ice_ping",
	"isExist",
	"saveImageFile"
    };

    public IceInternal.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
	int pos = java.util.Arrays.binarySearch(__all, __current.operation);
	if(pos < 0)
	{
	    return IceInternal.DispatchStatus.DispatchOperationNotExist;
	}

	switch(pos)
	{
	    case 0:
	    {
		return ___ice_id(this, in, __current);
	    }
	    case 1:
	    {
		return ___ice_ids(this, in, __current);
	    }
	    case 2:
	    {
		return ___ice_isA(this, in, __current);
	    }
	    case 3:
	    {
		return ___ice_ping(this, in, __current);
	    }
	    case 4:
	    {
		return ___isExist(this, in, __current);
	    }
	    case 5:
	    {
		return ___saveImageFile(this, in, __current);
	    }
	}

	assert(false);
	return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
