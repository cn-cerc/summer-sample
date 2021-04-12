package cn.cerc.summer.sample;

import cn.cerc.db.mysql.SqlQuery;
import cn.cerc.mis.custom.SessionDefault;
import cn.cerc.mis.sync.SyncProcess;
import cn.cerc.mis.sync.SyncTable;
import cn.cerc.summer.sample.core.AppDB;

public class SyncSample extends SyncProcess {

    public void send() {
        SessionDefault session = new SessionDefault();
        SqlQuery ds = new SqlQuery(session);
        ds.add("select * from %s", AppDB.TABLE_EXAMPLE);
        ds.open();
        while (ds.fetch()) {
            SyncTable.update(AppDB.TABLE_EXAMPLE, ds.getCurrent());
        }
    }

    public static void main(String[] args) {
        new SyncSample().send();

        SyncSample test = new SyncSample();
        test.execute();
    }

}
