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
@EntityKey(fields = { "corpNo_", "userCode_", "tbNo_",
        "it_" }, cache = CacheLevelEnum.RedisAndSession, smallTable = true)
@Table(name = "s_tranb", indexes = { @Index(name = "PRIMARY", columnList = "UID_", unique = true) })
@SqlServer(type = SqlServerType.Mysql)
@Getter
@Setter
@Describe(name = "单身")
public class Tranb extends CustomEntity {

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

    @Column(length = 10, nullable = false)
    @Describe(name = "单号")
    private String tbNo_;

    @Column(length = 11, nullable = false)
    @Describe(name = "单序")
    private int it_;

    @Column(length = 32)
    @Describe(name = "商品编号")
    private String code_;

    @Column(precision = 18, scale = 4)
    @Describe(name = "商品数量")
    private Double num_;
    
    @Column(precision = 18, scale = 4)
    @Describe(name = "当前商品数量")
    private Double currentNum_;

    @Override
    public void onInsertPost(IHandle handle) {
        super.onInsertPost(handle);
        this.setCorpNo_(handle.getCorpNo());
        this.setUserCode_(handle.getUserCode());
    }

    @Override
    public void onUpdatePost(IHandle handle) {
        super.onUpdatePost(handle);
    }
}