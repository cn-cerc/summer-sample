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
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.core.AppDB;

@Permission(Permission.GUEST)
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SvrOrderReport implements IService {

    @Description("订单统计服务")
    public DataSet search(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.s_tranh);
        SqlWhere where = query.addWhere();
        where.eq("corp_no_", handle.getCorpNo());
        if (headIn.has("order_sn_"))
            where.eq("order_sn_", headIn.getString("order_sn_"));
        if (headIn.has("dateFrom_") && headIn.has("dateTo_"))
            where.between("order_date_", headIn.getFastDate("dateFrom_"), headIn.getFastDate("dateTo_"));
        where.build();
        query.add("order by order_sn_ desc");
        query.openReadonly();
        return query.setState(ServiceState.OK).disableStorage();
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrOrderReport.class);
    }

}
