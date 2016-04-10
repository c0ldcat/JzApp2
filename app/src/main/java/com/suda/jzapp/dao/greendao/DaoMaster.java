package com.suda.jzapp.dao.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.suda.jzapp.dao.greendao.AccountDao;
import com.suda.jzapp.dao.greendao.RecordDao;
import com.suda.jzapp.dao.greendao.AccountTypeDao;
import com.suda.jzapp.dao.greendao.RecordTypeDao;
import com.suda.jzapp.dao.greendao.UserDao;
import com.suda.jzapp.dao.greendao.ConfigDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 5): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 5;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        AccountDao.createTable(db, ifNotExists);
        RecordDao.createTable(db, ifNotExists);
        AccountTypeDao.createTable(db, ifNotExists);
        RecordTypeDao.createTable(db, ifNotExists);
        UserDao.createTable(db, ifNotExists);
        ConfigDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        AccountDao.dropTable(db, ifExists);
        RecordDao.dropTable(db, ifExists);
        AccountTypeDao.dropTable(db, ifExists);
        RecordTypeDao.dropTable(db, ifExists);
        UserDao.dropTable(db, ifExists);
        ConfigDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            UpdateDB.updateDb(db, oldVersion, newVersion);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(AccountDao.class);
        registerDaoClass(RecordDao.class);
        registerDaoClass(AccountTypeDao.class);
        registerDaoClass(RecordTypeDao.class);
        registerDaoClass(UserDao.class);
        registerDaoClass(ConfigDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
