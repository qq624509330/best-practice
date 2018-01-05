package io.spldeolin.bestpractice.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.spldeolin.bestpractice.dao.TimeMapper;
import io.spldeolin.bestpractice.po.TimePo;
import io.spldeolin.bestpractice.service.TimeService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TimeServiceImpl implements TimeService {

    @Autowired
    private TimeMapper timeMapper;

    @Override
    public Boolean isLocalDateTimeLeapYear() {
        TimePo po = timeMapper.get(1);
        log.info("来自DB的三种时间字段绑定到了TimePo对象。" + po);
        LocalDateTime ldt = po.getDatetime_field();
        boolean result = ldt.toLocalDate().isLeapYear();
        return result;
    }

    @Override
    public LocalDateTime getLocalDateTimeLeapYear() {
        return LocalDateTime.now();
    }

}
