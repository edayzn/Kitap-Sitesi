package admin.bean;

import admin.dao.BookDao;
import admin.entity.Book;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class BookBean implements Serializable {

    private Book book;
    private BookDao bookDao;
    private List<Book> bookList;
    private List<Book> bookPageList;

    @Inject
    private AuthorBean authorBean;

    @Inject
    private CategoryBean categoryBean;

    private int page = 1;

    private int listItemCount = 5;

    private int pageCount;

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void create() {
        this.getBookDao().create(this.book);
        this.clearForm();
    }

    public void clearForm() {
        this.book = new Book();
    }

    public void deleteConfirm(Book book) {
        this.book = book;

    }

    public void delete() {
        this.getBookDao().delete(this.book);
        this.book = new Book();
        this.clearForm();
    }

    public void updateForm(Book ur) {
        this.book = ur;
    }

    public void update() {
        this.getBookDao().update(this.book);
        this.book = new Book();
        this.clearForm();
    }

    public Book getBook() {
        if (this.book == null) {
            this.book = new Book();
        }
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookDao getBookDao() {
        if (this.bookDao == null) {
            this.bookDao = new BookDao();
        }
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getBookList() {
        this.bookList = this.getBookDao().list();
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookPageList() {
        this.bookPageList = this.getBookDao().pagedList(this.page, this.listItemCount);
        return bookPageList;
    }

    public void setBookPageList(List<Book> bookPageList) {
        this.bookPageList = bookPageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getBookDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getListItemCount() {
        return listItemCount;
    }

    public void setListItemCount(int listItemCount) {
        this.listItemCount = listItemCount;
    }

    public AuthorBean getAuthorBean() {
        if (this.authorBean == null) {
            this.authorBean = new AuthorBean();
        }
        return authorBean;
    }

    public CategoryBean getCategoryBean() {
        if (this.categoryBean == null) {
            this.categoryBean = new CategoryBean();
        }
        return categoryBean;
    }

}
