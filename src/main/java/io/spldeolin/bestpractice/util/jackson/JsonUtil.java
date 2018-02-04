package io.spldeolin.bestpractice.util.jackson;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonUtil {

    private static ObjectMapper om;

    static {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public static void main(String[] args) throws Exception {
        Dish dish1 = new Dish();
        dish1.setDishId(1L);
        dish1.setDishName("深邃黑暗料理");
        Dish dish2 = new Dish();
        dish2.setDishId(2L);
        dish2.setDishName("光明料理");
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        Member member = new Member();
        member.setMemberId(2L);
        member.setMemberName("Van様");
        Order order = new Order();
        order.setDish(dishes);
        order.setMember(member);
        order.setCreateAt(new Date());
        order.setTotalPrices(new BigDecimal(23333.333));
        System.out.println(om.writeValueAsString(order));

        String json =
                "{\"UNKNOW\":\"who am i?\",\"member\":{\"member_id\":2,\"member_name\":\"Van様\"},\"dish\":[{\"dish_id\":1,\"dish_name\":\"深邃黑暗料理\"},{\"dish_id\":2,\"dish_name\":\"光明料理\"}],\"create_at\":\"2018-02-04 19:22\",\"total_prices\":23333.332999999998719431459903717041015625,\"note\":null}";
        JsonNode jn = om.readTree(json);
        System.out.println(jn.get("total_prices"));

        System.out.println(om.readValue(json, Order.class));
    }

}

