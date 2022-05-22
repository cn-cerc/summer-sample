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
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@EntityKey(fields = {"corp_no_", "code_"}, corpNo = true, cache = CacheLevelEnum.Redis, smallTable = true)
@Table(name = AppDB.s_partinfo, indexes = {@Index(name = "PRIMARY", columnList = "UID_", unique = true),
        @Index(name = "uk_corp_code", columnList = "corp_no_,code_", unique = true)})
@SqlServer(type = SqlServerType.Mysql)
@Getter
@Setter
@Describe(name = "商品基本资料")
public class PartinfoEntity extends CustomEntity {

    @Id
    @GeneratedValue
    @Column(length = 11, nullable = false)
    @Describe(name = "")
    private Integer uid_;

    @Column(length = 11, nullable = false)
    @Describe(name = "帐套")
    private String corp_no_;

    @Column(length = 30, nullable = false)
    @Describe(name = "料号")
    private String code_;

    @Column(length = 32, nullable = false)
    @Describe(name = "品名")
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
        this.setStock_(0d);

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

    /**
     * 新增商品库存
     */
    public void appendStock(String tb, double num) {
        double newStock;
        switch (tb) {
            case "AB":
                newStock = this.stock_ + num;
                break;
            case "BC":
                newStock = this.stock_ - num;
                break;
            case "AE":
                newStock = num;
                break;
            default:
                throw new RuntimeException(String.format("不支持的的单别 %s", tb));
        }
        if (newStock < 0)
            throw new RuntimeException("商品库存数量不允许为负数");
        this.setStock_(newStock);
    }

    /**
     * 回收商品库存
     */
    public void recycleStock(String tb, double original, double increment) {
        double newStock;
        switch (tb) {
            case "AB":
                newStock = this.stock_ - original;// AB 退货扣减库存
                break;
            case "AE":
                newStock = original - increment;// AE 退还原始库存
                break;
            case "BC":
                newStock = this.stock_ + original;// BC 退货返还库存
                break;
            default:
                throw new RuntimeException(String.format("不支持的的单别 %s", tb));
        }
        if (newStock < 0)
            throw new RuntimeException("商品库存数量不允许为负数");
        this.setStock_(newStock);
    }

    /**
     * 增量调整商品库存
     */
    public void updateStock(String tb, double diff) {
        double newStock;
        switch (tb) {
            case "AB":
            case "AE":
                newStock = this.stock_ + diff;
                break;
            case "BC":
                newStock = this.stock_ - diff;
                break;
            default:
                throw new RuntimeException(String.format("不支持的的单别 %s", tb));
        }
        if (newStock < 0)
            throw new RuntimeException("商品库存数量不允许为负数");
        this.setStock_(newStock);
    }


}