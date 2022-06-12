package cn.cerc.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Datetime;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.entity.EmployeeInfoEntity;
import cn.cerc.sample.entity.EmployeeTotalEntity;

@Permission(Permission.GUEST)
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Description("第二代服务编写方式(正式项目中逐渐淘汰)")
public class SvrEmployeeV2 implements IService {

    @Description("根据条件查询人员信息")
    public DataSet search(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", EmployeeInfoEntity.TABLE);
        SqlWhere where = query.addWhere();
        where.eq("corp_no_", handle.getCorpNo());
        if (headIn.has("code_"))
            where.eq("code_", headIn.getString("code_"));
        if (headIn.has("searchText_"))
            where.like("name_", headIn.getString("searchText_"));
        where.build();
        query.add("order by code_ desc");
        query.openReadonly();
        return query.setState(ServiceState.OK);
    }

    @Description("新增人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    @DataValidate(value = "name_", name = "员工姓名")
    @DataValidate(value = "gender_", name = "员工性别")
    @DataValidate(value = "entry_date_", name = "入职日期")
    public DataSet append(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", EmployeeInfoEntity.TABLE);
        query.add("where corp_no_='%s'", handle.getCorpNo());
        query.add("and code_='%s'", code);
        query.open();
        if (!query.eof())
            throw new RuntimeException("该工号已经存在，不允许重复登记");

        try (Transaction tx = new Transaction(handle)) {// 启用事务管控
            query.append();
            query.setValue("corp_no_", handle.getCorpNo());
            query.setValue("code_", code);
            query.setValue("name_", headIn.getString("name_"));
            query.setValue("gender_", headIn.getInt("gender_"));
            query.setValue("entry_date_", headIn.getFastDate("entry_date_"));
            query.setValue("enable_", true);
            query.setValue("create_user_", handle.getUserCode());
            query.setValue("create_time_", new Datetime());
            query.setValue("update_user_", handle.getUserCode());
            query.setValue("update_time_", new Datetime());
            query.setValue("version_", 1);
            query.post();

            updateTotal(handle, 1);
            tx.commit();
        }
        return query.setState(ServiceState.OK);
    }

    @Description("获取员工信息")
    @DataValidate(value = "code_", name = "员工工号")
    public DataSet download(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", EmployeeInfoEntity.TABLE);
        query.add("where corp_no_='%s'", handle.getCorpNo());
        query.add("and code_='%s'", code);
        query.open();
        if (query.eof())
            throw new RuntimeException(String.format("%s 员工编号不存在", code));

        return query.setState(ServiceState.OK);
    }

    @Description("修改人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    @DataValidate(value = "gender_", name = "员工性别")
    @DataValidate(value = "entry_date_", name = "入职日期")
    public DataSet modify(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", EmployeeInfoEntity.TABLE);
        query.add("where corp_no_='%s'", handle.getCorpNo());
        query.add("and code_='%s'", code);
        query.open();
        if (query.eof())
            throw new RuntimeException("该工号不存在，修改失败");
        query.edit();
        query.setValue("name_", headIn.getString("name_"));
        query.setValue("gender_", headIn.getInt("gender_"));
        query.setValue("entry_date_", headIn.getFastDate("entry_date_"));
        query.setValue("enable_", headIn.getBoolean("enable_"));
        query.setValue("update_user_", handle.getUserCode());
        query.setValue("update_time_", new Datetime());
        query.post();

        return query.setState(ServiceState.OK);
    }

    @Description("删除人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    public boolean delete(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", EmployeeInfoEntity.TABLE);
        query.add("where corp_no_='%s'", handle.getCorpNo());
        query.add("and code_='%s'", code);
        query.open();
        if (query.eof())
            throw new RuntimeException("该工号不存在，删除失败");
        // 更新员数量合计栏位
        try (Transaction tx = new Transaction(handle)) {// 启用事务管控
            query.delete();
            updateTotal(handle, -1);
            tx.commit();
        }
        return true;
    }

    private void updateTotal(IHandle handle, int num) {
        MysqlQuery total = new MysqlQuery(handle);
        total.add("select * from %s", EmployeeTotalEntity.TABLE);
        total.add("where corp_no_='%s'", handle.getCorpNo());
        total.open();

        if (total.eof()) {
            total.append();
            total.setValue("corp_no_", handle.getCorpNo());
            total.setValue("total_", 0);
            total.setValue("create_user_", handle.getUserCode());
            total.setValue("create_time_", new Datetime());
            total.setValue("version_", 1);
        } else {
            total.setValue("total_", total.getInt("total_") + num);
            total.setValue("version_", total.getInt("version_") + 1);
        }
        total.setValue("update_user_", handle.getUserCode());
        total.setValue("update_time_", new Datetime());
        total.post();
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrEmployeeV2.class);
    }

}
