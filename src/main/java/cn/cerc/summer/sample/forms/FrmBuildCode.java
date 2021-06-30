package cn.cerc.summer.sample.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.summer.sample.core.db.Field;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.ui.core.RequestReader;
import cn.cerc.ui.vcl.UIButtonSubmit;
import cn.cerc.ui.vcl.UIForm;
import cn.cerc.ui.vcl.UIInput;
import cn.cerc.ui.vcl.UILine;
import cn.cerc.ui.vcl.UITextArea;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmBuildCode extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        UICustomPage page = new UICustomPage(this);
        this.setName("自动生成范例代码");

        RequestReader reader = new RequestReader(this);
        UIForm form = new UIForm(page.getContent());

        UIInput input = new UIInput(form);
        input.setName("请输入表名: ");
        input.setValue(reader.getString(input, "s_example"));

        UIButtonSubmit submit = new UIButtonSubmit(form);
        submit.setText("确认");

        ArrayList<Field> fields = new ArrayList<>();
        if (reader.hasValue(submit)) {
            String tableId = reader.getString(input, "");
            loadTableFields(tableId, fields);

            new UILine(form);

            UITextArea memo = new UITextArea(form);
            memo.setCols(800);
            memo.setRows(50);
            memo.append(String.format("//tableName: %s", tableId));
            memo.append("");

            memo.append("package cn.cerc.summer.sample.forms;");
            memo.append("");
            memo.append("import cn.cerc.core.Record;");
            memo.append("import cn.cerc.core.TDateTime;");
            memo.append("import cn.cerc.core.Utils;");
            memo.append("import cn.cerc.db.mysql.BuildQuery;");
            memo.append("import cn.cerc.db.mysql.MysqlQuery;");
            memo.append("import cn.cerc.mis.core.CustomService;");
            memo.append("import cn.cerc.mis.core.DataValidateException;");
            memo.append("");
            String className = tableId.substring(0, 1).toUpperCase() + tableId.substring(1, tableId.length());
            className = className.replaceAll("_", "");
            memo.append("public class Svr%s extends CustomService {", className);

            memo.append("");
            buildSearch(tableId, memo, fields);

            memo.append("");
            buildAppend(tableId, memo, fields);

            memo.append("");
            buildModify(tableId, memo, fields);

            memo.append("");
            buildDownload(tableId, memo, fields);

            memo.append("");
            memo.append("}");
        }

        return page;
    }

    private void buildAppend(String tableId, UITextArea memo, ArrayList<Field> fields) {
        memo.append("    // 增加服务");
        memo.append("    public boolean append() throws DataValidateException {");
        memo.append("        Record headIn = getDataIn().getHead();");
        for (Field field : fields) {
            memo.append("        DataValidateException.stopRun(\"%s不允许为空！\", !headIn.hasValue(\"%s\"));",
                    field.getName(), field.getCode());
        }
        memo.append("");
        for (Field field : fields) {
            memo.append("        %s %s = headIn.getString(\"%s\"); // %s", field.getVarType(), field.getVarCode(),
                    field.getCode(), field.getName());
        }
        memo.append("");
        memo.append("        String tableId = \"%s\";", tableId);
        memo.append("        MysqlQuery query = new MysqlQuery(this);");
        memo.append("        query.add(\"select * from %s\", tableId);");
        int count = 0;
        for (Field field : fields) {
            if (count == 0) {
                memo.append("        query.add(\"where %s='%%s'\", %s);", field.getCode(), field.getVarCode());
            } else {
                memo.append("        query.add(\"and %s='%%s'\", %s);", field.getCode(), field.getVarCode());
            }
            count++;
        }
        memo.append("        query.open();");
        memo.append("        DataValidateException.stopRun(\"该记录已经存在\", !query.eof());");
        memo.append("");
        memo.append("        query.append();");
        memo.append("        query.setField(\"id_\", Utils.newGuid());");
        for (Field field : fields) {
            memo.append("        query.setField(\"%s\", %s);", field.getCode(), field.getVarCode());
        }
        memo.append("        query.setField(\"create_user_\", this.getUserCode());");
        memo.append("        query.setField(\"create_time_\", TDateTime.now());");
        memo.append("        query.setField(\"update_user_\", this.getUserCode());");
        memo.append("        query.setField(\"update_time_\", TDateTime.now());");
        memo.append("        query.post();");
        memo.append("");
        memo.append("        getDataOut().getHead().copyValues(query.getCurrent());");
        memo.append("        return true;");
        memo.append("    }");
    }

    private void buildSearch(String tableId, UITextArea memo, ArrayList<Field> fields) {
        memo.append("    // 查询服务");
        memo.append("    public boolean search() {");
        memo.append("        String tableId = \"%s\";", tableId);
        memo.append("        Record headIn = getDataIn().getHead();");
        memo.append("        BuildQuery f = new BuildQuery(this);");
        memo.append("        f.add(\"select * from %s\", tableId);");
        memo.append("");

        for (Field field : fields) {
            memo.append("        if (headIn.hasValue(\"%s\")) {", field.getCode());
            memo.append("            f.byField(\"%s\", headIn.getString(\"%s\"));", field.getCode(), field.getCode());
            memo.append("        }");
        }

        memo.append("        if (headIn.hasValue(\"searchText_\")) {");
        memo.append("            f.byLink(new String[] { \"%s\" }, headIn.getString(\"searchText_\"));",
                fields.get(0).getCode());
        memo.append("        }");
        memo.append("        f.open();");
        memo.append("        getDataOut().appendDataSet(f.getDataSet());");
        memo.append("        return true;");
        memo.append("    }");
    }

    private void buildModify(String tableId, UITextArea memo, ArrayList<Field> fields) {
        memo.append("    // 修改");
        memo.append("    public boolean modify() throws DataValidateException {");
        memo.append("        Record headIn = getDataIn().getHead();");
        for (Field field : fields)
            memo.append("        DataValidateException.stopRun(\"%s不允许为空！\", !headIn.hasValue(\"%s\"));",
                    field.getName(), field.getCode());
        memo.append("");
        for (Field field : fields)
            memo.append("        %s %s = headIn.getString(\"%s\");", field.getVarType(), field.getVarCode(),
                    field.getCode());
        memo.append("");
        memo.append("        String tableId = \"%s\";", tableId);
        memo.append("        MysqlQuery query = new MysqlQuery(this);");
        memo.append("        query.add(\"select * from %s\", tableId);");
        memo.append("        query.add(\"where materKey_='%s'\", materKey);");
        memo.append("        query.open();");
        memo.append("        DataValidateException.stopRun(\"记录不存在\", query.eof());");
        memo.append("");
        memo.append("        query.edit();");
        for (Field field : fields)
            memo.append("        query.setField(\"%s\", %s);", field.getCode(), field.getVarCode());
        memo.append("        query.setField(\"update_user_\", this.getUserCode());");
        memo.append("        query.setField(\"update_time_\", TDateTime.now());");
        memo.append("        query.post();");
        memo.append("        return true;");
        memo.append("    }");
    }

    private void buildDownload(String tableId, UITextArea memo, ArrayList<Field> fields) {
        memo.append("    // 下载");
        memo.append("    public boolean download() throws DataValidateException {");
        memo.append("        Record headIn = getDataIn().getHead();");
        for (Field field : fields)
            memo.append("        DataValidateException.stopRun(\"%s不允许为空！\", !headIn.hasValue(\"%s\"));",
                    field.getName(), field.getCode());
        memo.append("        String userCode = headIn.getString(\"user_code_\");");
        memo.append("");
        memo.append("        String tableId = \"%s\";", tableId);
        memo.append("        MysqlQuery query = new MysqlQuery(this);");
        memo.append("        query.add(\"select * from %s\", tableId);");
        memo.append("        query.add(\"where materKey_='%s'\", materKey);");
        memo.append("        query.open();");
        memo.append("        DataValidateException.stopRun(\"记录不存在\", query.eof());");
        memo.append("");
        memo.append("        getDataOut().getHead().copyValues(query.getCurrent());");
        memo.append("        return true;");
        memo.append("    }");
    }

    private void loadTableFields(String tableId, ArrayList<Field> fields) {
        Element tableRoot;
        try {
            tableRoot = getTable(tableId);
            if (tableRoot != null) {
                for (Element column : tableRoot.getChild("columns").getChildren()) {
                    String fieldCode = column.getAttributeValue("code");
                    String fieldType = column.getAttributeValue("type");
                    String fieldName = "";
                    Element comment = column.getChild("comment");
                    if (comment != null)
                        fieldName = comment.getText();
                    System.out.println(String.format("code: %s, name: %s, type: %s", fieldCode, fieldName, fieldType));
                    fields.add(new Field(fieldCode, fieldName, fieldType));
                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Element getTable(String tableId) throws JDOMException, IOException {
        // 1.创建SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();
        // 2.创建输入流

        String path = FrmBuildCode.class.getClassLoader().getResource("database.xml").getPath();
        InputStream is = new FileInputStream(new File(path));
        // 3.将输入流加载到build中
        Document document = saxBuilder.build(is);
        // 4.获取根节点
        Element rootElement = document.getRootElement();
        // 5.获取子节点
        List<Element> children = rootElement.getChildren();
        for (Element child : children) {
            if ("tables".equals(child.getName())) {
                List<Element> childrenList = child.getChildren();
                for (Element o : childrenList) {
                    if (o.getName().equals("table")) {
//                        System.out.println(o.getAttributeValue("code"));
                        if (tableId.equals(o.getAttributeValue("code"))) {
                            return o;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String path = FrmBuildCode.class.getClassLoader().getResource("database.xml").getPath();
        System.out.println(path);
    }

}
