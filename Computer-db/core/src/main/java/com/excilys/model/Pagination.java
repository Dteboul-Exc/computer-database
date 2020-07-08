package com.excilys.model;

public class Pagination {
	private long limit, offset;

	public Pagination() {
	}

	private void check() {
		if ((limit < 1) || (offset < 0))
			throw new IllegalArgumentException("Limit or index were set at less than 0");
		if (limit < offset)
			throw new IllegalArgumentException("Limit must not be inferior to offset");
	}

	public Pagination(int limit, int offset) {
		this.limit = limit;
		this.offset = offset;
	}
}
