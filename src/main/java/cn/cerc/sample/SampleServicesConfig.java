package cn.cerc.sample;

import cn.cerc.mis.client.ServiceSign;
import cn.cerc.sample.services.proxy.UserCenterProxy;

import java.util.Set;

public class SampleServicesConfig {

    /**
     * 用户中心服务代理
     */
    private static final UserCenterProxy proxy = new UserCenterProxy();

    public static class SvrEmployee {
        /**
         * 新增人员信息
         */
        public static final ServiceSign append = new ServiceSign("SvrEmployee.append", proxy).setVersion(3)
                .setProperties(Set.of("code_", "name_", "gender_", "entry_date_"));
        /**
         * 删除人员信息
         */
        public static final ServiceSign delete = new ServiceSign("SvrEmployee.delete", proxy).setVersion(5)
                .setProperties(Set.of("code_"));
        /**
         * 获取员工信息
         */
        public static final ServiceSign download = new ServiceSign("SvrEmployee.download", proxy).setVersion(3)
                .setProperties(Set.of("code_"));
        /**
         * 修改人员信息
         */
        public static final ServiceSign modify = new ServiceSign("SvrEmployee.modify", proxy).setVersion(3)
                .setProperties(Set.of("code_", "gender_", "entry_date_"));
        /**
         * 根据条件查询人员信息
         */
        public static final ServiceSign search = new ServiceSign("SvrEmployee.search", proxy).setVersion(3);
    }

}
