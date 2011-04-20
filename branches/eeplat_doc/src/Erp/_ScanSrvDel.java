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

public interface _ScanSrvDel extends Ice._ObjectDel
{
    int isExist(String serialno, java.util.Map __ctx)
	throws IceInternal.NonRepeatable;

    int saveImageFile(String fname, String serialno, byte[] data, String info, java.util.Map __ctx)
	throws IceInternal.NonRepeatable;
}
