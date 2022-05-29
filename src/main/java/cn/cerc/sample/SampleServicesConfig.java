package cn.cerc.sample;

import cn.cerc.mis.client.ServiceSign;
import cn.cerc.sample.services.proxy.UserCenterProxy;

import java.util.Set;

public class SampleServicesConfig {

    /**
     * 用户中心服务代理
     */
    private static final UserCenterProxy proxy = new UserCenterProxy();

    /** 第三代服务编写方式(正式项目中使用) */
    public static class SvrEmployeeV3 {
        /** 新增人员信息 */
        public static final ServiceSign append = new ServiceSign("SvrEmployeeV3.append", proxy).setVersion(3)
                .setProperties(Set.of("code_", "name_", "gender_", "entry_date_"));
        /** 删除人员信息 */
        public static final ServiceSign delete = new ServiceSign("SvrEmployeeV3.delete", proxy).setVersion(5)
                .setProperties(Set.of("code_"));
        /** 获取员工信息 */
        public static final ServiceSign download = new ServiceSign("SvrEmployeeV3.download", proxy).setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改人员信息 */
        public static final ServiceSign modify = new ServiceSign("SvrEmployeeV3.modify", proxy).setVersion(3)
                .setProperties(Set.of("code_", "gender_", "entry_date_"));
        /** 根据条件查询人员信息 */
        public static final ServiceSign search = new ServiceSign("SvrEmployeeV3.search", proxy).setVersion(3);
    }

    /** 第二代服务编写方式(正式项目中逐渐淘汰) */
    public static class SvrEmployeeV2 {
        /** 新增人员信息 */
        public static final ServiceSign append = new ServiceSign("SvrEmployeeV2.append", proxy).setVersion(3)
                .setProperties(Set.of("code_", "name_", "gender_", "entry_date_"));
        /** 删除人员信息 */
        public static final ServiceSign delete = new ServiceSign("SvrEmployeeV2.delete", proxy).setVersion(5)
                .setProperties(Set.of("code_"));
        /** 获取员工信息 */
        public static final ServiceSign download = new ServiceSign("SvrEmployeeV2.download", proxy).setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改人员信息 */
        public static final ServiceSign modify = new ServiceSign("SvrEmployeeV2.modify", proxy).setVersion(3)
                .setProperties(Set.of("code_", "gender_", "entry_date_"));
        /** 根据条件查询人员信息 */
        public static final ServiceSign search = new ServiceSign("SvrEmployeeV2.search", proxy).setVersion(3);
    }

}
