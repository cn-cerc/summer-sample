package cn.cerc.summer.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cerc.core.DataSet;
import cn.cerc.core.Record;
import cn.cerc.core.TDate;
import cn.cerc.core.TDateTime;
import cn.cerc.core.Utils;

/**
 * 框架自带工具简单的使用范例
 */
public class UtilsExample {
    private static final Logger log = LoggerFactory.getLogger(UtilsExample.class);

    public static void main(String[] args) {
        // 时间工具类 TDateTime
        log.info("当前时间 {}", TDateTime.now());
        log.info("今日日期 {}", TDate.today());

        // 数学工具类 Utils
        log.info("随机数字 {}", Utils.getNumRandom(12));
        log.info("随机字符 {}", Utils.getStrRandom(32));

        // DataSet 范例说明
        DataSet dataSet = new DataSet();
        Record head = dataSet.getHead();
        head.setField("tbNo", "AB201903270001");
        head.setField("tDate", TDateTime.now());
        log.info("dataSet 头部信息 {}", head);

        for (int i = 0; i < 2; i++) {
            dataSet.append();
            dataSet.setField("code_", "code_" + i);
            dataSet.setField("name_", "name_" + i);
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
