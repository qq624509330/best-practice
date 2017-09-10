package io.spldeolin.bestpractice.po;

import java.io.Serializable;

/**
 * 持久层对象：用户
 *
 * @author Deolin
 */
public class UserPo implements Serializable {

    private static final long serialVersionUID = 7665253963703798685L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[" + id + "]号数据";
    }

}
