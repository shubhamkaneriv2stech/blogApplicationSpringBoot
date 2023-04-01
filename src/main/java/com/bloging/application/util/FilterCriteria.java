package com.bloging.application.util;



import java.io.Serializable;

public class FilterCriteria implements Serializable {
	private static final long serialVersionUID = -99352342336441L;

	private int offset;

	private int limit;

	private String search;

	public FilterCriteria(int offset, int limit, String search) {
		super();
		this.offset = offset;
		this.limit = limit;
		this.search = search;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "FilterCriteria [offset=" + offset + ", limit=" + limit + ", search=" + search + "]";
	}

}
