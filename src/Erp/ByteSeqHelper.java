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

public final class ByteSeqHelper
{
    public static void
    write(IceInternal.BasicStream __os, byte[] __v)
    {
	__os.writeByteSeq(__v);
    }

    public static byte[]
    read(IceInternal.BasicStream __is)
    {
	byte[] __v;
	__v = __is.readByteSeq();
	return __v;
    }
}
