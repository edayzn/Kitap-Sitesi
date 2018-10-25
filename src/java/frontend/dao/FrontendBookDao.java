package frontend.dao;

import admin.dao.AuthorDao;
import admin.dao.CategoryDao;
import admin.dao.CommentDao;
import admin.dao.Connect;
import admin.dao.FileDao;
import admin.dao.LikeDao;
import admin.entity.Book;
import admin.entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FrontendBookDao extends Connect {

    private CategoryDao categoryDao;
    private LikeDao likeDao;
    private FileDao fileDao;
    private CommentDao commentDao;
    private AuthorDao authorDao;

    public AuthorDao getAuthorDao() {
        if (this.authorDao == null) {
            this.authorDao = new AuthorDao();
        }
        return authorDao;
    }

    public CategoryDao getCategoryDao() {
        if (this.categoryDao == null) {
            this.categoryDao = new CategoryDao();
        }
        return categoryDao;
    }

    public LikeDao getLikeDao() {
        if (this.likeDao == null) {
            this.likeDao = new LikeDao();
        }
        return likeDao;
    }

    public FileDao getFileDao() {
        if (this.fileDao == null) {
            this.fileDao = new FileDao();
        }
        return fileDao;
    }

    public CommentDao getCommentDao() {
        if (this.commentDao == null) {
            this.commentDao = new CommentDao();
        }
        return commentDao;
    }

    public ArrayList<Book> list(Category u) {
        ArrayList<Book> bookList = new ArrayList();
        try {
            if (u != null && u.getCategory_id() != null) {
                PreparedStatement pst = this.getC().prepareStatement("select * from book where category_id=?");
                pst.setLong(1, u.getCategory_id());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Book tmp = new Book();
                    tmp.setBook_id(rs.getLong("book_id"));
                    tmp.setBook_name(rs.getString("book_name"));
                    tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                    tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                    tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
                    tmp.setBookCommentList(this.getCommentDao().BookYorums(tmp.getBook_id()));
                    tmp.setBookImageList(this.getFileDao().bookResimler1(tmp.getBook_id()));
                    tmp.setBookLikeCount(this.getLikeDao().likeCount(tmp.getBook_id()));
                    tmp.setBookDisLikeCount(this.getLikeDao().disLikeCount(tmp.getBook_id()));
                    tmp.setBookCommentCount(this.getCommentDao().commentCount(tmp.getBook_id()));
                    bookList.add(tmp);
                }
            } else {
                PreparedStatement pst1 = this.getC().prepareStatement("select * from book");
                ResultSet rs = pst1.executeQuery();
                while (rs.next()) {
                    Book tmp = new Book();
                    tmp.setBook_id(rs.getLong("book_id"));
                    tmp.setBook_name(rs.getString("book_name"));
                    tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                    tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                    tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
                    tmp.setBookCommentList(this.getCommentDao().BookYorums(tmp.getBook_id()));
                    tmp.setBookImageList(this.getFileDao().bookResimler1(tmp.getBook_id()));
                     tmp.setBookLikeCount(this.getLikeDao().likeCount(tmp.getBook_id()));
                    tmp.setBookDisLikeCount(this.getLikeDao().disLikeCount(tmp.getBook_id()));
                    tmp.setBookCommentCount(this.getCommentDao().commentCount(tmp.getBook_id()));
                    bookList.add(tmp);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    public Book detail(Long id) {
        Book tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from book where book_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Book();
                tmp.setBook_id(rs.getLong("book_id"));
                tmp.setBook_name(rs.getString("book_name"));
                tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
                tmp.setBookCommentList(this.getCommentDao().BookYorums(tmp.getBook_id()));
                tmp.setBookImageList(this.getFileDao().bookResimler(tmp.getBook_id()));
                 tmp.setBookLikeCount(this.getLikeDao().likeCount(tmp.getBook_id()));
                    tmp.setBookDisLikeCount(this.getLikeDao().disLikeCount(tmp.getBook_id()));
                    tmp.setBookCommentCount(this.getCommentDao().commentCount(tmp.getBook_id()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public Book detail1(Book id) {
        Book tmp = null;
        try {
            if (id != null && id.getBook_id() != null) {
                PreparedStatement pst = this.getC().prepareStatement("select * from book where book_id=?");
                pst.setLong(1, id.getBook_id());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    tmp = new Book();
                    tmp.setBook_id(rs.getLong("book_id"));
                    tmp.setBook_name(rs.getString("book_name"));
                    tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                    tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                    tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
                    tmp.setBookCommentList(this.getCommentDao().BookYorums(tmp.getBook_id()));
                    tmp.setBookImageList(this.getFileDao().bookResimler(tmp.getBook_id()));
                     tmp.setBookLikeCount(this.getLikeDao().likeCount(tmp.getBook_id()));
                    tmp.setBookDisLikeCount(this.getLikeDao().disLikeCount(tmp.getBook_id()));
                    tmp.setBookCommentCount(this.getCommentDao().commentCount(tmp.getBook_id()));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }
}
