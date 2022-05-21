package cn.cerc.sample.enums;

import java.util.stream.Stream;

import lombok.Getter;

public enum TBType {
    AB("进货单"), BC("出货单"), AE("盘点单");

    @Getter
    private String desc;

    private TBType(String desc) {
        this.desc = desc;
    }

    public static void validateTB(String tb) {
        boolean value = Stream.of(TBType.values()).anyMatch(item -> item.name().equals(tb));
        if (!value)
            throw new RuntimeException("非法单别！");
    }

}