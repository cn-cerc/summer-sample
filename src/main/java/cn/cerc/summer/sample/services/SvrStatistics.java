package cn.cerc.summer.sample.services;

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
import cn.cerc.summer.sample.core.AppDB;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrStatistics implements IService {

    public static void main(String[] args) {
        ServiceSign.buildSourceCode(SvrStatistics.class);
    }

    @Description("统计报表")
    public DataSet statistics(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add(
                "select date(t1.tbDate_) tbDate_,"
                + "sum(if(t1.tb_='AB',t2.num_,0)) numIn_,"
                + "sum(if(t1.tb_='BC',t2.num_,0)) numOut_,"
                + "sum(if(t1.tb_='AE',t2.num_-t2.currentNum_,0)) profitAndLoss_  "
                + "from %s t1",
                AppDB.TABLE_TRANH);
        query.add(" inner join %s t2 on t1.tbNO_ = t2.tbNO_", AppDB.TABLE_TRANB);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("t1.corpNo_", handle.getCorpNo()).and().eq("t1.userCode_", handle.getUserCode());
        if (headIn.has("tbDate_")) {
            addWhere.eq("tbDate_", headIn.getFastDate("tbDate_"));
        }
        addWhere.build();
        query.add("group by date(tbDate_)");

        query.open();
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @Description("统计明细")
    @DataValidate(value = "tbDate_", name = "单据日期", message = "%s 不允许为空")
    public DataSet statisticsDetail(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select date(t1.tbDate_) tbDate_, t2.tbNo_ tbNo_, t3.desc_ desc_, t2.num_ num_ from %s t1", AppDB.TABLE_TRANH);
        query.add(" inner join %s t2 on t1.tbNO_ = t2.tbNO_", AppDB.TABLE_TRANB);
        query.add(" inner join %s t3 on t2.code_ = t3.code_", AppDB.TABLE_PARTINFO);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("t1.corpNo_", handle.getCorpNo()).and().eq("t1.userCode_", handle.getUserCode());
        addWhere.eq("date(tbDate_)", headIn.getString("tbDate_")).build();

        query.open();
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }
}
