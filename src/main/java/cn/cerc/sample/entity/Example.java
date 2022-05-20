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
@EntityKey(fields = { "corpNo_", "userCode_",
        "code_" }, corpNo = true, cache = CacheLevelEnum.Disabled, smallTable = true)
@Table(name = "s_example", indexes = { @Index(name = "PRIMARY", columnList = "UID_", unique = true),
        @Index(name = "uk_code_", columnList = "code_", unique = true) })
@SqlServer(type = SqlServerType.Mysql)
@Getter
@Setter
@Describe(name = "演示范例表")
public class Example extends CustomEntity {

    @Id
    @GeneratedValue
    @Column(length = 11, nullable = false)
    @Describe(name = "")
    private Integer UID_;

    @Column(length = 11)
    @Describe(name = "帐套")
    private String corpNo_;

    @Column(length = 11)
    @Describe(name = "账号")
    private String userCode_;

    @Column(length = 16, nullable = false)
    @Describe(name = "学号")
    private String code_;

    @Column(length = 16, nullable = false)
    @Describe(name = "姓名")
    private String name_;

    @Column(length = 1)
    @Describe(name = "性别")
    private Integer sex_;

    @Column(length = 3)
    @Describe(name = "年龄")
    private Integer age_;

    @Column(nullable = false, columnDefinition = "datetime")
    @Describe(name = "创建时间")
    private Datetime createTime_;

    @Column(nullable = false, columnDefinition = "datetime")
    @Describe(name = "更新时间")
    private Datetime updateTime_;

    @Override
    public void onInsertPost(IHandle handle) {
        super.onInsertPost(handle);
        this.setCreateTime_(new Datetime());
        this.setUpdateTime_(new Datetime());
        this.setCorpNo_(handle.getCorpNo());
        this.setUserCode_(handle.getUserCode());
    }

    @Override
    public void onUpdatePost(IHandle handle) {
        super.onUpdatePost(handle);
        this.setUpdateTime_(new Datetime());
        this.setUserCode_(handle.getUserCode());
        this.setCorpNo_(handle.getCorpNo());
    }
}