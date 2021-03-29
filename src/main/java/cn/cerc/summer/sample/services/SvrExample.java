package cn.cerc.summer.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.core.Record;
import cn.cerc.core.TDateTime;
import cn.cerc.db.mysql.SqlQuery;
import cn.cerc.mis.core.CustomService;
import cn.cerc.mis.core.DataValidateException;
import cn.cerc.summer.sample.core.AppDB;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SvrExample extends CustomService {

    public boolean search() {
        // 获取外部传进来的数据
        Record headIn = getDataIn().getHead();
        log.debug("headIn {}", headIn);

        // SqlQuery 用于操作数据库，可对数据进行增删改查，在使用增删改功能前，必须查询表。
        SqlQuery query = new SqlQuery(this);
        // add方法追加sql语句
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where 1=1 ");
        // 判断传进来的值，存在code_并且不为空
        if (headIn.hasValue("code_")) {
            query.add("and code_='%s'", headIn.getString("code_"));
        }

        if (headIn.hasValue("searchText_")) {
            String searchText = headIn.getString("searchText_");
            // 此处使用占位符进行%占位
            query.add("and (name_ like '%%%s%%' or age_ like '%%%s%%')", searchText, searchText);
        }
        log.debug("sql {}", query.getSqlText().getText());

        // 将准备好的sql语句执行，并将结果存放于cdsTmp对象。
        query.open();
        // 将sql查询出来的结果存放到服务出口返回给调用者
        getDataOut().appendDataSet(query);
        return true;
    }

    public boolean append() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("学号不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        DataValidateException.stopRun("姓名不允许为空", !headIn.hasValue("name_"));
        DataValidateException.stopRun("性别不允许为空", !headIn.hasValue("sex_"));
        DataValidateException.stopRun("年龄不允许为空", !headIn.hasValue("age_"));

        SqlQuery cdquery = new SqlQuery(this);
        cdquery.add("select * from %s", AppDB.TABLE_EXAMPLE);
        cdquery.add("where code_='%s'", code);
        cdquery.open();
        DataValidateException.stopRun("该学号已经存在，不允许重复登记", !cdquery.eof());

        cdquery.append();
        cdquery.setField("code_", code);
        cdquery.setField("name_", headIn.getString("name_"));
        cdquery.setField("sex_", headIn.getString("sex_"));
        cdquery.setField("age_", headIn.getString("age_"));
        cdquery.setField("createTime_", TDateTime.now());
        cdquery.setField("updateTime_", TDateTime.now());
        cdquery.post();

        return true;
    }

    public boolean download() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        SqlQuery query = new SqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        getDataOut().getHead().copyValues(query.getCurrent());
        return true;
    }

    public boolean modify() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_ 不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        DataValidateException.stopRun("sex_ 不允许为空", !headIn.hasValue("sex_"));
        String sex = headIn.getString("sex_");

        int age = headIn.getInt("age_");
        DataValidateException.stopRun("年龄不允许小于0", age <= 0);

        SqlQuery query = new SqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        query.edit();
        query.setField("age_", age);
        query.setField("sex_", sex);
        query.setField("updateTime_", TDateTime.now());
        query.post();
        return true;
    }

    public boolean delete() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_ 不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        SqlQuery query = new SqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        query.delete();
        return true;
    }
}
