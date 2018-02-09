package io.spldeolin.bestpractice.controller;

import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.spldeolin.bestpractice.valid.Choose;
import io.spldeolin.bestpractice.valid.Index;
import io.spldeolin.bestpractice.valid.Price;

@RestController
@RequestMapping("valid")
public class ValidController {

    @GetMapping("price")
    public String price(@RequestParam @Price(decimalLength = 3) String p) {
        return p;
    }

    @GetMapping("sex")
    public String sex(@RequestParam @NotNull @Choose(value = {"Female", "Male"}) String s) {
        return s;
    }

    @GetMapping(value = "user_kind", produces = "text/html;charset=utf-8")
    public Integer userKind(@RequestParam @NotNull @Index(value = {1, 2, 3, 4, 5}) Integer uk) {
        return uk;
    }

}
