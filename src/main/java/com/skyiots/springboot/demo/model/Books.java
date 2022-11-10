package com.skyiots.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

	private long id;
	private String bookId;
	private String bookTitle;
	private String bookDesc;
	private Double price;
	private int readStatus;
	private int ratings;
	private String authorName;

	public Books() {

	}

	public Books(String bookId, String bookTitle, String bookDesc, Double price, int readStatus, int ratings, String authorName) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookDesc = bookDesc;
		this.price = price;
		this.readStatus = readStatus;
		this.ratings = ratings;
		this.authorName = authorName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "bookId", nullable = false)
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name = "bookTitle", nullable = false)
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Column(name = "bookDesc")
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "readStatus", nullable = false)
	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	@Column(name = "ratings", nullable = false)
	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	@Column(name = "authorName", nullable = false)
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "Books [id=" + id + ", bookId=" + bookId + ", bookDesc=" + bookDesc + ", price=" + price + ", readStatus=" + readStatus+ ", "
				+ "ratings=" + ratings+ "authorName=" + authorName
				+ "]";
	}
}
