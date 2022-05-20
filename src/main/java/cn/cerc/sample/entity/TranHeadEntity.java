package cn.cerc.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import cn.cerc.db.core.CacheLevelEnum;
import cn.cerc.db.core.Datetime;
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
@EntityKey(fields = { "corpNo_", "createUser_",
        "tbNo_" }, corpNo = true, cache = CacheLevelEnum.Disabled, smallTable = true)
@Table(name = "s_tranh", indexes = { @Index(name = "PRIMARY", columnList = "UID_", unique = true),
        @Index(name = "uk_corpNo_tb_tbNo_", columnList = "corpNo_,tb_,tbNo_", unique = true) })
@SqlServer(type = SqlServerType.Mysql)
@Getter
@Setter
@Describe(name = "单头")
public class TranHeadEntity extends CustomEntity {

    @Id
    @GeneratedValue
    @Column(length = 11, nullable = false)
    @Describe(name = "")
    private Integer UID_;

    @Column(length = 11, nullable = false)
    @Describe(name = "帐套")
    private String corpNo_;

    @Column(length = 10, nullable = false)
    @Describe(name = "单别")
    private String tb_;

    @Column(length = 10, nullable = false)
    @Describe(name = "单号")
    private String tbNo_;

    @Column(length = 10, nullable = false)
    @Describe(name = "日期")
    private Datetime tbDate_;

    @Column(length = 128)
    @Describe(name = "备注")
    private String remark_;

    @Column(length = 10)
    @Describe(name = "数量")
    private int total_;

    @Column(length = 10, nullable = false)
    @Describe(name = "建档人员")
    private String createUser_;

    @Column(nullable = false, columnDefinition = "datetime")
    @Describe(name = "创建时间")
    private Datetime createDate_;

    @Override
    public void onInsertPost(IHandle handle) {
        super.onInsertPost(handle);
        this.setCreateDate_(new Datetime());
        this.setCorpNo_(handle.getCorpNo());
        this.setCreateUser_(handle.getUserCode());
    }

    @Override
    public void onUpdatePost(IHandle handle) {
        super.onUpdatePost(handle);
    }
}