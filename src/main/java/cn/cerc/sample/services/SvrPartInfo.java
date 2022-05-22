package cn.cerc.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.core.Utils;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.core.AppDB;
import cn.cerc.sample.entity.PartinfoEntity;

@Permission(Permission.GUEST)
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SvrPartInfo implements IService {

    @Description("商品查询服务")
    public DataSet search(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.s_partinfo);
        SqlWhere where = query.addWhere();
        where.eq("corp_no_", handle.getCorpNo());
        if (headIn.has("code_"))
            where.eq("code_", headIn.getString("code_"));
        if (headIn.has("searchText_"))
            where.like("desc_", headIn.getString("searchText_"));
        where.build();
        query.add("order by create_time_ desc");
        query.openReadonly();
        return query.setState(ServiceState.OK);
    }

    @Description("新增商品信息")
    @DataValidate(value = "desc_", name = "品名")
    @DataValidate(value = "spec_", name = "规格")
    @DataValidate(value = "unit_", name = "单位")
    public DataSet append(IHandle handle, DataRow headIn) {
        String code = handle.getCorpNo() + Utils.getNumRandom(6);
        EntityOne<PartinfoEntity> entity = EntityOne.open(handle, PartinfoEntity.class, code)
                .isPresentThrow(() -> new RuntimeException("该料号已经存在，不允许重复登记"));

        try (Transaction tx = new Transaction(handle)) {// 启用事务管控
            entity.orElseInsert(item -> {
                item.setCode_(code);
                item.setDesc_(headIn.getString("desc_"));
                item.setSpec_(headIn.getString("spec_"));
                item.setUnit_(headIn.getString("unit_"));
                item.setRemark_(headIn.getString("remark_"));
            });
            tx.commit();
        }
        return entity.dataSet().setState(ServiceState.OK);
    }

    @Description("获取商品信息")
    @DataValidate(value = "code_", name = "料号")
    public DataSet download(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        return EntityOne.open(handle, PartinfoEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 料号不存在", code))).dataSet()
                .setState(ServiceState.OK);
    }

    @Description("修改商品信息")
    @DataValidate(value = "code_", name = "料号")
    @DataValidate(value = "desc_", name = "品名")
    @DataValidate(value = "spec_", name = "规格")
    @DataValidate(value = "unit_", name = "单位")
    public boolean modify(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        EntityOne.open(handle, PartinfoEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 料号不存在", code))).update(item -> {
                    item.setDesc_(headIn.getString("desc_"));
                    item.setSpec_(headIn.getString("spec_"));
                    item.setUnit_(headIn.getString("unit_"));
                    item.setRemark_(headIn.getString("remark_"));
                });
        return true;
    }

    @Description("删除商品信息")
    @DataValidate(value = "code_", name = "料号")
    public boolean delete(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.s_tranb);
        query.addWhere().eq("corp_no_", handle.getCorpNo()).eq("code_", code).build();
        query.setMaximum(1);
        query.openReadonly();
        if (!query.eof())
            throw new RuntimeException(String.format("%s 料号已经在单据中使用，当前环境不允许删除", code));

        EntityOne.open(handle, PartinfoEntity.class, code)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 料号不存在", code))).delete();
        return true;
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrPartInfo.class);
    }

}
