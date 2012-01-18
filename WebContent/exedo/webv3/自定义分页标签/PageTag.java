package com.zbwt.archives.util;

/**
 * 分页封装 lj
 * @author Administrator
 *
 */
public class PageTag {
	private int leftPages = 10;
	private int rightPages = 3;
	private int pageCount = 15;
	private String firstName = "|&lt;";
	private String lastName = "&gt;|";
	private String prevName = "&lt;";
	private String nextName = "&gt;";
	private boolean showFirst = true;
	private boolean showLast = true;
	private boolean showPrev = true;
	private boolean showNext = true;
	private PageBean pageBean;
	private String targetPage;
	private String paggingClass = "digg";
	private String tableClass = "c1";

	public String getPage() {
		if (this.pageBean == null)
			return null;
		StringBuffer total = new StringBuffer(1024);
		genPagging(total);
		return total.toString();
	}

	private void genPagging(StringBuffer total) {		
		total.append("共&nbsp;<span class=\"text_bl\">"+this.pageBean.getTotalCount()+"</span>&nbsp;条\n");
		if (this.showPrev) {
			if(this.pageBean.isPrevious()){
				total.append("<input class=\"btn_pre\" type=\"button\" value=\"\" onclick=\"gotoMyPage("+(this.pageBean.getPageNo()-1)+");\">\n");
			}
		}

		if (this.leftPages + this.rightPages + 1 >= this.pageBean
				.getTotalPage()) {
			for (int i = 1; i <= this.pageBean.getTotalPage(); ++i)
				writeHref(total, i);
		} else {
			int begin = 1;
			int end = this.pageBean.getTotalPage();
			if (this.pageBean.getPageNo() >= this.leftPages) {
				begin = this.pageBean.getPageNo() - this.leftPages + 2;
			}
			if (this.pageBean.getPageNo() + this.leftPages - 2 < this.pageBean
					.getTotalPage()) {
				end = this.pageBean.getPageNo() + this.leftPages - 2;
			}
			int middle = this.pageBean.getTotalPage() / 2;
			if (this.pageBean.getPageNo() < middle + 2) {
				for (int i = begin; i < this.leftPages + begin; ++i) {
					writeHref(total, i);
				}
				total.append("...");
				for (int i = this.pageBean.getTotalPage() - this.rightPages + 1; i <= this.pageBean
						.getTotalPage(); ++i)
					writeHref(total, i);
			} else {
				for (int i = 1; i <= this.rightPages; ++i) {
					writeHref(total, i);
				}
				total.append("...");
				for (int i = end - this.leftPages + 1; i <= end; ++i) {
					writeHref(total, i);
				}
			}
		}

		if (this.showNext) {
			if(this.pageBean.isNext()){
				total.append("<input class=\"btn_nxt\" type=\"button\" value=\"\" onclick=\"gotoMyPage("+(this.pageBean.getPageNo()+1)+");\">\n");
			}
		}
		
		total.append("&nbsp; &nbsp;到第\n"
					+"<input id=\"goto_page142d7bf5f-83c9-4f7c-9cb1-920a1029a1a9\" class=\"goto\" type=\"text\" name=\"goto_page142d7bf5f-83c9-4f7c-9cb1-920a1029a1a9\" value=\""+this.pageBean.getPageNo()+"\" size=\"1\" maxlength=\"3\">\n"
					+"页\n"
					+"<input class=\"btn_sty\" type=\"button\" onclick=\"gotoMyPage($('goto_page142d7bf5f-83c9-4f7c-9cb1-920a1029a1a9').value);\" size=\"1\" value=\"Go\">\n");
	}

	private void writeHref(StringBuffer total, int i) {
		if (i == this.pageBean.getPageNo())
			total.append("<strong>" + i + "</strong>\n");
		else
			total.append("<a href=\"javascript:gotoMyPage(" + i + ");\">" + i
					+ "</a>\n");
	}

	public String field2Method(String fieldName, String prefix) {
		StringBuffer buffer = new StringBuffer();
		char upperCaseChar = Character.toUpperCase(fieldName.charAt(0));
		buffer.append(prefix).append(upperCaseChar).append(
				fieldName.substring(1));
		return buffer.toString();
	}

	public int getLeftPages() {
		return this.leftPages;
	}

	public void setLeftPages(int leftPages) {
		this.leftPages = leftPages;
	}

	public PageBean getPageBean() {
		return this.pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getRightPages() {
		return this.rightPages;
	}

	public void setRightPages(int rightPages) {
		this.rightPages = rightPages;
	}

	public String getNextName() {
		return this.nextName;
	}

	public void setNextName(String nextName) {
		this.nextName = nextName;
	}

	public String getPrevName() {
		return this.prevName;
	}

	public void setPrevName(String prevName) {
		this.prevName = prevName;
	}

	public boolean isShowNext() {
		return this.showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public boolean isShowPrev() {
		return this.showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public String getTargetPage() {
		return this.targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isShowFirst() {
		return this.showFirst;
	}

	public void setShowFirst(boolean showFirst) {
		this.showFirst = showFirst;
	}

	public boolean isShowLast() {
		return this.showLast;
	}

	public void setShowLast(boolean showLast) {
		this.showLast = showLast;
	}

	public String getPaggingClass() {
		return this.paggingClass;
	}

	public void setPaggingClass(String paggingClass) {
		this.paggingClass = paggingClass;
	}

	public String getTableClass() {
		return this.tableClass;
	}

	public void setTableClass(String tableClass) {
		this.tableClass = tableClass;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
