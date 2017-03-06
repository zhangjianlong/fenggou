package com.slash.youth.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.slash.youth.domain.SearchHistoryEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SEARCH_HISTORY_ENTITY".
*/
public class SearchHistoryEntityDao extends AbstractDao<SearchHistoryEntity, Long> {

    public static final String TABLENAME = "SEARCH_HISTORY_ENTITY";

    /**
     * Properties of entity SearchHistoryEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property SearchHistory = new Property(1, String.class, "searchHistory", false, "SEARCH_HISTORY");
    };


    public SearchHistoryEntityDao(DaoConfig config) {
        super(config);
    }
    
    public SearchHistoryEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEARCH_HISTORY_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"SEARCH_HISTORY\" TEXT);"); // 1: searchHistory
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEARCH_HISTORY_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SearchHistoryEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String searchHistory = entity.getSearchHistory();
        if (searchHistory != null) {
            stmt.bindString(2, searchHistory);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SearchHistoryEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String searchHistory = entity.getSearchHistory();
        if (searchHistory != null) {
            stmt.bindString(2, searchHistory);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public SearchHistoryEntity readEntity(Cursor cursor, int offset) {
        SearchHistoryEntity entity = new SearchHistoryEntity( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // searchHistory
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SearchHistoryEntity entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setSearchHistory(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SearchHistoryEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SearchHistoryEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
