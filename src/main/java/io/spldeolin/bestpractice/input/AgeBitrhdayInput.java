package io.spldeolin.bestpractice.input;

import java.util.Date;

public class AgeBitrhdayInput {

    private Integer age;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date birthday;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "AgeBitrhdayInput [age=" + age + ", birthday=" + birthday + "]";
    }

}
