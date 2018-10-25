/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Eda Yazan
 */
public class Book {

    private Long book_id;
    private String book_name;
    private Date date_of_issue;
    private Category category;
    private Author author;
    private ArrayList<File1> bookImageList;
    private ArrayList<Comment> bookCommentList;
    private ArrayList<Like> bookLikeList;
    private int bookLikeCount;
    private int bookDisLikeCount;
    private int bookCommentCount;

    public Book() {
    }

    public Long getBook_id() {
        return book_id;
    }

    public int getBookDisLikeCount() {
        return bookDisLikeCount;
    }

    public void setBookDisLikeCount(int bookDisLikeCount) {
        this.bookDisLikeCount = bookDisLikeCount;
    }

    public int getBookCommentCount() {
        return bookCommentCount;
    }

    public void setBookCommentCount(int bookCommentCount) {
        this.bookCommentCount = bookCommentCount;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Date getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(Date date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getBookLikeCount() {
        return bookLikeCount;
    }

    public void setBookLikeCount(int bookLikeCount) {
        this.bookLikeCount = bookLikeCount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.book_id, other.book_id);
    }

    public ArrayList<File1> getBookImageList() {
        return bookImageList;
    }

    public void setBookImageList(ArrayList<File1> bookImageList) {
        this.bookImageList = bookImageList;
    }

    public ArrayList<Comment> getBookCommentList() {
        return bookCommentList;
    }

    public void setBookCommentList(ArrayList<Comment> bookCommentList) {
        this.bookCommentList = bookCommentList;
    }

    public ArrayList<Like> getBookLikeList() {
        return bookLikeList;
    }

    public void setBookLikeList(ArrayList<Like> bookLikeList) {
        this.bookLikeList = bookLikeList;
    }

}
