package io.spldeolin.bestpractice.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通用字段 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 通用字段 插入时间
     */
    @Column(name = "inserted_at")
    private Date insertedAt;

    /**
     * 通用字段 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 通用字段 删除时间
     */
    @Column(name = "deleted_at")
    private Date deletedAt;

    /**
     * “用户名” 字符串的情况
     */
    private String username;

    /**
     * “密码”
     */
    private String password;

    /**
     * “TOKEN” 定长字符串的情况
     */
    private String token;

    /**
     * “性别（m男 f女 n中 a无）” 枚举类型的情况
     */
    private String sex;

    /**
     * “年龄” 数字的情况
     */
    private Integer age;

    /**
     * “能否登录” 逻辑型的情况
     */
    @Column(name = "login_enable")
    private Boolean loginEnable;

    /**
     * “生日” 日期的情况
     */
    private Date birthday;

    /**
     * “起床时间” 时分秒时间的情况
     */
    @Column(name = "wake_up_time")
    private Date wakeUpTime;

    /**
     * “获奖时刻” 年月日时分秒时间类型的情况
     */
    @Column(name = "win_at")
    private Date winAt;

    /**
     * “每日开销” 金额的情况
     */
    @Column(name = "daily_spending")
    private BigDecimal dailySpending;

    /**
     * “工号” 长数字的情况
     */
    @Column(name = "job_no")
    private Long jobNo;

    /**
     * “暴头率” 小数的情况
     */
    @Column(name = "one_shoot_rate")
    private Float oneShootRate;

    /**
     * “死亡率” 小数的情况
     */
    @Column(name = "dead_rate")
    private Double deadRate;

    /**
     * “自我介绍” 文本的情况
     */
    private String introduce;

    /**
     * “头像” 图片的情况
     */
    private byte[] head;

}
