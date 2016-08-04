package com.suda.jzapp.dao.greendao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "RECORD".
 */
@Entity
public class Record implements java.io.Serializable {

    @Id(autoincrement = true)
    private Long id;
    private Long RecordId;
    private Double RecordMoney;
    private Long RecordTypeID;
    private Integer RecordType;
    private Long AccountID;
    private java.util.Date RecordDate;
    private String Remark;
    private Boolean SyncStatus;
    private Boolean isDel;
    private String ObjectID;
    private Integer year;
    private Integer month;
    private Integer day;

    @Generated(hash = 477726293)
    public Record() {
    }

    public Record(Long id) {
        this.id = id;
    }

    @Generated(hash = 728084580)
    public Record(Long id, Long RecordId, Double RecordMoney, Long RecordTypeID, Integer RecordType, Long AccountID, java.util.Date RecordDate, String Remark, Boolean SyncStatus, Boolean isDel, String ObjectID, Integer year, Integer month, Integer day) {
        this.id = id;
        this.RecordId = RecordId;
        this.RecordMoney = RecordMoney;
        this.RecordTypeID = RecordTypeID;
        this.RecordType = RecordType;
        this.AccountID = AccountID;
        this.RecordDate = RecordDate;
        this.Remark = Remark;
        this.SyncStatus = SyncStatus;
        this.isDel = isDel;
        this.ObjectID = ObjectID;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return RecordId;
    }

    public void setRecordId(Long RecordId) {
        this.RecordId = RecordId;
    }

    public Double getRecordMoney() {
        return RecordMoney;
    }

    public void setRecordMoney(Double RecordMoney) {
        this.RecordMoney = RecordMoney;
    }

    public Long getRecordTypeID() {
        return RecordTypeID;
    }

    public void setRecordTypeID(Long RecordTypeID) {
        this.RecordTypeID = RecordTypeID;
    }

    public Integer getRecordType() {
        return RecordType;
    }

    public void setRecordType(Integer RecordType) {
        this.RecordType = RecordType;
    }

    public Long getAccountID() {
        return AccountID;
    }

    public void setAccountID(Long AccountID) {
        this.AccountID = AccountID;
    }

    public java.util.Date getRecordDate() {
        return RecordDate;
    }

    public void setRecordDate(java.util.Date RecordDate) {
        this.RecordDate = RecordDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public Boolean getSyncStatus() {
        return SyncStatus;
    }

    public void setSyncStatus(Boolean SyncStatus) {
        this.SyncStatus = SyncStatus;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String ObjectID) {
        this.ObjectID = ObjectID;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
