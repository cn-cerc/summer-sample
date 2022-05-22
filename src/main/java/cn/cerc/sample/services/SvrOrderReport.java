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
import cn.cerc.mis.core.DataValidate;
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
        query.add("select h.order_date_,");
        query.add("sum(case when h.tb_='AB' then b.num_ else 0 end) as in_,");
        query.add("sum(case when h.tb_='BC' then b.num_ else 0 end) as out_,");
        query.add("sum(case when h.tb_='AE' then b.num_ else 0 end) as reality_");
        query.add("from %s h", AppDB.s_tranh);
        query.add("inner join %s b on b.corp_no_=h.corp_no_ and b.order_sn_ =h.order_sn_", AppDB.s_tranb);

        SqlWhere where = query.addWhere();
        where.eq("h.corp_no_", handle.getCorpNo());
        if (headIn.has("dateFrom_") && headIn.has("dateTo_"))
            where.between("order_date_", headIn.getFastDate("dateFrom_"), headIn.getFastDate("dateTo_"));
        where.build();

        query.add("group by h.order_date_");
        query.openReadonly();
        return query.setState(ServiceState.OK).disableStorage();
    }

    @Description("单日订单明细")
    @DataValidate(value = "order_date_", name = "订单日期")
    public DataSet detail(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select h.order_date_,h.tb_,b.* from %s h", AppDB.s_tranh);
        query.add("inner join s_tranb b on b.corp_no_=h.corp_no_ and b.order_sn_ =h.order_sn_");
        query.addWhere().eq("h.corp_no_", handle.getCorpNo()).eq("order_date_", headIn.getFastDate("order_date_"))
                .build();
        query.add("order by b.code_");
        query.openReadonly();
        return query.setState(ServiceState.OK).disableStorage();
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrOrderReport.class);
    }

}
