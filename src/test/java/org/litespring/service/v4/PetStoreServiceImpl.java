package org.litespring.service.v4;/**
 * Created by LWD on 2018/8/9.
 */

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.stereotype.Component;

/**
 * @program: Litespring
 * @description: 测试
 * @author: JC.Lin
 * @createDate: 2018-08-09 23:58
 */
@Component(value="petStore")
public class PetStoreServiceImpl implements PetStoreService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
