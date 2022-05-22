package cn.cerc.sample;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Datetime;
import cn.cerc.db.core.FastDate;
import cn.cerc.db.core.Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * 框架自带工具简单的使用范例
 */
@Slf4j
public class UtilsExample {

    public static void main(String[] args) {
        // 时间工具类 TDateTime
        log.info("当前时间 {}", new Datetime());
        log.info("今日日期 {}", new FastDate());

        DataRow headIn = new DataRow();
        headIn.setValue("entry_date_", "2022-05-20");
        log.info("入职日期 {}", headIn.getFastDate("entry_date_"));

        // 数学工具类 Utils
        log.info("随机数字 {}", Utils.getNumRandom(12));
        log.info("随机字符 {}", Utils.getStrRandom(32));

        // DataSet 范例说明
        DataSet dataSet = new DataSet();
        DataRow head = dataSet.head();
        head.setValue("tbNo", "AB201903270001");
        head.setValue("tDate", new Datetime());
        log.info("dataSet 头部信息 {}", head);

        for (int i = 0; i < 2; i++) {
            dataSet.append();
            dataSet.setValue("code_", "code_" + i);
            dataSet.setValue("name_", "name_" + i);
        }
        log.info("dataSet 完整信息 {}", dataSet);

        // 遍历dataSet数据集获取每个元素的数据
        while (dataSet.fetch()) {
            String code = dataSet.getString("code_");
            String name = dataSet.getString("name_");
            log.info("{}, {}", code, name);
        }
    }

}
