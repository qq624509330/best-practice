package io.spldeolin.bestpractice.input;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InteractionInput {

    private String name;

    private Date date;

    @Max(value = 10L, message = "钱太多")
    private BigDecimal money;

    private Integer no;

    private Long serial;

}