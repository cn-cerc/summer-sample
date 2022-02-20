package cn.cerc.summer.sample.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.FieldDefs;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.core.CustomService;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.DataValidateException;
import cn.cerc.mis.security.Permission;
import cn.cerc.summer.sample.core.AppDB;
import cn.cerc.summer.sample.entity.Example;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrExample extends CustomService {
    private static final Logger log = LoggerFactory.getLogger(SvrExample.class);

    @Description("根据条件查询人员信息")
    public boolean search() {
        // 获取外部传进来的数据
        DataRow headIn = dataIn().head();
        log.debug("headIn {}", headIn);

        // MysqlQuery 用于操作数据库，可对数据进行增删改查，在使用增删改功能前，必须查询表。
        MysqlQuery query = new MysqlQuery(this);
        // add方法追加sql语句
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        SqlWhere where = query.addWhere();
        // 判断传进来的值，存在code_并且不为空
        if (headIn.has("code_")) {
            where.eq("code_", headIn.getString("code_"));
        }

        if (headIn.has("searchText_")) {
            where.like("name_", headIn.getString("searchText_"));
        }
        where.build();
        log.debug("sql {}", query.sql().text());

        // 将准备好的sql语句执行，并将结果存放于cdsTmp对象。
        query.open();
        // 将sql查询出来的结果存放到服务出口返回给调用者
        dataOut().appendDataSet(query);
        // 返回meta讯息
        FieldDefs columns = dataOut().fields();
        columns.get("code_").setName("工号");
        columns.get("name_").setName("姓名");
        columns.get("sex_").setName("性别");
        columns.get("age_").setName("年龄");
        columns.get("createTime_").setName("创建时间");
        dataOut().setMeta(true);
        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    @DataValidate(value = "name_", name = "姓名")
    @DataValidate(value = "sex_", name = "性别")
    @DataValidate(value = "age_", name = "年龄")
    @Description("新增人员信息")
    public boolean append() throws DataValidateException {
        DataRow headIn = dataIn().head();
        String code = headIn.getString("code_");

        EntityOne.open(this, Example.class, code).isPresentThrow(() -> new RuntimeException("该学号已经存在，不允许重复登记"))
                .orElseInsert(item -> {
                    item.setCode_(code);
                    item.setName_(headIn.getString("name_"));
                    item.setSex_(headIn.getInt("sex_"));
                    item.setAge_(headIn.getInt("age_"));
                });

        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    @Description("获取人员信息")
    public boolean download() throws DataValidateException {
        DataRow headIn = dataIn().head();
        String code = headIn.getString("code_");

        MysqlQuery query = new MysqlQuery(this);
        query.add("select * from %s", AppDB.TABLE_EXAMPLE);
        query.add("where code_='%s'", code);
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());

        dataOut().head().copyValues(query.current());
        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    @DataValidate(value = "sex_", name = "性别")
    @Description("修改人员信息")
    public boolean modify() throws DataValidateException {
        DataRow headIn = dataIn().head();
        String code = headIn.getString("code_");

        int age = headIn.getInt("age_");
        DataValidateException.stopRun("年龄不允许小于0", age <= 0);

        EntityOne.open(this, Example.class, code).isEmptyThrow(() -> new RuntimeException("记录不存在")).update(item -> {
            item.setSex_(headIn.getInt("sex_"));
            item.setAge_(headIn.getInt("age_"));
        });
        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    @Description("删除人员信息")
    public boolean delete() throws DataValidateException {
        DataRow headIn = dataIn().head();
        String code = headIn.getString("code_");

        EntityOne.open(this, Example.class, code).isEmptyThrow(() -> new RuntimeException("记录不存在")).delete();
        return true;
    }
}
