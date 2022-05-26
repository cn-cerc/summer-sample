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
import cn.cerc.sample.config.AppDB;

@Component
@Entity
@EntityKey(fields = { "corp_no_", "order_sn_", "it_" }, corpNo = true, cache = CacheLevelEnum.Redis, smallTable = true)
@Table(name = AppDB.s_tranb, indexes = { @Index(name = "PRIMARY", columnList = "UID_", unique = true),
        @Index(name = "uk_corp_order_it", columnList = "corp_no_,order_sn_,it_", unique = true) })
@SqlServer(type = SqlServerType.Mysql)
@Describe(name = "订单单身")
public class TranBodyEntity extends CustomEntity {

    @Id
    @GeneratedValue
    @Column(length = 11, nullable = false)
    @Describe(name = "")
    private Integer uid_;

    @Column(length = 11, nullable = false)
    @Describe(name = "帐套")
    private String corp_no_;

    @Column(length = 10, nullable = false)
    @Describe(name = "单号")
    private String order_sn_;

    @Column(length = 11, nullable = false)
    @Describe(name = "单序")
    private Integer it_;

    @Column(length = 32)
    @Describe(name = "商品编号")
    private String code_;

    @Column(precision = 18, scale = 4)
    @Describe(name = "商品数量")
    private Double num_;

    @Column(precision = 18, scale = 4)
    @Describe(name = "变化增量")
    private Double increment_;

    @Version
    @Column(length = 11, nullable = false)
    @Describe(name = "当前版本")
    private Integer version_;

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

    public Integer getUid_() {
        return uid_;
    }

    public void setUid_(Integer uid_) {
        this.uid_ = uid_;
    }

    public String getCorp_no_() {
        return corp_no_;
    }

    public void setCorp_no_(String corp_no_) {
        this.corp_no_ = corp_no_;
    }

    public String getOrder_sn_() {
        return order_sn_;
    }

    public void setOrder_sn_(String order_sn_) {
        this.order_sn_ = order_sn_;
    }

    public Integer getIt_() {
        return it_;
    }

    public void setIt_(Integer it_) {
        this.it_ = it_;
    }

    public String getCode_() {
        return code_;
    }

    public void setCode_(String code_) {
        this.code_ = code_;
    }

    public Double getNum_() {
        return num_;
    }

    public void setNum_(Double num_) {
        this.num_ = num_;
    }

    public Double getIncrement_() {
        return increment_;
    }

    public void setIncrement_(Double increment_) {
        this.increment_ = increment_;
    }

    public Integer getVersion_() {
        return version_;
    }

    public void setVersion_(Integer version_) {
        this.version_ = version_;
    }

    public String getCreate_user_() {
        return create_user_;
    }

    public void setCreate_user_(String create_user_) {
        this.create_user_ = create_user_;
    }

    public Datetime getCreate_time_() {
        return create_time_;
    }

    public void setCreate_time_(Datetime create_time_) {
        this.create_time_ = create_time_;
    }

    public String getUpdate_user_() {
        return update_user_;
    }

    public void setUpdate_user_(String update_user_) {
        this.update_user_ = update_user_;
    }

    public Datetime getUpdate_time_() {
        return update_time_;
    }

    public void setUpdate_time_(Datetime update_time_) {
        this.update_time_ = update_time_;
    }

}