package cn.cerc.sample.services;

import java.util.OptionalInt;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.ado.EntityMany;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.sample.entity.PartInfoEntity;
import cn.cerc.sample.entity.TranBodyEntity;
import cn.cerc.sample.entity.TranHeadEntity;

@Permission(Permission.GUEST)
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SvrTranBody implements IService {

    @Description("添加单身商品")
    @DataValidate(value = "order_sn_", name = "订单单号")
    @DataValidate(value = "code_", name = "商品编号")
    @DataValidate(value = "num_", name = "商品数量")
    public DataSet append(IHandle handle, DataRow headIn) {
        DataSet dataSet = new DataSet();
        String orderSN = headIn.getString("order_sn_");
        String code = headIn.getString("code_");
        double num = headIn.getDouble("num_");
        if (num <= 0)
            throw new RuntimeException("商品数量必须大于0");

        try (Transaction tx = new Transaction(handle)) {
            EntityOne<TranHeadEntity> head = EntityOne.open(handle, TranHeadEntity.class, orderSN)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 单号不存在", orderSN)));

            EntityMany<TranBodyEntity> entity = EntityMany.open(handle, TranBodyEntity.class, orderSN);
            if (entity.stream().anyMatch(item -> item.getCode_().equals(code)))
                throw new RuntimeException(String.format("%s 单序商品已经存在单身，不允许重复添加", code));

            EntityOne<PartInfoEntity> partInfo = EntityOne.open(handle, PartInfoEntity.class, code)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 商品编号不存在", code)));
            double stock = partInfo.get().getStock_();// 原始库存
            double increment = num - stock;
            partInfo.update(item -> item.appendStock(head.get().getTb_(), num));

            OptionalInt maxIt = entity.stream().mapToInt(TranBodyEntity::getIt_).max();
            int it = maxIt.isEmpty() ? 1 : maxIt.getAsInt() + 1;
            entity.insert(item -> {
                item.setOrder_sn_(orderSN);
                item.setIt_(it);
                item.setCode_(code);
                item.setNum_(num);
                item.setIncrement_(increment);
            });
            head.update(item -> item.setTotal_(item.getTotal_() + num));
            tx.commit();
            dataSet.append().setValue("it_", it);
        }
        return dataSet.setState(ServiceState.OK);
    }

    @Description("获取单身信息")
    @DataValidate(value = "order_sn_", name = "订单单号")
    @DataValidate(value = "it_", name = "订单单序")
    public DataSet download(IHandle handle, DataRow headIn) {
        String orderSN = headIn.getString("order_sn_");
        String it = headIn.getString("it_");
        DataRow dataRow = EntityOne.open(handle, TranBodyEntity.class, orderSN, it)
                .isEmptyThrow(() -> new RuntimeException("订单单身不存在")).current();
        DataSet dataSet = new DataSet();
        dataSet.append().copyRecord(dataRow);
        return dataSet.setState(ServiceState.OK);
    }

    @Description("修改单身信息")
    @DataValidate(value = "order_sn_", name = "订单单号")
    @DataValidate(value = "it_", name = "订单单序")
    @DataValidate(value = "num_", name = "商品数量")
    public DataSet modify(IHandle handle, DataRow headIn) {
        DataSet dataSet = new DataSet();

        String orderSN = headIn.getString("order_sn_");
        String it = headIn.getString("it_");
        double num = headIn.getDouble("num_");// 新的数量
        if (num <= 0)
            throw new RuntimeException("商品数量必须大于0");
        try (Transaction tx = new Transaction(handle)) {
            EntityOne<TranHeadEntity> head = EntityOne.open(handle, TranHeadEntity.class, orderSN)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 单号不存在", orderSN)));

            EntityOne<TranBodyEntity> entity = EntityOne.open(handle, TranBodyEntity.class, orderSN, it)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 单序商品不存在于单身，不允许修改数据", it)));
            String code = entity.get().getCode_();
            double increment = entity.get().getIncrement_();// 原始增量
            double original = entity.get().getNum_();// 原始数量
            double diff = num - original;

            EntityOne<PartInfoEntity> partInfo = EntityOne.open(handle, PartInfoEntity.class, code)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 商品编号不存在", code)));
            partInfo.update(item -> item.updateStock(head.get().getTb_(), diff));

            entity.update(item -> {
                item.setNum_(num);
                item.setIncrement_(increment + diff);
            });
            head.update(item -> item.setTotal_(item.getTotal_() + diff));
            tx.commit();
            dataSet.append().setValue("it_", it);
        }
        return dataSet.setState(ServiceState.OK);
    }

    @Description("删除单身信息")
    @DataValidate(value = "order_sn_", name = "订单单号")
    @DataValidate(value = "it_", name = "订单单序")
    public DataSet delete(IHandle handle, DataRow headIn) {
        DataSet dataSet = new DataSet();

        String orderSN = headIn.getString("order_sn_");
        String it = headIn.getString("it_");

        try (Transaction tx = new Transaction(handle)) {
            EntityOne<TranHeadEntity> head = EntityOne.open(handle, TranHeadEntity.class, orderSN)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 单号不存在", orderSN)));

            EntityOne<TranBodyEntity> entity = EntityOne.open(handle, TranBodyEntity.class, orderSN, it)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 单序商品不存在于单身，不允许删除数据", it)));
            String code = entity.get().getCode_();
            double original = entity.get().getNum_();// 原始数量
            double increment = entity.get().getIncrement_();// 变化增量

            EntityOne<PartInfoEntity> partInfo = EntityOne.open(handle, PartInfoEntity.class, code)
                    .isEmptyThrow(() -> new RuntimeException(String.format("%s 商品编号不存在", code)));
            partInfo.update(item -> item.recycleStock(head.get().getTb_(), original, increment));

            entity.delete();
            head.update(item -> item.setTotal_(item.getTotal_() - original));
            tx.commit();
            dataSet.append().setValue("it_", it);
        }
        return dataSet.setState(ServiceState.OK);
    }

    public static void main(String[] args) {
        // 生成当前对象的服务签名
        ServiceSign.buildSourceCode(SvrTranBody.class);
    }

}
