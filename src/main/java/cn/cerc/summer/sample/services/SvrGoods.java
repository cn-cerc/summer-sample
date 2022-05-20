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
import cn.cerc.mis.ado.EntityOne;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.DataValidate;
import cn.cerc.mis.core.DataValidateException;
import cn.cerc.mis.core.IService;
import cn.cerc.mis.core.ServiceState;
import cn.cerc.mis.security.Permission;
import cn.cerc.summer.sample.core.AppDB;
import cn.cerc.summer.sample.entity.Partinfo;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Permission(Permission.GUEST)
public class SvrGoods implements IService {

    public static void main(String[] args) {
        ServiceSign.buildSourceCode(SvrGoods.class);
    }

    @Description("根据条件查询商品数据")
    public DataSet search(IHandle handle, DataRow headIn) {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.TABLE_PARTINFO);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("corpNo_", handle.getCorpNo()).and().eq("userCode_", handle.getUserCode());
        if (headIn.has("code_")) {
            addWhere.eq("code_", headIn.getString("code_"));
        }
        if (headIn.has("searchText_")) {
            addWhere.like("desc_", headIn.getString("searchText_"));
        }
        addWhere.build();

        query.open();
        query.setStorage(false);
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "code_", name = "商品编号", message = "%s 不允许为空")
    @DataValidate(value = "desc_", name = "商品名", message = "%s 不允许为空")
    @DataValidate(value = "spec_", name = "商品规格", message = "%s 不允许为空")
    @DataValidate(value = "unit_", name = "商品单位", message = "%s 不允许为空")
    @Description("新增商品数据")
    public boolean append(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");
        EntityOne.open(handle, Partinfo.class, handle.getUserCode(), code)
                .isPresentThrow(() -> new RuntimeException("商品编号已存在，不允许添加")).orElseInsert(item -> {
                    item.setCode_(code);
                    item.setDesc_(headIn.getString("desc_"));
                    item.setSpec_(headIn.getString("spec_"));
                    item.setUnit_(headIn.getString("unit_"));
                    item.setRemark_(headIn.getString("remark_"));
                });

        return true;
    }

    @DataValidate(value = "code_", name = "商品编号", message = "%s 不允许为空")
    @Description("根据code查询商品数据")
    public DataSet download(IHandle handle, DataRow headIn) throws DataValidateException {
        MysqlQuery query = new MysqlQuery(handle);
        query.add("select * from %s", AppDB.TABLE_PARTINFO);
        // 查询条件
        SqlWhere addWhere = query.addWhere();
        addWhere.eq("corpNo_", handle.getCorpNo()).and().eq("userCode_", handle.getUserCode()).and()
                .eq("code_", headIn.getString("code_")).build();
        query.open();
        // 数据展示不能修改数据
        query.setStorage(false);
        // 数据校验
        DataValidateException.stopRun("记录不存在", query.eof());
        return query.setState(ServiceState.OK);
    }

    @DataValidate(value = "code_", name = "商品编号", message = "%s 不允许为空")
    @DataValidate(value = "desc_", name = "商品名", message = "%s 不允许为空")
    @DataValidate(value = "spec_", name = "商品规格", message = "%s 不允许为空")
    @DataValidate(value = "unit_", name = "商品单位", message = "%s 不允许为空")
    @Description("修改商品数据")
    public boolean modify(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");
        String userCode = handle.getUserCode();
        EntityOne.open(handle, Partinfo.class, userCode, code).isEmptyThrow(() -> new RuntimeException("记录不存在"))
                .update(item -> {
                    item.setDesc_(headIn.getString("desc_"));
                    item.setSpec_(headIn.getString("spec_"));
                    item.setUnit_(headIn.getString("unit_"));
                    item.setRemark_(headIn.getString("remark_"));
                });

        return true;
    }

    @DataValidate(value = "code_", name = "商品编号", message = "%s 不允许为空")
    @Description("删除商品数据")
    public boolean delete(IHandle handle, DataRow headIn) {
        String code = headIn.getString("code_");
        String userCode = handle.getUserCode();
        EntityOne.open(handle, Partinfo.class, userCode, code).isEmptyThrow(() -> new RuntimeException("记录不存在"))
                .delete();
        return true;
    }
    
    @DataValidate(value = "code_", name = "商品编号", message = "%s 不允许为空")
    @DataValidate(value = "stock_", name = "商品库存", message = "%s 不允许为空")
    @Description("更新商品库存")
    public boolean modifyStock(IHandle handle, DataRow headIn) throws DataValidateException {
        String code = headIn.getString("code_");
        String userCode = handle.getUserCode();
        EntityOne.open(handle, Partinfo.class, userCode, code).isEmptyThrow(() -> new RuntimeException("记录不存在"))
                .update(item -> {
                    item.setStock_(headIn.getDouble("stock_"));
                });

        return true;
    }
}
