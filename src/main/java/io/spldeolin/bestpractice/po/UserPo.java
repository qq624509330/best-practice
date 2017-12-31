package io.spldeolin.bestpractice.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 持久层对象：用户
 *
 * @author Deolin
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "user")
public class UserPo implements Serializable {

    private static final long serialVersionUID = 7665253963703798685L;

    private Integer id;

    private Date insertTime;

    private Date updateTime;

    private Date removeTime;

    private String nickname;

    private String password;

}