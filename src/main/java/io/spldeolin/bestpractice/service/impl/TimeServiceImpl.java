package io.spldeolin.bestpractice.service.impl;

import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.spldeolin.bestpractice.mapper.TimeMapper;
import io.spldeolin.bestpractice.po.TimePo;
import io.spldeolin.bestpractice.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private TimeMapper timeMapper;

    @Override
    public Boolean isLocalDateTimeLeapYear() {
        TimePo po = timeMapper.get(1);
        LOG.info("来自DB的三种时间字段绑定到了TimePo对象。" + po);
        LocalDateTime ldt = po.getDatetime_field();
        boolean result = ldt.toLocalDate().isLeapYear();
        return result;
    }

    @Override
    public LocalDateTime getLocalDateTimeLeapYear() {
        return LocalDateTime.now();
    }

}
