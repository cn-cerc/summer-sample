package cn.cerc.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

import cn.cerc.db.core.CacheLevelEnum;
import cn.cerc.db.core.Datetime;
import cn.cerc.db.core.Describe;
import cn.cerc.db.core.EntityKey;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlServer;
import cn.cerc.db.core.SqlServerType;
import cn.cerc.mis.ado.CustomEntity;
import cn.cerc.sample.core.AppDB;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@EntityKey(fields = { "corp_no_", "order_sn_" }, corpNo = true, cache = CacheLevelEnum.Disabled, smallTable = true)
@Table(name = AppDB.s_tranh, indexes = { @Index(name = "PRIMARY", columnList = "UID_", unique = true),
        @Index(name = "uk_corp_tb_order", columnList = "corp_no_,tb_,order_sn_", unique = true) })
@SqlServer(type = SqlServerType.Mysql)
@Getter
@Setter
@Describe(name = "订单单头")
public class TranHeadEntity extends CustomEntity {

    @Id
    @GeneratedValue
    @Column(length = 11, nullable = false)
    @Describe(name = "")
    private Integer uid_;

    @Column(length = 11, nullable = false)
    @Describe(name = "帐套")
    private String corp_no_;

    @Column(length = 10, nullable = false)
    @Describe(name = "单别")
    private String tb_;

    @Column(length = 10, nullable = false)
    @Describe(name = "订单单号")
    private String order_sn_;

    @Column(length = 10, nullable = false)
    @Describe(name = "订单日期")
    private Datetime order_date_;

    @Column(length = 128)
    @Describe(name = "备注")
    private String remark_;

    @Column(length = 10)
    @Describe(name = "数量汇总")
    private int total_;

    @Version
    @Column(length = 11, nullable = false)
    @Describe(name = "当前版本")
    private int version_;

    @Column(length = 10, nullable = false)
    @Describe(name = "创建人员")
    private String create_user_;

    @Column(columnDefinition = "datetime")
    @Describe(name = "创建时间")
    private Datetime create_time_;

    @Column(length = 10, nullable = false)
    @Describe(name = "更新人员")
    private String update_user_;

    @Column(columnDefinition = "datetime")
    @Describe(name = "更新时间")
    private Datetime update_time_;

    @Override
    public void onInsertPost(IHandle handle) {
        super.onInsertPost(handle);
        this.setCorp_no_(handle.getCorpNo());

        this.setCreate_user_(handle.getUserCode());
        this.setCreate_time_(new Datetime());

        this.setUpdate_user_(handle.getUserCode());
        this.setUpdate_time_(new Datetime());
    }

    @Override
    public void onUpdatePost(IHandle handle) {
        super.onUpdatePost(handle);

        this.setUpdate_user_(handle.getUserCode());
        this.setUpdate_time_(new Datetime());
    }

}