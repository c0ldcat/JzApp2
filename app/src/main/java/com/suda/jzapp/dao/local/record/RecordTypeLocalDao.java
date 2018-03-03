package com.suda.jzapp.dao.local.record;

import android.content.Context;

import com.suda.jzapp.dao.greendao.RecordType;
import com.suda.jzapp.dao.greendao.RecordTypeDao;
import com.suda.jzapp.dao.local.BaseLocalDao;
import com.suda.jzapp.manager.domain.RecordTypeIndexDO;
import com.suda.jzapp.misc.Constant;

import java.util.List;

/**
 * Created by ghbha on 2016/2/25.
 */
public class RecordTypeLocalDao extends BaseLocalDao {

    public void createOrUpdateRecordType(Context context, RecordType recordType) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        RecordType recordTypeOld = getSingleData(recordTypeDao.queryBuilder()
                .whereOr(RecordTypeDao.Properties.RecordTypeID.eq(recordType.getRecordTypeID())
                        , RecordTypeDao.Properties.ObjectID.eq(recordType.getObjectID())).build().list());
        if (recordTypeOld != null) {
            recordType.setId(recordTypeOld.getId());
            updateRecordType(context, recordType);
        } else {
            createNewRecordType(context, recordType);
        }
    }

    public boolean haveCreate(Context context, String recordDesc, int recordType) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        return getSingleData(recordTypeDao.queryBuilder().
                where(RecordTypeDao.Properties.IsDel.eq(false))
                .where(RecordTypeDao.Properties.RecordDesc.eq(recordDesc))
                .where(RecordTypeDao.Properties.RecordType.eq(recordType)).list()) != null;
    }

    public void createNewRecordType(Context context, RecordType recordType) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        recordTypeDao.insert(recordType);
    }

    public void update2DelSysType(Context context) {
        String sql = "update RECORD_TYPE set IS_DEL =1 where SYS_TYPE =1";
        getDaoSession(context).getDatabase().execSQL(sql);
    }

    public void updateRecordTypeIndex(Context context, RecordTypeIndexDO recordTypeIndexDO) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        RecordType recordType = getRecordTypeByRecordTypeId(context, recordTypeIndexDO.getRecordTypeID());
        if (recordType == null)
            return;
        if (recordType.getSysType()) {
            recordType.setIsDel(false);
        }
        recordType.setIndex(recordTypeIndexDO.getIndex());
        recordTypeDao.update(recordType);
    }

    public void clearAllRecordType(Context context) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        recordTypeDao.deleteAll();
    }

    public List<RecordType> getAllZhiChuRecordType(Context context) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        return recordTypeDao.queryBuilder().
                where(RecordTypeDao.Properties.IsDel.eq(false))
                .whereOr(RecordTypeDao.Properties.RecordType.eq(Constant.RecordType.ZUICHU.getId()),
                        RecordTypeDao.Properties.RecordType.eq(Constant.RecordType.AA_ZHICHU.getId()))
                .orderAsc(RecordTypeDao.Properties.Index)
                .build()
                .list();
    }

    public List<RecordType> getAllShouRuRecordType(Context context) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        return recordTypeDao.queryBuilder().
                where(RecordTypeDao.Properties.IsDel.eq(false))
                .whereOr(RecordTypeDao.Properties.RecordType.eq(Constant.RecordType.SHOURU.getId()),
                        RecordTypeDao.Properties.RecordType.eq(Constant.RecordType.AA_SHOURU.getId()))
                .orderAsc(RecordTypeDao.Properties.Index)
                .build()
                .list();
    }

    public RecordType getRecordTypeByRecordTypeId(Context context, long id) {
        return getRecordTypeByRecordTypeId(context, id, false);
    }

    public RecordType getRecordTypeByRecordTypeId(Context context, long id, boolean canNull) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        RecordType recordType = getSingleData(recordTypeDao.queryBuilder()
                .where(RecordTypeDao.Properties.RecordTypeID.eq(id))
                .build()
                .list());
        if (recordType == null && !canNull)
            recordType = new RecordType(0L, 0L, "一般", Constant.RecordType.ZUICHU.getId(), true, Constant.RecordTypeConstant.ICON_TYPE_YI_BAN, 0, Constant.Sex.ALL.getId(), Constant.Occupation.ALL.getId(), true, false, "");
        return recordType;
    }


    public RecordType getRecordTypeByNameAndType(Context context, String name, int recordType) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        return getSingleData(recordTypeDao.queryBuilder()
                .where(RecordTypeDao.Properties.IsDel.eq(false))
                .where(RecordTypeDao.Properties.RecordDesc.eq(name))
                .where(RecordTypeDao.Properties.RecordType.eq(recordType))
                .build()
                .list());
    }

    public void updateRecordOrder(Context context, List<RecordType> list) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        //去除加号
        list.remove(list.size() - 1);

        int i = 0;
        //待同步
        for (RecordType recordType : list) {
            //recordType.setSyncStatus(false);
            recordType.setIndex(i);
            recordTypeDao.update(recordType);
            i++;
        }
        list.add(new RecordType());
    }

    public int getMaxIndexByRecordType(Context context, int type) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        RecordType recordType = getSingleData(recordTypeDao.queryBuilder().where(RecordTypeDao.Properties.RecordType.eq(type))
                .where(RecordTypeDao.Properties.IsDel.eq(false))
                .orderDesc(RecordTypeDao.Properties.Index).limit(1).list());

        return recordType == null ? 0 : recordType.getIndex();
    }

    public void updateRecordType(Context context, RecordType recordType) {
        RecordTypeDao recordTypeDao = getDaoSession(context).getRecordTypeDao();
        recordTypeDao.update(recordType);
    }
}
