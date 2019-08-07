package cn.cerc.example.service;

import cn.cerc.example.common.AppDB;
import cn.cerc.jbean.core.CustomService;
import cn.cerc.jbean.core.DataValidateException;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.mysql.SqlQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SvrExample extends CustomService {

    public boolean search() {
        Record headIn = getDataIn().getHead();
        log.info("headIn {}", headIn);

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where 1=1");

        if (headIn.hasValue("code_")) {
            cdsTmp.add("and code_='%s'", headIn.getString("code_"));
        }

        if (headIn.hasValue("searchText_")) {
            String searchText = headIn.getString("searchText_");
            // 此处使用占位符进行%占位
            cdsTmp.add("and (name_ like '%%%s%%' or age_ like '%%%s%%')", searchText, searchText);
        }
        log.info("sql {}", cdsTmp.getSqlText().getText());

        cdsTmp.open();
        getDataOut().appendDataSet(cdsTmp);
        return true;
    }

    public boolean append() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("学号不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        DataValidateException.stopRun("姓名不允许为空", !headIn.hasValue("name_"));
        DataValidateException.stopRun("性别不允许为空", !headIn.hasValue("sex_"));
        DataValidateException.stopRun("年龄不允许为空", !headIn.hasValue("age_"));

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        DataValidateException.stopRun("该学号已经存在，不允许重复登记", !cdsTmp.eof());

        cdsTmp.append();
        cdsTmp.setField("code_", code);
        cdsTmp.setField("name_", headIn.getString("name_"));
        cdsTmp.setField("sex_", headIn.getString("sex_"));
        cdsTmp.setField("age_", headIn.getString("age_"));
        cdsTmp.setField("createTime_", TDateTime.Now());
        cdsTmp.setField("updateTime_", TDateTime.Now());
        cdsTmp.post();

        return true;
    }

    public boolean download() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        getDataOut().getHead().copyValues(cdsTmp.getCurrent());
        return true;
    }

    public boolean modify() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_ 不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        int age = headIn.getInt("age_");
        DataValidateException.stopRun("年龄不允许小于0", age <= 0);

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.edit();
        cdsTmp.setField("age_", age);
        cdsTmp.setField("updateTime_", TDateTime.Now());
        cdsTmp.post();
        return true;
    }

    public boolean delete() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_ 不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.delete();
        return true;
    }
}
