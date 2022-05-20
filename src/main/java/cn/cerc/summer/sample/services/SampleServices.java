package cn.cerc.summer.sample.services;

import java.util.Set;

import cn.cerc.mis.client.ServiceSign;

public class SampleServices {
    public static class SvrUiExample {
        /** 新增人员信息 */
        public static final ServiceSign append = new ServiceSign("SvrUiExample.append").setVersion(5)
                .setProperties(Set.of("code_", "name_", "sex_", "age_"));
        /** 删除人员信息 */
        public static final ServiceSign delete = new ServiceSign("SvrUiExample.delete").setVersion(5)
                .setProperties(Set.of("code_"));
        /** 获取人员信息 */
        public static final ServiceSign download = new ServiceSign("SvrUiExample.download").setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改人员信息 */
        public static final ServiceSign modify = new ServiceSign("SvrUiExample.modify").setVersion(5)
                .setProperties(Set.of("code_", "sex_"));
        /** 根据条件查询人员信息 */
        public static final ServiceSign search = new ServiceSign("SvrUiExample.search").setVersion(3);
    }

    public static class SvrExample {
        /** 新增人员信息 */
        public static final ServiceSign append = new ServiceSign("SvrExample.append").setVersion(5)
                .setProperties(Set.of("code_", "name_", "sex_", "age_"));
        /** 删除人员信息 */
        public static final ServiceSign delete = new ServiceSign("SvrExample.delete").setVersion(5)
                .setProperties(Set.of("code_"));
        /** 获取人员信息 */
        public static final ServiceSign download = new ServiceSign("SvrExample.download").setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改人员信息 */
        public static final ServiceSign modify = new ServiceSign("SvrExample.modify").setVersion(5)
                .setProperties(Set.of("code_", "sex_"));
        /** 根据条件查询人员信息 */
        public static final ServiceSign search = new ServiceSign("SvrExample.search").setVersion(3);
    }

    public static class SvrUiExampleMysql {
        public static final ServiceSign append = new ServiceSign("SvrUiExampleMysql.append").setVersion(5)
                .setProperties(Set.of("code_", "name_", "sex_"));
        public static final ServiceSign delete = new ServiceSign("SvrUiExampleMysql.delete").setVersion(5)
                .setProperties(Set.of("code_"));
        public static final ServiceSign download = new ServiceSign("SvrUiExampleMysql.download").setVersion(3)
                .setProperties(Set.of("code_"));
        public static final ServiceSign modify = new ServiceSign("SvrUiExampleMysql.modify").setVersion(5)
                .setProperties(Set.of("code_", "name_", "sex_"));
        public static final ServiceSign search = new ServiceSign("SvrUiExampleMysql.search").setVersion(3);
    }

    public static class SvrGoods {
        /** 新增商品数据 */
        public static final ServiceSign append = new ServiceSign("SvrGoods.append").setVersion(5)
                .setProperties(Set.of("code_", "desc_", "spec_", "unit_"));
        /** 删除商品数据 */
        public static final ServiceSign delete = new ServiceSign("SvrGoods.delete").setVersion(5)
                .setProperties(Set.of("code_"));
        /** 根据code查询商品数据 */
        public static final ServiceSign download = new ServiceSign("SvrGoods.download").setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改商品数据 */
        public static final ServiceSign modify = new ServiceSign("SvrGoods.modify").setVersion(5)
                .setProperties(Set.of("code_", "desc_", "spec_", "unit_"));
        /** 更新商品库存 */
        public static final ServiceSign modifyStock = new ServiceSign("SvrGoods.modifyStock").setVersion(5)
                .setProperties(Set.of("code_", "stock_"));
        /** 根据条件查询商品数据 */
        public static final ServiceSign search = new ServiceSign("SvrGoods.search").setVersion(3);
    }

    public static class SvrBills {
        /** 新增单据数据 */
        public static final ServiceSign append = new ServiceSign("SvrBills.append").setVersion(5)
                .setProperties(Set.of("tb_", "tbNo_", "tbDate_"));
        /** 新增单据数据 */
        public static final ServiceSign appendBody = new ServiceSign("SvrBills.appendBody").setVersion(5)
                .setProperties(Set.of("tbNo_", "code_", "num_", "currentNum_"));
        /** 删除单头单体数据 */
        public static final ServiceSign delete = new ServiceSign("SvrBills.delete").setVersion(5)
                .setProperties(Set.of("tbNo_", "it_"));
        /** 删除单体数据 */
        public static final ServiceSign deleteBody = new ServiceSign("SvrBills.deleteBody").setVersion(5)
                .setProperties(Set.of("tbNo_", "it_"));
        /** 根据tbNo_查询单头数据 */
        public static final ServiceSign downloadHead = new ServiceSign("SvrBills.downloadHead").setVersion(3)
                .setProperties(Set.of("tbNo_"));
        /** 修改单体数据 */
        public static final ServiceSign modifyBody = new ServiceSign("SvrBills.modifyBody").setVersion(5)
                .setProperties(Set.of("tbNo_", "num_", "it_"));
        /** 修改单头数据 */
        public static final ServiceSign modifyHead = new ServiceSign("SvrBills.modifyHead").setVersion(5)
                .setProperties(Set.of("tbNo_", "tbDate_"));
        /** 根据条件查询单头数据 */
        public static final ServiceSign search = new ServiceSign("SvrBills.search").setVersion(3);
        /** 根据条件查询单体数据 */
        public static final ServiceSign searchBody = new ServiceSign("SvrBills.searchBody").setVersion(3)
                .setProperties(Set.of("tbNo_"));
    }

    public static class SvrStatistics {
        /** 统计报表 */
        public static final ServiceSign statistics = new ServiceSign("SvrStatistics.statistics").setVersion(3);
        /** 统计明细 */
        public static final ServiceSign statisticsDetail = new ServiceSign("SvrStatistics.statisticsDetail")
                .setVersion(3).setProperties(Set.of("tbDate_"));
    }

}
