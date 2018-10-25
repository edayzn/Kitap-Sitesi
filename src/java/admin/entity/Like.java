/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.entity;

import java.util.Objects;

/**
 *
 * @author Eda Yazan
 */
public class Like {

    private Long begeni_id;
    private boolean begenme;
    private User user;
    private Book book;

    public Like() {
    }

    public Long getBegeni_id() {
        return begeni_id;
    }

    public void setBegeni_id(Long begeni_id) {
        this.begeni_id = begeni_id;
    }

    public boolean isBegenme() {
        return begenme;
    }

    public void setBegenme(boolean begenme) {
        this.begenme = begenme;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.begeni_id);
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
        final Like other = (Like) obj;
        return Objects.equals(this.begeni_id, other.begeni_id);
    }

}
