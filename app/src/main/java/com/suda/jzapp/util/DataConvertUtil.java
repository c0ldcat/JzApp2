package com.suda.jzapp.util;

import com.suda.jzapp.dao.greendao.Account;
import com.suda.jzapp.dao.greendao.AccountType;
import com.suda.jzapp.manager.domain.AccountDetailDO;

/**
 * Created by ghbha on 2016/2/16.
 */
public class DataConvertUtil {
    public static AccountDetailDO getAccountDetailDO(Account account, AccountType accountType) {

        AccountDetailDO accountDetailDO = new AccountDetailDO();

        if (account == null) {
            return null;
        }

        accountDetailDO.setId(account.getId());
        accountDetailDO.setAccountID(account.getAccountID());
        accountDetailDO.setAccountTypeID(account.getAccountTypeID());
        accountDetailDO.setAccountName(account.getAccountName());
        accountDetailDO.setAccountMoney(account.getAccountMoney());
        accountDetailDO.setAccountRemark(account.getAccountRemark());
        accountDetailDO.setAccountColor(account.getAccountColor());
        accountDetailDO.setSyncStatus(account.getSyncStatus());
        accountDetailDO.setIsDel(account.getIsDel());
        accountDetailDO.setAccountDesc(accountType.getAccountDesc());
        accountDetailDO.setAccountIcon(accountType.getAccountIcon());

        return accountDetailDO;
    }
}
