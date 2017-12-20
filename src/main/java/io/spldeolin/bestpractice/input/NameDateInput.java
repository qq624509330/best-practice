package io.spldeolin.bestpractice.input;

import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 输入实体：名称与日期
 *
 * @author Deolin
 */
@Getter
@Setter
@ToString
public class NameDateInput {

    private String name;

    @Pattern(regexp = "1993*")
    private String date;

}