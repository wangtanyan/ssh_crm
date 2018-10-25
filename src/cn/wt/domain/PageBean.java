package cn.wt.domain;

import java.util.List;

public class PageBean {
	private Integer currentPage;	//当前页
	private Integer totalPage;		//总页数
	private Integer totalCount;		//总记录数
	private Integer pageSize;		//每页记录数
	//private Integer begin;			//开始位置
	private List<?> list;	//每页显示的记录
	private String uri;
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	/*public Integer getTotalPage() {
		int tp = totalCount/pageSize;
		return (totalCount%pageSize>0) ? tp+1 : tp;
	}*/
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
//	public Integer getBegin() {
//		return begin;
//	}
//	public void setBegin(Integer begin) {
//		this.begin = begin;
//	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ ", pageSize=" + pageSize + ", list=" + list + ", uri=" + uri + "]";
	}
	
	
}
