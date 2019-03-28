package cn.cerc.example.example;

import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
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

        // 数学工具类 utils
        log.info("随机数字 {}", utils.getNumRandom(6));
        log.info("随机字符 {}", utils.getStrRandom(6));

        DataSet dataSet = new DataSet();
        Record head = dataSet.getHead();
        head.setField("tbNo", "AB201903270001");
        head.setField("tDate", TDateTime.Now());

        for (int i = 0; i < 2; i++) {
            dataSet.append();
            dataSet.setField("code_", i);
            dataSet.setField("name_", "name_" + i);
        }
        log.info("{}", head);
        log.info("{}", dataSet);

        while (dataSet.fetch()) {
            String code = dataSet.getString("code_");
            String name = dataSet.getString("name_");
            log.info("{}-{}", code, name);
        }
    }

}
