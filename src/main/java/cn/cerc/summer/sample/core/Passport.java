package cn.cerc.summer.sample.core;

import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractHandle;
import cn.cerc.mis.core.IPassport;
import cn.cerc.mis.rds.PassportRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Passport extends AbstractHandle implements IPassport {

    @Override
    public boolean passProc(String versions, String procCode) {
        log.info("versions:{}, procCode: {}", versions, procCode);
        //根据当前token，去数据库检查是否拥有指定的 procCode
        return "user.base".equals(procCode);
    }

    @Override
    public boolean passAction(String procCode, String action) {
        return true;
    }

    @Override
    public PassportRecord getRecord(String procCode) {
        PassportRecord result = new PassportRecord();
        result.setAdmin(true);
        return result;
    }

    @Override
    public boolean passsMenu(String menuCode) {
        return true;
    }

}
