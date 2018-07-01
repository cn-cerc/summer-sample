package cn.cerc.example.service;

import cn.cerc.example.common.AppDB;
import cn.cerc.jbean.core.CustomService;
import cn.cerc.jbean.core.DataValidateException;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.BuildQuery;
import cn.cerc.jdb.mysql.SqlQuery;

public class SvrExample extends CustomService {

    public boolean search() {
        Record headIn = getDataIn().getHead();
        BuildQuery f = new BuildQuery(this);
        f.add("select * from %s", AppDB.Table_Example);

        if (headIn.hasValue("searchText_")) {
            f.byLink(new String[] { "name_", "age_" }, headIn.getString("searchText_"));
        }

        getDataOut().appendDataSet(f.open());
        return true;
    }

    public boolean append() {
        Record headIn = getDataIn().getHead();
        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.setMaximum(0);
        cdsTmp.open();

        cdsTmp.append();
        cdsTmp.setField("name_", headIn.getString("name_"));
        cdsTmp.setField("sex_", headIn.getString("sex_"));
        cdsTmp.setField("age_", headIn.getString("age_"));
        cdsTmp.post();

        return true;
    }

    public boolean download() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        getDataOut().getHead().copyValues(cdsTmp.getCurrent());
        return true;
    }

    public boolean modify() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");

        int age = headIn.getInt("age_");
        DataValidateException.stopRun("年龄不允许小于0", age <= 0);

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.edit();
        cdsTmp.setField("age_", age);
        cdsTmp.post();
        return true;
    }

    public boolean delete() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.delete();
        cdsTmp.post();
        return true;
    }
}
