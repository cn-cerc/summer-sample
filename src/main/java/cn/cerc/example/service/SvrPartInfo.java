package cn.cerc.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cerc.example.common.AppDB;
import cn.cerc.jbean.core.CustomService;
import cn.cerc.jbean.core.DataValidateException;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.BuildQuery;
import cn.cerc.jdb.mysql.SqlQuery;

public class SvrPartInfo extends CustomService {

    private static final Logger log = LoggerFactory.getLogger(SvrPartInfo.class);

    public boolean search() {
        Record headIn = getDataIn().getHead();
        log.info("headIn {}", headIn);

        BuildQuery f = new BuildQuery(this);
        f.add("select * from %s", AppDB.Table_PartInfo);

        if (headIn.hasValue("code_")) {
            f.byField("code_", headIn.getString("code_"));
        }

        if (headIn.hasValue("searchText_")) {
            f.byLink(new String[] { "corpNo_", "unit_", "code_", "desc_", "spec_" }, headIn.getString("searchText_"));
        }
        log.info("sql {}", f.getCommandText());

        getDataOut().appendDataSet(f.open());
        return true;
    }

    public boolean append() {
        Record headIn = getDataIn().getHead();
        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_PartInfo);
        cdsTmp.setMaximum(0);
        cdsTmp.open();

        cdsTmp.append();
        cdsTmp.setField("desc_", headIn.getString("desc_"));
        cdsTmp.setField("spec_", headIn.getString("spec_"));
        cdsTmp.setField("remark_", headIn.getString("remark_"));
        cdsTmp.setField("corpNo_", headIn.getString("corpNo_"));
        cdsTmp.setField("code_", headIn.getString("code_"));
        cdsTmp.setField("unit_", headIn.getString("unit_"));
        /*
         * cdsTmp.setField("createTime_", TDateTime.Now());
         * cdsTmp.setField("updateTime_", TDateTime.Now());
         */
        cdsTmp.post();

        return true;
    }

    public boolean download() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_PartInfo);
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

        DataValidateException.stopRun("名称不能为空", !headIn.hasValue("desc_"));
        String desc = headIn.getString("desc_");

        DataValidateException.stopRun("规格不能为空", !headIn.hasValue("spec_"));
        String spec = headIn.getString("spec_");

        DataValidateException.stopRun("单位不能为空", !headIn.hasValue("unit_"));
        String unit = headIn.getString("unit_");

        String remark = headIn.getString("remark_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_PartInfo);
        cdsTmp.add("where UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.edit();
        cdsTmp.setField("desc_", desc);
        cdsTmp.setField("spec_", spec);
        cdsTmp.setField("unit_", unit);
        cdsTmp.setField("remark_", remark);
        // cdsTmp.setField("updateTime_", TDateTime.Now());
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
        return true;
    }
}
