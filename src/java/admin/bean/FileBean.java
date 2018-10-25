package admin.bean;

import admin.dao.FileDao;
import admin.entity.File1;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class FileBean implements Serializable {

    private File1 file;
    private Part part;
    private FileDao fileDao;
    private ArrayList<File1> dlist;
    private ArrayList<File1> filePageList;

    private final String uploadPath = "C:\\Server\\";
    private int page = 1;
    private int listItemCount = 3;
    private int pageCount;

    @Inject
    private BookBean bookBean;

    public BookBean getBookBean() {
        if (this.bookBean == null) {
            this.bookBean = new BookBean();
        }
        return bookBean;
    }

    public void upload() {
        try {
            InputStream input = part.getInputStream();
            File f = new File(this.uploadPath + part.getSubmittedFileName());
            Files.copy(input, f.toPath(), REPLACE_EXISTING);

            String format = "dd-MM-yyyy-hhmmss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String extension = f.getName().substring(f.getName().lastIndexOf("."));
            String fileName = sdf.format(new Date()) + extension;
            File newFile = new File(this.uploadPath + fileName);
            f.renameTo(newFile);

            this.file.setName(newFile.getName());
            this.file.setPath(newFile.getParent());
            this.file.setType(part.getContentType());
            this.getFileDao().create(this.file);

        } catch (IOException ex) {
            System.out.println("Upload File hata=" + ex.getLocalizedMessage());
        }
    }

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

    public void clearForm() {
        this.file = new File1();
    }

    public void deleteConfirm(File1 ud) {
        this.file = ud;

    }

    public void delete() {
        this.getFileDao().delete(this.file);
        this.file = new File1();
        this.clearForm();
    }

    public void updateForm(File1 ud) {
        this.file = ud;
    }

    public void update() {
        this.getFileDao().update(this.file);
        this.file = new File1();
        this.clearForm();
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public ArrayList<File1> getDlist() {
        this.dlist = new ArrayList();
        this.dlist = this.getFileDao().list();
        return dlist;
    }

    public void setDlist(ArrayList<File1> dlist) {
        this.dlist = dlist;
    }

    public ArrayList<File1> getFilePageList() {
        this.filePageList = new ArrayList();
        this.filePageList = this.getFileDao().pagedList(this.page, this.listItemCount);
        return filePageList;
    }

    public void setFilePageList(ArrayList<File1> filePageList) {
        this.filePageList = filePageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getFileDao().itemCountTop() / (double) listItemCount);
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

    public FileDao getFileDao() {
        if (this.fileDao == null) {
            this.fileDao = new FileDao();
        }
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public File1 getFile() {
        if (this.file == null) {
            this.file = new File1();
        }
        return file;
    }

    public void setFile(File1 file) {
        this.file = file;
    }

}
