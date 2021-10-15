package cn.cerc.summer.sample.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.core.DataRow;
import cn.cerc.core.Datetime;
import cn.cerc.core.FieldDefs;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.db.other.RecordFilter;
import cn.cerc.mis.core.CustomService;
import cn.cerc.mis.core.DataValidateException;
import cn.cerc.mis.core.Permission;
import cn.cerc.summer.sample.core.AppDB;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrExample extends CustomService {
    private static final Logger log = LoggerFactory.getLogger(SvrExample.class);

    public boolean search() {
        // 获取外部传进来的数据
        DataRow headIn = getDataIn().getHead();
        log.debug("headIn {}", headIn);

        // MysqlQuery 用于操作数据库，可对数据进行增删改查，在使用增删改功能前，必须查询表。
        MysqlQuery query = new MysqlQuery(this);
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
        // 返回meta讯息
        FieldDefs columns = getDataOut().getFieldDefs();
        columns.get("code_").setName("工号");
        columns.get("name_").setName("姓名");
        columns.get("sex_").setName("性别");
        columns.get("age_").setName("年龄");
        columns.get("createTime_").setName("创建时间");
        getDataOut().setMetaInfo(true);
        // 支持对服务结果进行过滤
        this.setDataOut(RecordFilter.execute(this.getDataOut(), getDataIn().getHead().getString("_service_filter_")));
        return true;
    }

    public boolean append() throws DataValidateException {
        DataRow headIn = getDataIn().getHead();
        DataValidateException.stopRun("学号不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        DataValidateException.stopRun("姓名不允许为空", !headIn.hasValue("name_"));
        DataValidateException.stopRun("性别不允许为空", !headIn.hasValue("sex_"));
        DataValidateException.stopRun("年龄不允许为空", !headIn.hasValue("age_"));

        MysqlQuery query = new MysqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("该学号已经存在，不允许重复登记", !query.eof());

        query.append();
        query.setField("code_", code);
        query.setField("name_", headIn.getString("name_"));
        query.setField("sex_", headIn.getString("sex_"));
        query.setField("age_", headIn.getString("age_"));
        query.setField("createTime_", new Datetime());
        query.setField("updateTime_", new Datetime());
        query.post();

        return true;
    }

    public boolean download() throws DataValidateException {
        DataRow headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        MysqlQuery query = new MysqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        getDataOut().getHead().copyValues(query.getCurrent());
        return true;
    }

    public boolean modify() throws DataValidateException {
        DataRow headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_ 不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        DataValidateException.stopRun("sex_ 不允许为空", !headIn.hasValue("sex_"));
        String sex = headIn.getString("sex_");

        int age = headIn.getInt("age_");
        DataValidateException.stopRun("年龄不允许小于0", age <= 0);

        MysqlQuery query = new MysqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        query.edit();
        query.setField("age_", age);
        query.setField("sex_", sex);
        query.setField("updateTime_", new Datetime());
        query.post();
        return true;
    }

    public boolean delete() throws DataValidateException {
        DataRow headIn = getDataIn().getHead();
        DataValidateException.stopRun("code_ 不允许为空", !headIn.hasValue("code_"));
        String code = headIn.getString("code_");

        MysqlQuery query = new MysqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        query.delete();
        return true;
    }
}
