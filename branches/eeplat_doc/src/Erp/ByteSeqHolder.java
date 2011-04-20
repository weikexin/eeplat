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

public final class ByteSeqHolder
{
    public
    ByteSeqHolder()
    {
    }

    public
    ByteSeqHolder(byte[] value)
    {
	this.value = value;
    }

    public byte[] value;
}
