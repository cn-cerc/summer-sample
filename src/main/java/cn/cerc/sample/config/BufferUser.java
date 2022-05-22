package cn.cerc.sample.config;

import cn.cerc.mis.core.IBufferKey;

public enum BufferUser implements IBufferKey {

    Notice_UserCode, UserList_CorpNo;

    @Override
    public int getStartingPoint() {
        return 1000;
    }

    @Override
    public int getMinimumNumber() {
        return 1;
    }

    @Override
    public int getMaximumNumber() {
        return 99;
    }

}
