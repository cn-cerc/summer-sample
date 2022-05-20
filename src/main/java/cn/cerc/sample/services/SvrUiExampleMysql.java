package cn.cerc.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Datetime;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.core.AppDB;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrUiExampleMysql implements IService {

    public static void main(String[] args) {
        ServiceSign.buildSourceCode(SvrUiExampleMysql.class);
    }

    public DataSet search(IHandle handle, DataRow headIn) {
        // MysqlQuery 用于操作数据库，可对数据进行增删改查，在使用增删改功能前，必须查询表。
        MysqlQuery query = new MysqlQuery(handle);
        // add方法追加sql语句
        query.add("select * from %s", AppDB.s_example);
        SqlWhere where = query.addWhere();
        where.eq("corpNo_", handle.getCorpNo());
        where.eq("userCode_", handle.getUserCode());
        // 判断传进来的值，存在code_并且不为空
        if (headIn.has("code_")) {
            where.eq("code_", headIn.getString("code_"));
        }

        if (headIn.has("searchText_")) {
            where.like("name_", headIn.getString("searchText_"));
        }
        where.build();

        // 将准备好的sql语句执行，并将结果存放于cdsTmp对象。
        query.open();
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "code_", name = "学号")
    @DataValidate(value = "name_", name = "姓名")
    @DataValidate(value = "sex_", name = "性别")
    public boolean append(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");

        // 调用SvrCorpInfo.modify服务
        MysqlQuery ds = new MysqlQuery(handle);
        ds.add("select * from %s", AppDB.s_example);
        SqlWhere where = ds.addWhere();
        where.eq("corpNo_", handle.getCorpNo());
        where.eq("userCode_", handle.getUserCode());
        where.eq("code_", code).build();
        ds.open();

        if (!ds.eof())
            throw new RuntimeException("该学号已经存在，不允许重复登记");

        ds.append();
        ds.setValue("code_", code);
        ds.setValue("corpNo_", handle.getCorpNo());
        ds.setValue("userCode_", handle.getUserCode());
        ds.setValue("name_", headIn.getString("name_"));
        ds.setValue("sex_", headIn.getInt("sex_"));
        ds.setValue("age_", headIn.getInt("age_"));
        ds.setValue("createTime_", new Datetime());
        ds.setValue("updateTime_", new Datetime());
        ds.post();
        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    public DataSet download(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.s_example);
        SqlWhere where = query.addWhere();
        where.eq("corpNo_", handle.getCorpNo());
        where.eq("userCode_", handle.getUserCode());
        where.eq("code_", headIn.getString("code_")).build();
        query.open();
        query.setStorage(false);
        if (query.eof())
            throw new RuntimeException("记录不存在");
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "code_", name = "学号")
    @DataValidate(value = "name_", name = "姓名")
    @DataValidate(value = "sex_", name = "性别")
    public boolean modify(IHandle handle, DataRow headIn) {
        int age = headIn.getInt("age_");
        if (age <= 0)
            throw new RuntimeException("年龄不允许小于0");

        MysqlQuery ds = new MysqlQuery(handle);
        ds.add("select * from %s", AppDB.s_example);
        SqlWhere dsWhere = ds.addWhere();
        dsWhere.eq("corpNo_", handle.getCorpNo());
        dsWhere.eq("userCode_", handle.getUserCode());
        dsWhere.eq("code_", headIn.getString("code_")).build();
        ds.open();

        if (ds.eof())
            throw new RuntimeException("记录不存在");

        ds.edit();
        ds.setValue("sex_", headIn.getInt("sex_"));
        ds.setValue("age_", headIn.getInt("age_"));
        ds.setValue("updateTime_", new Datetime());
        ds.post();
        return true;
    }

    @DataValidate(value = "code_", name = "学号", message="%s 不允许为空")
    public boolean delete(IHandle handle, DataRow headIn) {
        MysqlQuery ds = new MysqlQuery(handle);
        ds.add("select * from %s", AppDB.s_example);
        SqlWhere where = ds.addWhere();
        where.eq("corpNo_", handle.getCorpNo());
        where.eq("userCode_", handle.getUserCode());
        where.eq("code_", headIn.getString("code_")).build();
        ds.open();

        if (ds.eof())
            throw new RuntimeException("记录不存在");
        ds.delete();
        return true;
    }
}
