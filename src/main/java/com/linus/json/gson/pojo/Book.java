package com.linus.json.gson.pojo;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Book {
	@Expose
	private Author author = new Author();
	
	@Expose
	public String title = "hello world";

	@Expose
	public Date publishDate;
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * Author must be public, otherwise, author's fields are accessible from book instance.
	 * @author lyan2
	 *
	 */
	class Author {
		@Expose
		private String name = "linus";

		public String getName() {
			return name;
		}
	}
}
