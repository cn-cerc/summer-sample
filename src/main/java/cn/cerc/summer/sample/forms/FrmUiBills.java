package cn.cerc.summer.sample.forms;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.FastDate;
import cn.cerc.db.core.Utils;
import cn.cerc.db.mysql.Transaction;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.summer.sample.core.CustomForm;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.summer.sample.core.ui.UINotice;
import cn.cerc.summer.sample.services.SampleServices.SvrBills;
import cn.cerc.summer.sample.services.SampleServices.SvrGoods;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.DateColumn;
import cn.cerc.ui.columns.DoubleColumn;
import cn.cerc.ui.columns.IntColumn;
import cn.cerc.ui.columns.ItColumn;
import cn.cerc.ui.columns.OptionColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.panels.UIAppendPanel;
import cn.cerc.ui.panels.UIModifyPanel;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UIUrl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Webform(module = "", name = "单据管理ui", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmUiBills extends CustomForm {
    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        new UIUrl(page.getFooter()).setText("商品入库").setSite("FrmUiBills.append").putParam("tb_", "AB");
        new UIUrl(page.getFooter()).setText(" | ");
        new UIUrl(page.getFooter()).setText("商品出库").setSite("FrmUiBills.append").putParam("tb_", "BC");
        new UIUrl(page.getFooter()).setText(" | ");
        new UIUrl(page.getFooter()).setText("核对库存").setSite("FrmUiBills.append").putParam("tb_", "AE");

        UISearchPanel search = new UISearchPanel(page.getContent());
        new StringColumn(search, "单据编号", "tbNO_").setPlaceholder("请输单据编号");
        Map<String, String> tbMap = new LinkedHashMap<String, String>();
        tbMap.put("", "请选择");
        tbMap.put("AB", "商品入库");
        tbMap.put("BC", "商品出库");
        tbMap.put("AE", "核对库存");
        new OptionColumn(search, "单据类别", "tb_", 6).setOptions(tbMap);
        new DateColumn(search, "单据日期", "tbDate_");
        search.readAll();
        ServiceQuery svr = ServiceQuery.open(this, SvrBills.search, search.getRecord());
        if (svr.isFail()) {
            page.setMessage(svr.dataOut().message());
            return page;
        }

        DataSet dataOut = svr.dataOut();
        UIGrid grid = new UIGrid(page.getContent());
        grid.setDataSet(dataOut);

        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(50, 50);
        UIPhoneLine line5 = grid.addPhoneLine(50, 50);
        UIPhoneLine line6 = grid.addPhoneLine(50, 50);
        UIPhoneLine line7 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "账号代码", "userCode_", 2);
        new StringColumn(line2.cell(0), "单据类别", "tb_", 1);
        new StringColumn(line3.cell(1), "单据编号", "tbNo_", 2);
        new StringColumn(line4.cell(0), "单据日期", "tbDate_", 2);
        new StringColumn(line5.cell(1), "建档人员", "createUser_", 1);
        new StringColumn(line6.cell(0), "建档时间", "createDate_", 3);
        CustomColumn defineCell = new CustomColumn(line7.cell(0));
        defineCell.setSpaceWidth(3);
        defineCell.defineCell((column, row) -> {
            new UIUrl(column).setText("明细").setSite("FrmUiBills.modify").putParam("tbNo_", row.getString("tbNo_"))
                    .putParam("tb_", row.getString("tb_"));
            new UIUrl(column).setText(" | ");
            new UIUrl(column).setText("删除").setSite("FrmUiBills.delete").putParam("tbNo_", row.getString("tbNo_"));
        });
        return page;
    }

    public IPage append() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setSite("FrmUiBills").setText("返回");
        UIAppendPanel formInput = new UIAppendPanel(page.getContent());

        String tbNo;
        // 生成tbNo
        if (getRequest().getParameter("tbNo_") != null && getRequest().getParameter("tbNo_") != "") {
            tbNo = getRequest().getParameter("tbNo_");
        } else {
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
            tbNo = getRequest().getParameter("tb_") + uuid;
        }

        // 单身
        ServiceQuery goods = ServiceQuery.open(this, SvrBills.searchBody, Map.of("tbNo_", tbNo));
        if (goods.isFail()) {
            page.setMessage(goods.dataOut().message());
            return page;
        }

        DataSet dataOut = goods.dataOut();
        // 总数
        int total = 0;
        while (dataOut.fetch()) {
            total += dataOut.getInt("num_");
        }

        DataRow dr = new DataRow();
        dr.setValue("total_", total);
        formInput.setRecord(dr);
        new DateColumn(formInput, "单据日期", "tbDate_");
        new StringColumn(formInput, "备注", "remark_");
        new IntColumn(formInput, "总数", "total_", 2).setReadonly(true);

        UIGrid grid = new UIGrid(page.getContent());
        grid.setDataSet(dataOut);

        // 数据条数+1
        String it = String.valueOf(dataOut.size() + 1);
        new UIUrl(page.getFooter()).setText("添加商品").setSite("FrmUiBills.appendGoods").putParam("tbNo_", tbNo)
                .putParam("it_", it);

        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(100);

        // 商品列表
        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "商品", "desc_", 1);
        new StringColumn(line2.cell(0), "数量", "num_", 1);
        CustomColumn defineCell = new CustomColumn(line3.cell(0));
        defineCell.setSpaceWidth(3);
        defineCell.defineCell((column, row) -> {
            new UIUrl(column).setText("修改").setSite("FrmUiBills.modifyBody").putParam("tbNo_", row.getString("tbNo_"))
                    .putParam("it_", row.getString("it_"));
            new UIUrl(column).setText(" | ");
            new UIUrl(column).setText("删除").setSite("FrmUiBills.deleteBody").putParam("tbNo_", row.getString("tbNo_"))
                    .putParam("it_", row.getString("it_"));
        });

        if (!Utils.isEmpty(formInput.readAll())) {
            // 保存单头
            Map<String, Object> headIn = new LinkedHashMap<>();
            headIn.put("tbDate_", formInput.getRecord().getFastDate("tbDate_"));
            headIn.put("remark_", formInput.getRecord().getString("remark_"));
            headIn.put("tb_", tbNo.substring(0, 2));
            headIn.put("tbNo_", tbNo);
            headIn.put("total_", total);
            ServiceQuery svr = ServiceQuery.open(this, SvrBills.append, headIn);
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }
            UINotice.sendInfo(getSession(), getClass(), "execute", "新增成功");
            return new RedirectPage(this, "FrmUiBills");
        }
        return page;
    }

    public IPage appendGoods() {
        String tbNo = getRequest().getParameter("tbNo_");
        String it = getRequest().getParameter("it_");
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmUiBills.append").putParam("tbNo_", tbNo);
        UIAppendPanel form = new UIAppendPanel(page.getContent());

        // 查询商品数据
        ServiceQuery svrOfGoods = ServiceQuery.open(this, SvrGoods.search, form.getRecord());
        if (svrOfGoods.isFail()) {
            page.setMessage(svrOfGoods.dataOut().message());
            return page;
        }
        DataSet ds = svrOfGoods.dataOut();
        Map<String, String> goodsMap = new LinkedHashMap<String, String>();
        while (ds.fetch()) {
            goodsMap.put(ds.getString("code_"), ds.getString("desc_"));
        }
        new OptionColumn(form, "商品", "code_", 6).setOptions(goodsMap);
        new DoubleColumn(form, "商品数量", "num_", 6);

        if (!Utils.isEmpty(form.readAll())) {
            // 查询库存
            ServiceQuery svr1 = ServiceQuery.open(this, SvrGoods.download,
                    Map.of("code_", form.getRecord().getString("code_")));
            if (svr1.isFail()) {
                page.setMessage(svr1.dataOut().message());
                return page;
            }
            DataRow dataRow = svr1.dataOut().current();
            double stock = dataRow.getDouble("stock_");

            // 保存当前数量（删除单据时恢复数量）
            DataRow row = new DataRow();
            row.copyValues(form.getRecord());
            row.setValue("currentNum_", stock);
            row.setValue("tbNo_", tbNo);
            row.setValue("it_", it);
            UIModifyPanel form1 = new UIModifyPanel(page.getContent());
            form1.setRecord(row);

            // 判断输入是否大于0
            double num = form1.getRecord().getDouble("num_");
            if (num <= 0) {
                page.setMessage("数量必须大于0");
                return page;
            }

            // 保存单身
            ServiceQuery svr = ServiceQuery.open(this, SvrBills.appendBody, form1.getRecord());
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }

            // 计算库存
            switch (form1.getRecord().getString("tbNo_").substring(0, 2)) {
            case "AB":
                stock += num;
                break;
            case "BC":
                // 判断输入是否大于库存
                if (num > stock) {
                    page.setMessage("库存不足");
                    return page;
                }
                stock -= num;
                break;
            case "AE":
                stock = num;
                break;
            }

            // 更新库存
            Map<String, Object> headIn = new LinkedHashMap<>();
            headIn.put("code_", form.getRecord().getString("code_"));
            headIn.put("stock_", stock);
            ServiceQuery modify = ServiceQuery.open(this, SvrGoods.modifyStock, headIn);
            if (modify.isFail()) {
                page.setMessage(modify.dataOut().message());
                return page;
            }

            UINotice.sendInfo(getSession(), getClass(), "execute", "新增成功");
            return new RedirectPage(this, "FrmUiBills.append").put("tbNo_", tbNo);
        }
        return page;
    }

    public IPage appendBody() {
        UICustomPage page = new UICustomPage(this);
        String tbDate = getRequest().getParameter("tbDate_");
        String remark = getRequest().getParameter("remark_");
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmUiBills.append").putParam("tbDate_", tbDate).putParam("remark_", remark);
        new UINotice(page.getFrontPanel()).receive(this, "execute");

        UIAppendPanel append = new UIAppendPanel(page.getContent());

        ServiceQuery svr = ServiceQuery.open(this, SvrGoods.search, append.getRecord());
        if (svr.isFail()) {
            page.setMessage(svr.dataOut().message());
            return page;
        }

        DataSet dataOut = svr.dataOut();
        UIGrid grid = new UIGrid(page.getContent());
        grid.setDataSet(dataOut);

        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(50, 50);
        UIPhoneLine line5 = grid.addPhoneLine(50, 50);
        UIPhoneLine line6 = grid.addPhoneLine(50, 50);
        UIPhoneLine line7 = grid.addPhoneLine(50, 50);
        UIPhoneLine line8 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "账号", "userCode_", 4);
        new StringColumn(line2.cell(0), "商品编号", "code_", 2);
        new StringColumn(line3.cell(1), "品名", "desc_", 2);
        new StringColumn(line4.cell(0), "规格", "spec_", 2);
        new StringColumn(line5.cell(1), "单位", "unit_", 1);
        new StringColumn(line6.cell(0), "库存", "stock_", 2);
        new StringColumn(line7.cell(0), "备注", "remark_", 4);
        CustomColumn defineCell = new CustomColumn(line8.cell(0));
        defineCell.setSpaceWidth(4);
        defineCell.defineCell((column, row) -> {
            new UIUrl(column).setText("修改").setSite("FrmUiGoods.modify").putParam("code", row.getString("code_"));

        });
        return page;
    }

    public IPage modify() throws ParseException {
        try (Transaction tx = new Transaction(this)) {
            log.info("auto commit {}", this.getMysql().getClient().getConnection().getAutoCommit());
            UICustomPage page = new UICustomPage(this);
            new UIUrl(page.getFrontPanel()).setSite("FrmUiBills").setText("返回");

            // 接收传过来的tbNo_值(相当于ID)
            String tbNo = getRequest().getParameter("tbNo_");
            String tbDateStr = getRequest().getParameter("tbDate_");
            Date tbDate = null;
            if (tbDateStr != null && tbDateStr != "") {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                tbDate = sdf.parse(tbDateStr);
            }
            String remarkStr = getRequest().getParameter("remark_");

            // 单头
            ServiceQuery svr = ServiceQuery.open(this, SvrBills.downloadHead, Map.of("tbNo_", tbNo));
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }

            // 单身
            ServiceQuery goods = ServiceQuery.open(this, SvrBills.searchBody, Map.of("tbNo_", tbNo));
            if (goods.isFail()) {
                page.setMessage(goods.dataOut().message());
                return page;
            }

            DataSet dataOut = goods.dataOut();
            // 总数
            int total = 0;
            while (dataOut.fetch()) {
                total += dataOut.getInt("num_");
            }

            DataRow dr = new DataRow();
            dr.copyValues(svr.dataOut().current());
            dr.setValue("total_", total);
            if (tbDate != null) {
                dr.setValue("tbDate_", tbDate);
            }
            if (remarkStr != null && remarkStr != "") {
                dr.setValue("remark_", remarkStr);
            }
            UIModifyPanel form = new UIModifyPanel(page.getContent());
            form.setRecord(dr);

            FastDate date = dr.getFastDate("tbDate_");
            String remark = dr.getString("remark_");

            new DateColumn(form, "单据日期", "tbDate_");
            new StringColumn(form, "备注", "remark_");
            new IntColumn(form, "总数", "total_", 2).setReadonly(true);

            UIGrid grid = new UIGrid(page.getContent());
            grid.setDataSet(dataOut);

            // 数据条数+1
            String it = String.valueOf(dataOut.size() + 1);
            new UIUrl(page.getFooter()).setText("添加商品").setSite("FrmUiBills.appendGoods").putParam("tbNo_", tbNo)
                    .putParam("it_", it).putParam("tbDate_", date.toString()).putParam("remark_", remark);

            UIPhoneLine line1 = grid.addPhoneLine(50, 50);
            UIPhoneLine line2 = grid.addPhoneLine(50, 50);
            UIPhoneLine line3 = grid.addPhoneLine(100);

            // 商品列表
            new ItColumn(line1.cell(0));
            new StringColumn(line1.cell(1), "商品", "desc_", 1);
            new StringColumn(line2.cell(0), "数量", "num_", 1);
            CustomColumn defineCell = new CustomColumn(line3.cell(0));
            defineCell.setSpaceWidth(3);
            defineCell.defineCell((column, row) -> {
                new UIUrl(column).setText("修改").setSite("FrmUiBills.modifyBody")
                        .putParam("tbNo_", row.getString("tbNo_")).putParam("it_", row.getString("it_"))
                        .putParam("tbDate_", date.toString()).putParam("remark_", remark);
                new UIUrl(column).setText(" | ");
                new UIUrl(column).setText("删除").setSite("FrmUiBills.deleteBody")
                        .putParam("tbNo_", row.getString("tbNo_")).putParam("it_", row.getString("it_"));
            });

            if (!Utils.isEmpty(form.readAll())) {
                Map<String, Object> headIn = new LinkedHashMap<>();
                headIn.put("tbDate_", form.getRecord().getFastDate("tbDate_"));
                headIn.put("remark_", form.getRecord().getString("remark_"));
                headIn.put("tb_", tbNo.substring(0, 2));
                headIn.put("tbNo_", tbNo);
                headIn.put("total_", total);
                ServiceQuery svr1 = ServiceQuery.open(this, SvrBills.modifyHead, headIn);
                if (svr1.isFail()) {
                    page.setMessage(svr1.dataOut().message());
                    return page;
                }
                UINotice.sendInfo(getSession(), getClass(), "execute", "修改成功成功");
                return new RedirectPage(this, "FrmUiBills");
            }
            log.info("auto commit {}", this.getMysql().getClient().getConnection().getAutoCommit());
            tx.commit();
            return page;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public IPage modifyBody() {
        // 接收传过来的tbNo_值(相当于ID)
        String tbNo = getRequest().getParameter("tbNo_");
        String tb = tbNo.substring(0, 2);
        String it = getRequest().getParameter("it_");
        String tbDate = getRequest().getParameter("tbDate_");
        String remark = getRequest().getParameter("remark_");
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmUiBills.append").putParam("tbNo_", tbNo).putParam("tbDate_", tbDate).putParam("remark_", remark);

        // 查询单体
        Map<String, Object> headIn1 = new LinkedHashMap<>();
        headIn1.put("tbNo_", tbNo);
        headIn1.put("it_", it);
        ServiceQuery svrDetail = ServiceQuery.open(this, SvrBills.searchBody, headIn1);
        if (svrDetail.isFail()) {
            page.setMessage(svrDetail.dataOut().message());
            return page;
        }

        DataRow row = new DataRow();
        row.copyValues(svrDetail.dataOut().current());
        double dbNum = row.getDouble("num_");

        UIModifyPanel form = new UIModifyPanel(page.getContent());
        // 数据拷贝
        form.setRecord(row);
        // 不能直接set
        new StringColumn(form, "商品", "desc_").setReadonly(true);
        new DoubleColumn(form, "商品数量", "num_", 6);

        if (!Utils.isEmpty(form.readAll())) {
            // 查询库存
            ServiceQuery svr1 = ServiceQuery.open(this, SvrGoods.download,
                    Map.of("code_", form.getRecord().getString("code_")));
            if (svr1.isFail()) {
                page.setMessage(svr1.dataOut().message());
                return page;
            }
            DataRow dataRow = svr1.dataOut().current();
            double stock = dataRow.getDouble("stock_");
            // 判断输入是否大于0
            double num = form.getRecord().getDouble("num_");
            if (num <= 0) {
                page.setMessage("数量必须大于0");
                return page;
            }
            // 输入差值
            double result = num - dbNum;

            // 计算库存
            switch (tb) {
            case "AB":
                stock += result;
                break;
            case "BC":
                // 判断输入是否大于库存
                if (num > stock) {
                    page.setMessage("库存不足");
                    return page;
                }
                stock -= result;
                break;
            case "AE":
                stock = result;
                break;
            }

            // 更新库存
            Map<String, Object> headIn = new LinkedHashMap<>();
            headIn.put("code_", form.getRecord().getString("code_"));
            headIn.put("stock_", stock);
            ServiceQuery modify = ServiceQuery.open(this, SvrGoods.modifyStock, headIn);
            if (modify.isFail()) {
                page.setMessage(modify.dataOut().message());
                return page;
            }

            // 更新数量
            headIn.put("tbNo_", tbNo);
            headIn.put("num_", form.getRecord().getDouble("num_"));
            headIn.put("it_", row.getString("it_"));
            ServiceQuery modify1 = ServiceQuery.open(this, SvrBills.modifyBody, form.getRecord());
            if (modify1.isFail()) {
                page.setMessage(svrDetail.dataOut().message());
                return page;
            }

            UINotice.sendInfo(getSession(), getClass(), "execute", "修改成功");
            return new RedirectPage(this, "FrmUiBills.append").put("tbNo_", tbNo);
        }
        return page;
    }

    public IPage delete() {
        try (Transaction tx = new Transaction(this)) {
            String tbNo = getRequest().getParameter("tbNo_");
            UrlRecord url = new UrlRecord();
            url.setSite("FrmUiBills");

            // 查询单体数据
            ServiceQuery svr = ServiceQuery.open(this, SvrBills.searchBody, Map.of("tbNo_", tbNo));
            if (svr.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }
            DataRow row = svr.dataOut().current();

            // 查询库存
            ServiceQuery svr1 = ServiceQuery.open(this, SvrGoods.download, Map.of("code_", row.getString("code_")));
            if (svr1.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }
            DataRow dataRow = svr1.dataOut().current();
            double stock = dataRow.getDouble("stock_");

            // 删除单头单体
            Map<String, Object> headIn = new LinkedHashMap<>();
            headIn.put("tbNo_", tbNo);
            headIn.put("it_", row.getString("it_"));
            ServiceQuery svr2 = ServiceQuery.open(this, SvrBills.delete, headIn);
            if (svr2.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }

            // 库存恢复
//        headIn.put("num_", row.getString("num_"));
            double num = row.getDouble("num_");
            double currentNum = row.getDouble("currentNum_");

            // 计算库存
            switch (row.getString("tbNo_").substring(0, 2)) {
            case "AB":
                stock -= num;
                break;
            case "BC":
                stock += num;
                break;
            case "AE":
                stock += (currentNum - num);
                break;
            }

            // 更新库存
            Map<String, Object> headIn1 = new LinkedHashMap<>();
            headIn1.put("code_", row.getString("code_"));
            headIn1.put("stock_", stock);
            ServiceQuery modify = ServiceQuery.open(this, SvrGoods.modifyStock, headIn1);
            if (modify.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }

            UINotice.sendInfo(getSession(), getClass(), "execute", "删除成功");
            tx.commit();
            return new RedirectPage(this, url.getUrl());
        }
    }

    public IPage deleteBody() {
        try (Transaction tx = new Transaction(this)) {
            String it = getRequest().getParameter("it_");
            String tbNo = getRequest().getParameter("tbNo_");
            UrlRecord url = new UrlRecord();
            url.setSite("FrmUiBills.append");

            Map<String, Object> headIn = new LinkedHashMap<>();
            headIn.put("tbNo_", tbNo);
            headIn.put("it_", it);

            // 查询单体数据
            ServiceQuery svr = ServiceQuery.open(this, SvrBills.searchBody, headIn);
            if (svr.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }
            DataRow row = svr.dataOut().current();

            // 查询库存
            ServiceQuery svr1 = ServiceQuery.open(this, SvrGoods.download, Map.of("code_", row.getString("code_")));
            if (svr1.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }
            DataRow dataRow = svr1.dataOut().current();
            double stock = dataRow.getDouble("stock_");

            // 删除单体
            ServiceQuery svr2 = ServiceQuery.open(this, SvrBills.deleteBody, headIn);
            if (svr2.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }

            // 库存恢复
            double num = row.getDouble("num_");
            double currentNum = row.getDouble("currentNum_");

            // 计算库存
            switch (row.getString("tbNo_").substring(0, 2)) {
            case "AB":
                stock -= num;
                break;
            case "BC":
                stock += num;
                break;
            case "AE":
                stock += (currentNum - num);
                break;
            }

            // 更新库存
            Map<String, Object> headIn1 = new LinkedHashMap<>();
            headIn1.put("code_", row.getString("code_"));
            headIn1.put("stock_", stock);
            ServiceQuery modify = ServiceQuery.open(this, SvrGoods.modifyStock, headIn1);
            if (modify.isFail()) {
                UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
            }

            UINotice.sendInfo(getSession(), getClass(), "execute", "删除成功");
            tx.commit();
            return new RedirectPage(this, url.getUrl()).put("tbNo_", tbNo);
        }
    }

}
