/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import java.sql.Connection;
import utility.ConnectionManager;

/**
 *
 * @author essah
 */
public abstract class Connect {
    
    private ConnectionManager d;
    private Connection c;
        public ConnectionManager getD() {
        if (this.d == null) {
            this.d = ConnectionManager.getInstance();
        }
        return d;
    }

    public void setD(ConnectionManager d) {
        this.d = d;
    }

    public Connection getC() {
        if (this.c == null) {
            this.c = this.getD().getConnection();
        }
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }
}
