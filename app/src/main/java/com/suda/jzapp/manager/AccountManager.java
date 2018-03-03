package com.suda.jzapp.manager;

import android.content.Context;
import android.os.Handler;

import com.suda.jzapp.dao.greendao.Account;
import com.suda.jzapp.dao.greendao.AccountType;
import com.suda.jzapp.dao.local.account.AccountLocalDao;
import com.suda.jzapp.dao.local.conf.ConfigLocalDao;
import com.suda.jzapp.dao.local.record.RecordLocalDAO;
import com.suda.jzapp.manager.domain.AccountDetailDO;
import com.suda.jzapp.misc.Constant;
import com.suda.jzapp.util.DataConvertUtil;
import com.suda.jzapp.util.MoneyUtil;
import com.suda.jzapp.util.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suda on 2015/11/16.
 */
public class AccountManager extends BaseManager {
    public AccountManager(Context context) {
        super(context);
    }

    /**
     * 根据类型匹配合适的账户
     *
     * @param recordTypeId
     * @return
     */
    public Account getSuitAccount(long recordTypeId) {
        long accountId = recordLocalDAO.getMoreUseAccountByRecord(_context, recordTypeId);
        if (accountId > 0) {
            Account account = accountLocalDao.getAccountByID(accountId, _context);
            if (account != null && account.getIsDel() == false)
                return account;
            else
                return accountLocalDao.getSuitAccount(_context);
        } else
            return accountLocalDao.getSuitAccount(_context);
    }


    /**
     * 根据id查账户详细
     *
     * @param accountID
     * @return
     */
    public AccountDetailDO getAccountByID(final long accountID) {
        Account account = accountLocalDao.getAccountByID(accountID, _context);
        if (account == null)
            return null;
        AccountType accountType = accountLocalDao.getAccountTypeByID(account.getAccountTypeID(), _context);
        AccountDetailDO accountDetailDO = DataConvertUtil.getAccountDetailDO(account, accountType);
        return accountDetailDO;
    }

    /**
     * 根据id查账户类型
     *
     * @param accountTypeId
     * @param handler
     */
    public void getAccountTypeByID(final long accountTypeId, final Handler handler) {
        ThreadPoolUtil.getThreadPoolService().execute(new Runnable() {
            @Override
            public void run() {
                AccountType accountType = accountLocalDao.getAccountTypeByID(accountTypeId, _context);
                sendMessage(handler, accountType, accountType != null);
            }
        });
    }

    /**
     * 获取所有账户信息
     *
     * @param handler
     */
    public void getAllAccount(final Handler handler) {

        ThreadPoolUtil.getThreadPoolService().execute(new Runnable() {
            @Override
            public void run() {
                List<Account> accounts = accountLocalDao.getAllAccount(_context);
                List<AccountDetailDO> accountDetailDOs = new ArrayList<AccountDetailDO>();
                for (Account account : accounts) {
                    AccountType accountType = accountLocalDao.getAccountTypeByID(account.getAccountTypeID(), _context);
                    AccountDetailDO accountDetailDO = DataConvertUtil.getAccountDetailDO(account, accountType);
                    accountDetailDO.setTodayCost(recordLocalDAO.countTodayCostByAccountId(_context, account.getAccountID()));
                    accountDetailDOs.add(accountDetailDO);
                }
                sendMessage(handler, accountDetailDOs);
            }
        });
    }

    /**
     * 查询所有账户类型
     *
     * @param handler
     */
    public void getAllAccountType(final Handler handler) {
        ThreadPoolUtil.getThreadPoolService().execute(new Runnable() {
            @Override
            public void run() {
                List<AccountType> accountTypes = accountLocalDao.getAllAccountType(_context);
                sendMessage(handler, accountTypes);
            }
        });
    }

    /**
     * 创建新账户
     *
     * @param accountName
     * @param accountMoney
     * @param accountTypeID
     * @param accountRemark
     * @param handler
     */
    public void createNewAccount(String accountName, double accountMoney, int accountTypeID, String accountRemark, int accountColor, final Handler handler) {
        final Account account = new Account();
        account.setAccountID(System.currentTimeMillis());
        account.setAccountColor("");
        account.setAccountName(accountName);
        account.setAccountMoney(accountMoney);
        account.setAccountTypeID(accountTypeID);
        account.setAccountRemark(accountRemark);
        account.setSyncStatus(true);
        account.setIsDel(false);
        account.setAccountColor(accountColor + "");
        account.setSyncStatus(false);
        accountLocalDao.createNewAccount(account, _context);
        sendEmptyMessage(handler, Constant.MSG_SUCCESS);
    }

    /**
     * 删除账户
     *
     * @param accountID
     * @param handler
     */
    public void deleteAccountByID(final long accountID, final Handler handler) {
        editAccount(EDIT_TYPE_DEL, accountID, null, 0, 0, null, new Callback() {
            @Override
            public void doSth(boolean isSync, String objId) {
                accountLocalDao.deleteAccount(accountID, true, objId, _context);
            }
        }, handler);
    }

    /**
     * 更新账户名
     *
     * @param accountID
     * @param accountName
     * @param handler
     */
    public void updateAccountName(final long accountID, final String accountName, final Handler handler) {
        editAccount(EDIT_TYPE_ACCOUNT_NAME, accountID, null, 0, 0, accountName, new Callback() {
            @Override
            public void doSth(boolean isSync, String objId) {
                accountLocalDao.updateAccountName(accountID, accountName, isSync, objId, _context);
            }
        }, handler);
    }


    /**
     * 更新账户类型
     *
     * @param accountID
     * @param typeID
     * @param handler
     */
    public void updateAccountTypeID(final long accountID, final int typeID, final Handler handler) {
        editAccount(EDIT_TYPE_ACCOUNT_TYPE, accountID, null, typeID, 0, null, new Callback() {
            @Override
            public void doSth(boolean isSync, String objId) {
                accountLocalDao.updateAccountTypeID(accountID, typeID, isSync, objId, _context);
            }
        }, handler);
    }

    /**
     * 更新账户余额
     *
     * @param accountID
     * @param money
     * @param handler
     */
    public void updateAccountMoney(final long accountID, final double money, final Handler handler) {
        Account account = accountLocalDao.getAccountByID(accountID, _context);
        if (account == null)
            return;
        editAccount(EDIT_TYPE_ACCOUNT_MONEY, accountID, null, 0, MoneyUtil.getFormatNum(account.getAccountMoney() + money), null, new Callback() {
            @Override
            public void doSth(boolean isSync, String objId) {
                accountLocalDao.updateAccountMoney(accountID, money, isSync, objId, _context);
            }
        }, handler);
    }


    /**
     * 更新账户说明
     *
     * @param accountID
     * @param remark
     * @param handler
     */
    public void updateAccountRemark(final long accountID, final String remark, final Handler handler) {
        editAccount(EDIT_TYPE_ACCOUNT_REMARK, accountID, remark, 0, 0, null, new Callback() {
            @Override
            public void doSth(boolean isSync, String objId) {
                accountLocalDao.updateAccountRemark(accountID, remark, isSync, objId, _context);
            }
        }, handler);
    }

    /**
     * 更新账户颜色
     *
     * @param accountID
     * @param color
     * @param handler
     */
    public void updateAccountColor(final long accountID, final String color, final Handler handler) {
        editAccount(EDIT_TYPE_ACCOUNT_COLOR, accountID, color, 0, 0, null, new Callback() {
            @Override
            public void doSth(boolean isSync, String objId) {
                accountLocalDao.updateAccountColor(accountID, color, isSync, objId, _context);
            }
        }, handler);
    }


    /**
     * 修改账户通用方法
     *
     * @param editType
     * @param accountID
     * @param remark
     * @param typeID
     * @param money
     * @param accountName
     * @param callback
     * @param handler
     */
    private void editAccount(final int editType, final long accountID, final String remark, final int typeID, final double money, final String accountName,
                             final Callback callback, final Handler handler) {
        callback.doSth(false, null);
        sendEmptyMessage(handler, Constant.MSG_SUCCESS);
    }

    /**
     * 更新账户排序
     *
     * @param handler
     * @param list
     */
    public void updateAccountIndex(final Handler handler, List<AccountDetailDO> list) {
        if (list != null) {
            accountLocalDao.updateAccountIndex(_context, list);
        }
    }

    public void updateBudget(double money) {
        AccountLocalDao accountLocalDao = new AccountLocalDao();
        accountLocalDao.updateBudget(_context, money);
    }

    private final static int EDIT_TYPE_DEL = -1;
    private final static int EDIT_TYPE_ACCOUNT_NAME = 0;
    private final static int EDIT_TYPE_ACCOUNT_MONEY = 1;
    private final static int EDIT_TYPE_ACCOUNT_TYPE = 2;
    private final static int EDIT_TYPE_ACCOUNT_REMARK = 3;
    private final static int EDIT_TYPE_ACCOUNT_COLOR = 4;


    private AccountLocalDao accountLocalDao = new AccountLocalDao();
    private RecordLocalDAO recordLocalDAO = new RecordLocalDAO();
    private ConfigLocalDao configLocalDao = new ConfigLocalDao();
    private final static String ACCOUNT_INDEX_UPDATE = "ACCOUNT_INDEX_UPDATE";
}
