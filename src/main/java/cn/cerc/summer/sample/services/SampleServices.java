package cn.cerc.summer.sample.services;

import java.util.Set;

import cn.cerc.mis.client.ServiceSign;

public class SampleServices {
    public static class SvrExample {
        /** 新增人员信息 */
        public static final ServiceSign append = new ServiceSign("SvrExample.append")
                .setProperties(Set.of("code_", "name_", "sex_", "age_"));
        /** 删除人员信息 */
        public static final ServiceSign delete = new ServiceSign("SvrExample.delete").setProperties(Set.of("code_"));
        /** 获取人员信息 */
        public static final ServiceSign download = new ServiceSign("SvrExample.download")
                .setProperties(Set.of("code_"));
        /** 修改人员信息 */
        public static final ServiceSign modify = new ServiceSign("SvrExample.modify")
                .setProperties(Set.of("code_", "sex_"));
        /** 根据条件查询人员信息 */
        public static final ServiceSign search = new ServiceSign("SvrExample.search");
    }
}
