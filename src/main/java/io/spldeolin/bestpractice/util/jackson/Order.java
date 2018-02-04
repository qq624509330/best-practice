package io.spldeolin.bestpractice.util.jackson;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {

    private Member member;

    private List<Dish> dish;

    private Date createAt;

    private BigDecimal totalPrices;

    private String note;

}
