package cn.cerc.summer.sample.services;

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
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.ado.EntityMany;
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.DataValidateException;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.summer.sample.core.AppDB;
import cn.cerc.summer.sample.entity.Tranb;
import cn.cerc.summer.sample.entity.Tranh;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrBills implements IService {

    public static void main(String[] args) {
        ServiceSign.buildSourceCode(SvrBills.class);
    }

    @Description("根据条件查询单头数据")
    public DataSet search(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.TABLE_TRANH);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("corpNo_", handle.getCorpNo()).and().eq("userCode_", handle.getUserCode());
        if (headIn.has("tbNO_")) {
            addWhere.eq("tbNO_", headIn.getString("tbNO_"));
        }
        if (headIn.has("tbDate_")) {
            addWhere.eq("date(tbDate_)", headIn.getString("tbDate_"));
        }
        if (headIn.has("tb_")) {
            addWhere.eq("tb_", headIn.getString("tb_"));
        }
        if (headIn.has("code_")) {
            addWhere.eq("code_", headIn.getString("code_"));
        }
        addWhere.build();
        query.open();
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @Description("根据条件查询单体数据")
    public DataSet searchBody(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s t1", AppDB.TABLE_TRANB);
        query.add(" inner join %s t2 on t1.code_ = t2.code_", AppDB.TABLE_PARTINFO);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("t1.corpNo_", handle.getCorpNo()).and().eq("t1.userCode_", handle.getUserCode()).and().eq("tbNo_",
                headIn.getString("tbNo_"));
        if (headIn.has("code_")) {
            addWhere.eq("code_", headIn.getString("code_"));
        }
        if (headIn.has("it_")) {
            addWhere.eq("it_", headIn.getString("it_"));
        }
        addWhere.build();
        query.add("order by `it_`");
        query.open();
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "tb_", name = "单据类别", message = "%s 不允许为空")
    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @DataValidate(value = "tbDate_", name = "单据日期", message = "%s 不允许为空")
    @Description("新增单头数据")
    public boolean append(IHandle handle, DataRow headIn) throws DataValidateException {
        try (Transaction tx = new Transaction(handle)) {
            // 插入单头数据
            EntityOne.open(handle, Tranh.class, headIn.getString("createUser_"), headIn.getString("tbNo_"))
                    .isPresentThrow(() -> new RuntimeException("单据已存在，不允许添加")).orElseInsert(item -> {
                        item.setTb_(headIn.getString("tb_"));
                        item.setTbNo_(headIn.getString("tbNo_"));
                        item.setTbDate_(headIn.getFastDate("tbDate_"));
                        item.setRemark_(headIn.getString("remark_"));
                    });
            tx.commit();
        }
        return true;
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @DataValidate(value = "code_", name = "商品编号", message = "%s 不允许为空")
    @DataValidate(value = "num_", name = "商品数量", message = "%s 不允许为空")
    @DataValidate(value = "currentNum_", name = "当前商品数量", message = "%s 不允许为空")
    @Description("新增单据数据")
    public boolean appendBody(IHandle handle, DataRow headIn) throws DataValidateException {
        try (Transaction tx = new Transaction(handle)) {
            // 插入单体数据
            EntityOne
                    .open(handle, Tranb.class, headIn.getString("createUser_"), headIn.getString("tbNo_"),
                            headIn.getString("it_"))
                    .isPresentThrow(() -> new RuntimeException("单据已存在，不允许添加")).orElseInsert(item -> {
                        item.setTbNo_(headIn.getString("tbNo_"));
                        item.setIt_(headIn.getInt("it_"));
                        item.setCode_(headIn.getString("code_"));
                        item.setNum_(headIn.getDouble("num_"));
                        item.setCurrentNum_(headIn.getDouble("currentNum_"));
                    });
            tx.commit();
        }
        return true;
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @Description("根据tbNo_查询单头数据")
    public DataSet downloadHead(IHandle handle, DataRow headIn) throws DataValidateException {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.TABLE_TRANH);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("corpNo_", handle.getCorpNo()).and().eq("userCode_", handle.getUserCode()).and()
                .eq("tbNo_", headIn.getString("tbNo_")).build();
        query.open();
        // 数据展示不能修改数据
        query.setStorage(false);
        // 数据校验
        DataValidateException.stopRun("记录不存在", query.eof());
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @Description("修改单头数据")
    public boolean modifyHead(IHandle handle, DataRow headIn) throws DataValidateException, SQLException {
        FastDate fastDate = headIn.getFastDate("tbDate_");
        String tbNo = headIn.getString("tbNo_");
        log.info("tbDate_:" + fastDate);
        EntityOne.open(handle, Tranh.class, handle.getUserCode(), tbNo)
                .isEmptyThrow(() -> new RuntimeException("记录不存在")).update(item -> {
                    item.setTbDate_(fastDate);
                    item.setRemark_(headIn.getString("remark_"));
                });
        return true;
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @DataValidate(value = "num_", name = "商品数量", message = "%s 不允许为空")
    @DataValidate(value = "it_", name = "单序", message = "%s 不允许为空")
    @Description("修改单体数据")
    public boolean modifyBody(IHandle handle, DataRow headIn) throws DataValidateException {
        EntityOne.open(handle, Tranb.class, handle.getCorpNo(), handle.getUserCode(), headIn.getString("tbNo_"),
                headIn.getString("it_")).isEmptyThrow(() -> new RuntimeException("记录不存在")).update(item -> {
            item.setNum_(headIn.getDouble("num_"));
        });
        return true;
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @Description("删除单头单体数据")
    public boolean delete(IHandle handle, DataRow headIn) {
        try (Transaction tx = new Transaction(handle)) {
            EntityOne.open(handle, Tranh.class, handle.getUserCode(), headIn.getString("tbNo_"))
                    .isEmptyThrow(() -> new RuntimeException("记录不存在")).delete();
            EntityMany.open(handle, Tranb.class, handle.getCorpNo(), handle.getUserCode(), headIn.getString("tbNo_"))
                    .isEmptyThrow(() -> new RuntimeException("记录不存在")).deleteAll();
            tx.commit();
        }
        return true;
    }

    @DataValidate(value = "tbNo_", name = "单号", message = "%s 不允许为空")
    @DataValidate(value = "it_", name = "单序", message = "%s 不允许为空")
    @Description("删除单体数据")
    public boolean deleteBody(IHandle handle, DataRow headIn) {
        try (Transaction tx = new Transaction(handle)) {
            EntityOne.open(handle, Tranb.class, handle.getCorpNo(), handle.getUserCode(), headIn.getString("tbNo_"),
                    headIn.getString("it_")).isEmptyThrow(() -> new RuntimeException("记录不存在")).delete();
            tx.commit();
        }
        return true;
    }
}
