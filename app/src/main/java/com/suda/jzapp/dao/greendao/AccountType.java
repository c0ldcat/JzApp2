package com.suda.jzapp.dao.greendao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ACCOUNT_TYPE".
 */
@Entity
public class AccountType {

    @Id
    private Long id;
    private Integer AccountTypeID;
    private String AccountDesc;
    private Integer AccountIcon;

    @Generated(hash = 551127513)
    public AccountType() {
    }

    public AccountType(Long id) {
        this.id = id;
    }

    @Generated(hash = 370868745)
    public AccountType(Long id, Integer AccountTypeID, String AccountDesc, Integer AccountIcon) {
        this.id = id;
        this.AccountTypeID = AccountTypeID;
        this.AccountDesc = AccountDesc;
        this.AccountIcon = AccountIcon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(Integer AccountTypeID) {
        this.AccountTypeID = AccountTypeID;
    }

    public String getAccountDesc() {
        return AccountDesc;
    }

    public void setAccountDesc(String AccountDesc) {
        this.AccountDesc = AccountDesc;
    }

    public Integer getAccountIcon() {
        return AccountIcon;
    }

    public void setAccountIcon(Integer AccountIcon) {
        this.AccountIcon = AccountIcon;
    }

}
