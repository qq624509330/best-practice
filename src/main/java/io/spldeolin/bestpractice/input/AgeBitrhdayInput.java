package io.spldeolin.bestpractice.input;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AgeBitrhdayInput {

    private Integer age;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone =
    // "GMT+8")
    private Date birthday;

}
