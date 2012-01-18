package com.zbwt.archives.util;

import java.util.Collection;
import java.util.Collections;

/**
 * 分页封装
 * @author lj
 *
 */
public class PageBean {
	private Collection objs = Collections.EMPTY_LIST;
	private int totalCount;
	private int pageNo;
	private int pageCount;
	private int totalPage;
	private String info;

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public boolean isNext() {
		return this.pageNo < this.totalPage;
	}

	public boolean isPrevious() {
		return this.pageNo > 1;
	}

	public Collection getObjs() {
		return this.objs;
	}

	public void setObjs(Collection objs) {
		this.objs = objs;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PageBean(Collection objs, Long totalCount, int pageNo, int pageCount) {
		this.objs = objs;
		this.totalCount = Integer.parseInt(totalCount.toString());
		this.pageNo = pageNo;
		this.pageCount = pageCount;
		if (totalCount % pageCount == 0)
			this.totalPage = (Integer.parseInt(totalCount.toString()) / pageCount);
		else
			this.totalPage = (Integer.parseInt(totalCount.toString())
					/ pageCount + 1);
	}
}
