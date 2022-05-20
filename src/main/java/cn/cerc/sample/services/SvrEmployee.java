package cn.cerc.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.FastDate;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.core.AppDB;
import cn.cerc.sample.entity.EmployeeEntity;

@Permission(Permission.GUEST)
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SvrEmployee implements IService {

    @Description("根据条件查询人员信息")
    public DataSet search(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.s_employee);
        SqlWhere where = query.addWhere();
        where.eq("corp_no_", handle.getCorpNo());
        if (headIn.has("code_"))
            where.eq("code_", headIn.getString("code_"));
        if (headIn.has("searchText_"))
            where.like("name_", headIn.getString("searchText_"));
        where.build();
        query.openReadonly();
        return query.setState(ServiceState.OK).disableStorage();
    }

    @Description("新增人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    @DataValidate(value = "name_", name = "员工姓名")
    @DataValidate(value = "gender_", name = "员工性别")
    @DataValidate(value = "entry_date_", name = "入职日期")
    public DataSet append(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        EntityOne<EmployeeEntity> entity = EntityOne.open(handle, EmployeeEntity.class, code)
                .isPresentThrow(() -> new RuntimeException("该工号已经存在，不允许重复登记"));
        entity.orElseInsert(item -> {
            item.setCode_(code);
            item.setName_(headIn.getString("name_"));
            item.setGender_(headIn.getInt("gender_"));
            item.setEntry_date_(new FastDate());
        });
        DataSet dataSet = new DataSet();
        dataSet.append().copyRecord(entity.current());
        return dataSet.setState(ServiceState.OK).disableStorage();
    }

    @Description("获取员工信息")
    @DataValidate(value = "code_", name = "员工工号")
    public DataSet download(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        DataRow dataRow = EntityOne.open(handle, EmployeeEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 员工编号不存在", code))).current();
        DataSet dataSet = new DataSet();
        dataSet.append().copyRecord(dataRow);
        return dataSet.setState(ServiceState.OK).disableStorage();
    }

    @Description("修改人员信息")
    @DataValidate(value = "code_", name = "员工工号")
    @DataValidate(value = "gender_", name = "员工性别")
    @DataValidate(value = "entry_date_", name = "入职日期")
    public DataSet modify(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        EntityOne.open(handle, EmployeeEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 员工编号不存在", code))).update(item -> {
                    item.setName_(headIn.getString("name_"));
                    item.setGender_(headIn.getInt("gender_"));
                    item.setEntry_date_(headIn.getFastDate("entry_date_"));
                });
        return new DataSet().setState(ServiceState.OK).disableStorage();
    }

    @DataValidate(value = "code_", name = "员工工号")
    @Description("删除人员信息")
    public boolean delete(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        EntityOne.open(handle, EmployeeEntity.class, code).isEmptyThrow(() -> new RuntimeException("记录不存在")).delete();
        return true;
    }

    public static void main(String[] args) {
        ServiceSign.buildSourceCode(SvrEmployee.class);
    }
}
