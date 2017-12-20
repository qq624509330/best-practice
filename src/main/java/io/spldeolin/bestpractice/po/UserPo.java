package io.spldeolin.bestpractice.po;

import java.io.Serializable;
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
public class UserPo implements Serializable {

    private static final long serialVersionUID = 7665253963703798685L;

    private Integer id;

}
