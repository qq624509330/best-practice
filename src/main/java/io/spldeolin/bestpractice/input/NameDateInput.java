package io.spldeolin.bestpractice.input;

import javax.validation.constraints.Pattern;

/**
 * 输入实体：名称与日期
 *
 * @author Deolin
 */
public class NameDateInput {

    private String name;

    @Pattern(regexp = "1993*")
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NameDateInput [name=" + name + ", date=" + date + "]";
    }

}
