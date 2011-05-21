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

public final class ScanSrvHolder
{
    public
    ScanSrvHolder()
    {
    }

    public
    ScanSrvHolder(ScanSrv value)
    {
	this.value = value;
    }

    public class Patcher implements IceInternal.Patcher
    {
	public void
	patch(Ice.Object v)
	{
	    value = (ScanSrv)v;
	}

	public String
	type()
	{
	    return "::Erp::ScanSrv";
	}
    }

    public Patcher
    getPatcher()
    {
	return new Patcher();
    }

    public ScanSrv value;
}
