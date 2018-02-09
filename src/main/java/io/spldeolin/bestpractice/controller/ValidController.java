package io.spldeolin.bestpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.spldeolin.bestpractice.valid.Choose;
import io.spldeolin.bestpractice.valid.Price;

@RestController
@RequestMapping("valid")
public class ValidController {

    @GetMapping("price")
    public String price(@RequestParam @Price(decimalLength = 3) String p) {
        return p;
    }

    @GetMapping("sex")
    public String sex(@RequestParam @Choose(value = {"Female", "Male"}) String s) {
        return s;
    }

}
