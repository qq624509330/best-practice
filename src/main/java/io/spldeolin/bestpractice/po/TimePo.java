package io.spldeolin.bestpractice.po;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TimePo {

    private Integer id;

    private LocalDate date_field;

    private LocalTime time_field;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime_field;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate_field() {
        return date_field;
    }

    public void setDate_field(LocalDate date_field) {
        this.date_field = date_field;
    }

    public LocalTime getTime_field() {
        return time_field;
    }

    public void setTime_field(LocalTime time_field) {
        this.time_field = time_field;
    }

    public LocalDateTime getDatetime_field() {
        return datetime_field;
    }

    public void setDatetime_field(LocalDateTime datetime_field) {
        this.datetime_field = datetime_field;
    }

    @Override
    public String toString() {
        return "TimePo [id=" + id + ", date_field=" + date_field + ", time_field=" + time_field + ", datetime_field="
                + datetime_field + "]";
    }

}
