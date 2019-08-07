package cn.cerc.example.example;

import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDate;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.other.utils;
import lombok.extern.slf4j.Slf4j;

/**
 * 框架自带工具简述
 */
@Slf4j
public class ToolExample {

    public static void main(String[] args) {

        // 时间工具类 TDateTime
        log.info("当前时间 {}", TDateTime.Now());
        log.info("今日日期 {}", TDate.Today());

        // 数学工具类 utils
        log.info("随机数字 {}", utils.getNumRandom(12));
        log.info("随机字符 {}", utils.getStrRandom(32));

        // DataSet 范例说明
        DataSet dataSet = new DataSet();
        Record head = dataSet.getHead();
        head.setField("tbNo", "AB201903270001");
        head.setField("tDate", TDateTime.Now());
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
