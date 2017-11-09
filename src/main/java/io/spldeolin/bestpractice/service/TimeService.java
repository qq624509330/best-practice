package io.spldeolin.bestpractice.service;

import java.time.LocalDateTime;

public interface TimeService {

    /**
     * 判断一个LocalDateTime对象代表的年月日是否是闰年，
     * 每个实现方法会通过各自的途径获得LocalDateTime对象
     *
     * @return true：是闰年 false：不是闰年
     */
    Boolean isLocalDateTimeLeapYear();

    /**
     * 返回一个LocalDateTime对象，
     * 每个实现方法会通过各自的途径获得LocalDateTime对象
     *
     * @return LocalDateTime对象
     */
    LocalDateTime getLocalDateTimeLeapYear();

}
