package cn.cerc.sample.services.proxy;

import java.util.stream.Stream;

import cn.cerc.db.core.ServerConfig;
import cn.cerc.db.core.Utils;

public class AppConfig {

    /**
     * 应用服务原始标记
     */
    private static final String original = ServerConfig.getInstance().getProperty("application.original");

    /**
     * 获取当前服务版本
     */
    public final static OriginalEdition original() {
        if (Utils.isEmpty(original))
            return OriginalEdition.STD;
        return Stream.of(OriginalEdition.values()).filter(item -> original.equals(item.name())).findFirst()
                .orElse(OriginalEdition.STD);
    }

}
