package cn.cerc.training.common;

import cn.cerc.mis.core.AbstractHandle;
import cn.cerc.mis.core.IPassport;
import cn.cerc.mis.rds.PassportRecord;

public class Passport extends AbstractHandle implements IPassport {

    @Override
    public boolean passProc(String versions, String procCode) {
        return true;
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
