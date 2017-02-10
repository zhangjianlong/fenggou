package com.slash.youth.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.slash.youth.domain.CityHistoryEntity;
import com.slash.youth.domain.SearchHistoryEntity;

import com.slash.youth.gen.CityHistoryEntityDao;
import com.slash.youth.gen.SearchHistoryEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cityHistoryEntityDaoConfig;
    private final DaoConfig searchHistoryEntityDaoConfig;

    private final CityHistoryEntityDao cityHistoryEntityDao;
    private final SearchHistoryEntityDao searchHistoryEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cityHistoryEntityDaoConfig = daoConfigMap.get(CityHistoryEntityDao.class).clone();
        cityHistoryEntityDaoConfig.initIdentityScope(type);

        searchHistoryEntityDaoConfig = daoConfigMap.get(SearchHistoryEntityDao.class).clone();
        searchHistoryEntityDaoConfig.initIdentityScope(type);

        cityHistoryEntityDao = new CityHistoryEntityDao(cityHistoryEntityDaoConfig, this);
        searchHistoryEntityDao = new SearchHistoryEntityDao(searchHistoryEntityDaoConfig, this);

        registerDao(CityHistoryEntity.class, cityHistoryEntityDao);
        registerDao(SearchHistoryEntity.class, searchHistoryEntityDao);
    }
    
    public void clear() {
        cityHistoryEntityDaoConfig.getIdentityScope().clear();
        searchHistoryEntityDaoConfig.getIdentityScope().clear();
    }

    public CityHistoryEntityDao getCityHistoryEntityDao() {
        return cityHistoryEntityDao;
    }

    public SearchHistoryEntityDao getSearchHistoryEntityDao() {
        return searchHistoryEntityDao;
    }

}
