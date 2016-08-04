package com.suda.jzapp.dao.greendao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "REMARK_TIP".
 */
@Entity
public class RemarkTip {

    @Id(autoincrement = true)
    private Long id;
    private Integer useTimes;
    private String remark;
    private Boolean SyncStatus;
    private Boolean isDel;

    @Generated(hash = 1211701810)
    public RemarkTip() {
    }

    public RemarkTip(Long id) {
        this.id = id;
    }

    @Generated(hash = 75873709)
    public RemarkTip(Long id, Integer useTimes, String remark, Boolean SyncStatus, Boolean isDel) {
        this.id = id;
        this.useTimes = useTimes;
        this.remark = remark;
        this.SyncStatus = SyncStatus;
        this.isDel = isDel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Integer useTimes) {
        this.useTimes = useTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

}
