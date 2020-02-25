package com.julioalfaro.ejemplodb;

import android.app.Application;

import com.julioalfaro.ejemplodb.utilities.DBUtility;

public class AppEjemplo extends Application {
    private DBUtility conn;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    DBUtility getDBUtility() {
        if (conn == null) {
            conn = new DBUtility(this);
        }
        return conn;
    }
}
