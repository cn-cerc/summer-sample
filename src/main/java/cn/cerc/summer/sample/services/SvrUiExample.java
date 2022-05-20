package cn.cerc.summer.sample.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.FieldDefs;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.DataValidateException;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.summer.sample.core.AppDB;
import cn.cerc.summer.sample.entity.Example;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrUiExample implements IService {
    private static final Logger log = LoggerFactory.getLogger(SvrUiExample.class);

    @Description("根据条件查询人员信息")
    public DataSet search(IHandle handle, DataRow headIn) {
        // 获取外部传进来的数据
        log.info("headIn {}", headIn);

        // MysqlQuery 用于操作数据库，可对数据进行增删改查，在使用增删改功能前，必须查询表。
        MysqlQuery query = new MysqlQuery(handle);
        // add方法追加sql语句
        query.add("select * from %s", AppDB.s_example);
        SqlWhere where = query.addWhere();
        // 公司别
        where.eq("corpNo_", handle.getCorpNo());
        // 用户账号
        where.eq("userCode_", handle.getUserCode());
        // 判断传进来的值，存在code_并且不为空
        if (headIn.has("code_")) {
            where.eq("code_", headIn.getString("code_"));
        }

        if (headIn.has("searchText_")) {
            where.like("name_", headIn.getString("searchText_"));
        }
        where.build();
        log.info("sql {}", query.sql().text());

        // 将准备好的sql语句执行，并将结果存放于cdsTmp对象。
        query.open();
        // 返回meta讯息
        FieldDefs columns = query.fields();
        columns.get("code_").setName("学号");
        columns.get("name_").setName("姓名");
        columns.get("sex_").setName("性别");
        columns.get("age_").setName("年龄");
        columns.get("createTime_").setName("创建时间");
        query.setMeta(true);
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "code_", name = "学号")
    @DataValidate(value = "name_", name = "姓名")
    @DataValidate(value = "sex_", name = "性别")
    @DataValidate(value = "age_", name = "年龄")
    @Description("新增人员信息")
    public boolean append(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");
        String corpNo = handle.getCorpNo();
        String userCode = handle.getUserCode();
        EntityOne.open(handle, Example.class, handle.getUserCode(), corpNo, userCode, code)
                .isPresentThrow(() -> new RuntimeException("该学号已经存在，不允许重复登记")).orElseInsert(item -> {
                    item.setCode_(code);
                    item.setName_(headIn.getString("name_"));
                    item.setSex_(headIn.getInt("sex_"));
                    item.setAge_(headIn.getInt("age_"));
                });
        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    @Description("获取人员信息")
    public DataSet download(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");

        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.s_example);
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("corpNo_", handle.getCorpNo());
        addWhere.eq("userCode_", handle.getUserCode());
        addWhere.eq("code_", code).build();
        query.open();
        DataValidateException.stopRun("记录不存在", query.eof());
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "code_", name = "学号")
    @DataValidate(value = "sex_", name = "性别")
    @Description("修改人员信息")
    public boolean modify(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");

        int age = headIn.getInt("age_");
        DataValidateException.stopRun("年龄不允许小于0", age <= 0);

        EntityOne.open(handle, Example.class, handle.getUserCode(), code)
                .isEmptyThrow(() -> new RuntimeException("记录不存在")).update(item -> {
                    item.setSex_(headIn.getInt("sex_"));
                    item.setAge_(headIn.getInt("age_"));
                });
        return true;
    }

    @DataValidate(value = "code_", name = "学号")
    @Description("删除人员信息")
    public boolean delete(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");

        EntityOne.open(handle, Example.class, handle.getUserCode(), code)
                .isEmptyThrow(() -> new RuntimeException("记录不存在")).delete();
        return true;
    }
}
