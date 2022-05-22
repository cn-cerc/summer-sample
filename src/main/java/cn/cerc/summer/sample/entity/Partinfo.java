package cn.cerc.summer.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import cn.cerc.db.core.CacheLevelEnum;
import cn.cerc.db.core.Describe;
import cn.cerc.db.core.EntityKey;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.SqlServer;
import cn.cerc.db.core.SqlServerType;
import cn.cerc.mis.ado.CustomEntity;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@EntityKey(fields = { "corpNo_", "userCode_",
        "code_" }, corpNo = true, cache = CacheLevelEnum.Disabled, smallTable = true)
@Table(name = "s_partinfo", indexes = { @Index(name = "PRIMARY", columnList = "UID_", unique = true),
        @Index(name = "uk_corpNo_code", columnList = "corpNo_,code_", unique = true) })
@SqlServer(type = SqlServerType.Mysql)
@Getter
@Setter
@Describe(name = "商品表")
public class Partinfo extends CustomEntity {

    @Id
    @GeneratedValue
    @Column(length = 11, nullable = false)
    @Describe(name = "")
    private Integer UID_;

    @Column(length = 11, nullable = false)
    @Describe(name = "帐套")
    private String corpNo_;

    @Column(length = 11)
    @Describe(name = "账号")
    private String userCode_;

    @Column(length = 30, nullable = false)
    @Describe(name = "编号")
    private String code_;

    @Column(length = 32, nullable = false)
    @Describe(name = "名称")
    private String desc_;

    @Column(length = 64, nullable = false)
    @Describe(name = "规格")
    private String spec_;

    @Column(length = 10, nullable = false)
    @Describe(name = "单位")
    private String unit_;

    @Column(precision = 18, scale = 4)
    @Describe(name = "库存")
    private Double stock_;

    @Column(length = 128)
    @Describe(name = "备注")
    private String remark_;

    @Override
    public void onInsertPost(IHandle handle) {
        super.onInsertPost(handle);
        this.stock_ = 0d;
        this.setCorpNo_(handle.getCorpNo());
        this.setUserCode_(handle.getUserCode());
    }

    @Override
    public void onUpdatePost(IHandle handle) {
        super.onUpdatePost(handle);
    }
}