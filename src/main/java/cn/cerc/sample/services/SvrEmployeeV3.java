package cn.cerc.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.ado.EntityOne;
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
@Description("第三代服务编写方式(正式项目中使用)")
public class SvrEmployeeV3 implements IService {

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
        EntityOne<EmployeeInfoEntity> entity = EntityOne.open(handle, EmployeeInfoEntity.class, code)
                .isPresentThrow(() -> new RuntimeException("该工号已经存在，不允许重复登记"));
        try (Transaction tx = new Transaction(handle)) {// 启用事务管控
            entity.orElseInsert(item -> {
                item.setCode_(code);
                item.setName_(headIn.getString("name_"));
                item.setGender_(headIn.getInt("gender_"));
                item.setEntry_date_(headIn.getFastDate("entry_date_"));
            });
            // 更新员数量合计栏位
            EntityOne.open(handle, EmployeeTotalEntity.class).update(item -> item.setTotal_(item.getTotal_() + 1))
                    .orElseInsert(item -> item.setTotal_(1));
            tx.commit();
        }
        return entity.dataSet().setState(ServiceState.OK);
    }

    @Description("获取员工信息")
    @DataValidate(value = "code_", name = "员工工号")
    public DataSet download(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        return EntityOne.open(handle, EmployeeInfoEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 员工编号不存在", code))).dataSet()
                .setState(ServiceState.OK);
    }

    @Description("修改人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    @DataValidate(value = "gender_", name = "员工性别")
    @DataValidate(value = "entry_date_", name = "入职日期")
    public DataSet modify(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        return EntityOne.open(handle, EmployeeInfoEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 员工编号不存在", code))).update(item -> {
                    item.setName_(headIn.getString("name_"));
                    item.setGender_(headIn.getInt("gender_"));
                    item.setEntry_date_(headIn.getFastDate("entry_date_"));
                    item.setEnable_(headIn.getBoolean("enable_"));
                }).dataSet().setState(ServiceState.OK);
    }

    @Description("删除人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    public boolean delete(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        try (Transaction tx = new Transaction(handle)) {
            EntityOne.open(handle, EmployeeInfoEntity.class, code)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 员工编号不存在", code))).delete();

            // 更新员数量合计栏位
            EntityOne.open(handle, EmployeeTotalEntity.class).update(item -> item.setTotal_(item.getTotal_() - 1))
                    .orElseInsert(item -> item.setTotal_(0));
            tx.commit();
        }
        return true;
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrEmployeeV3.class);
    }

}
