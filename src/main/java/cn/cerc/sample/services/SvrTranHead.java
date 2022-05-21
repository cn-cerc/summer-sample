package cn.cerc.sample.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Datetime;
import cn.cerc.db.core.FastDate;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlWhere;
import cn.cerc.db.core.Utils;
import cn.cerc.db.mysql.MysqlQuery;
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.ado.EntityMany;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.core.AppDB;
import cn.cerc.sample.entity.TranBodyEntity;
import cn.cerc.sample.entity.TranHeadEntity;
import cn.cerc.sample.enums.TBType;

@Permission(Permission.GUEST)
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SvrTranHead implements IService {

    @Description("订单查询服务")
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

    @Description("新增商品信息")
    @DataValidate(value = "tb_", name = "单别")
    public DataSet append(IHandle handle, DataRow headIn) {
        String tb = headIn.getString("tb_");
        TBType.validateTB(tb);

        DataSet dataSet = new DataSet();
        try (Transaction tx = new Transaction(handle)) {
            String orderSn = tb + new Datetime().getYearMonth() + Utils.getNumRandom(3);
            EntityOne<TranHeadEntity> entity = EntityOne.open(handle, TranHeadEntity.class, orderSn)
                    .isPresentThrow(() -> new RuntimeException("该订单已经存在，不允许重复登记"));
            entity.orElseInsert(item -> {
                item.setTb_(tb);
                item.setOrder_sn_(orderSn);
                item.setOrder_date_(new FastDate());
            });
            dataSet.append().copyRecord(entity.current());
            tx.commit();
        }
        return dataSet.setState(ServiceState.OK).disableStorage();
    }

    @Description("获取订单信息")
    @DataValidate(value = "order_sn_", name = "订单单号")
    public DataSet download(IHandle handle, DataRow headIn) {
        String orderSN = headIn.getString("order_sn_");
        DataRow dataHead = EntityOne.open(handle, TranHeadEntity.class, orderSN)
                .isEmptyThrow(() -> new RuntimeException(String.format("%s 单号不存在", orderSN))).current();
        DataSet dataSet = EntityMany.open(handle, TranBodyEntity.class, orderSN).dataSet();
        if (dataSet.eof())
            dataSet = new DataSet();
        dataSet.setReadonly(false);
        dataSet.head().copyValues(dataHead);
        return dataSet.setState(ServiceState.OK).disableStorage();
    }

    @Description("修改单头信息")
    @DataValidate(value = "order_sn_", name = "订单单号")
    @DataValidate(value = "order_date_", name = "单据日期")
    public DataSet modify(IHandle handle, DataRow headIn) {
        String orderSn = headIn.getString("order_sn_");
        try (Transaction tx = new Transaction(handle)) {
            EntityOne.open(handle, TranHeadEntity.class, orderSn)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 料号不存在", orderSn))).update(item -> {
                        item.setOrder_date_(headIn.getFastDate("order_date_"));
                        item.setRemark_(headIn.getString("remark_"));
                    });
            tx.commit();
        }
        return new DataSet().setState(ServiceState.OK).disableStorage();
    }

    @Description("删除订单信息")
    @DataValidate(value = "order_sn_", name = "订单单号")
    public DataSet delete(IHandle handle, DataRow headIn) {
        String orderSN = headIn.getString("order_sn_");
        try (Transaction tx = new Transaction(handle)) {
            MysqlQuery query = new MysqlQuery(handle);
            query.add("select * from %s", AppDB.s_tranb);
            query.addWhere().eq("corp_no_", handle.getCorpNo()).eq("order_sn_", orderSN).build();
            query.setMaximum(1);
            query.openReadonly();
            if (!query.eof()) {
                throw new RuntimeException(String.format("%s 单号单身数据不为空，当前环境不允许删除", orderSN));
            }

            EntityOne.open(handle, TranHeadEntity.class, orderSN)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 单号不存在", orderSN))).delete();
            tx.commit();
        }
        return new DataSet().setState(ServiceState.OK).disableStorage();
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrTranHead.class);
    }

}
