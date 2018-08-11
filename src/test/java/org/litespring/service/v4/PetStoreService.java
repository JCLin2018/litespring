package org.litespring.service.v4;

import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;

/**
 * Created by LWD on 2018/8/11.
 */
public interface PetStoreService {
    public AccountDao getAccountDao();

    public ItemDao getItemDao();
}
