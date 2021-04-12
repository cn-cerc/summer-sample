package cn.cerc.summer.sample;

import cn.cerc.core.Record;
import cn.cerc.mis.core.Handle;
import cn.cerc.mis.sync.ISyncRecord;

public class Sync_s_example extends Handle implements ISyncRecord {

    @Override
    public boolean onUpdate(Record current, Record newRecord) {
        return false;
    }

    @Override
    public boolean onAppend(Record newRecord) {
        return false;
    }

    @Override
    public boolean onDelete(Record current) {
        return false;
    }

}
