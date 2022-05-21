package cn.cerc.sample;

import java.util.Set;

import cn.cerc.mis.client.ServiceSign;
import cn.cerc.sample.services.proxy.UserCenterProxy;

public class SampleServices {

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

    public static class SvrPartInfo {
        /** 新增商品信息 */
        public static final ServiceSign append = new ServiceSign("SvrPartInfo.append").setVersion(3)
                .setProperties(Set.of("code_", "desc_", "spec_", "unit_"));
        /** 删除商品信息 */
        public static final ServiceSign delete = new ServiceSign("SvrPartInfo.delete").setVersion(3)
                .setProperties(Set.of("code_"));
        /** 获取商品信息 */
        public static final ServiceSign download = new ServiceSign("SvrPartInfo.download").setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改商品信息 */
        public static final ServiceSign modify = new ServiceSign("SvrPartInfo.modify").setVersion(3)
                .setProperties(Set.of("code_", "desc_", "spec_", "unit_"));
        /** 商品查询服务 */
        public static final ServiceSign search = new ServiceSign("SvrPartInfo.search").setVersion(3);
    }

    public static class SvrTranOrder {
        /** 新增商品信息 */
        public static final ServiceSign appendHead = new ServiceSign("SvrTranOrder.appendHead").setVersion(3)
                .setProperties(Set.of("tb_"));
        /** 删除订单信息 */
        public static final ServiceSign deleteHead = new ServiceSign("SvrTranOrder.deleteHead").setVersion(3)
                .setProperties(Set.of("order_sn_"));
        /** 获取订单信息 */
        public static final ServiceSign download = new ServiceSign("SvrTranOrder.download").setVersion(3)
                .setProperties(Set.of("code_"));
        /** 修改单头信息 */
        public static final ServiceSign modifyHead = new ServiceSign("SvrTranOrder.modifyHead").setVersion(3)
                .setProperties(Set.of("order_sn_", "order_date_"));
        /** 订单查询服务 */
        public static final ServiceSign search = new ServiceSign("SvrTranOrder.search").setVersion(3);

        /** 添加单身商品 */
        public static final ServiceSign appendBody = new ServiceSign("SvrTranOrder.appendBody").setVersion(3)
                .setProperties(Set.of("order_sn_", "code_"));
    }

}
