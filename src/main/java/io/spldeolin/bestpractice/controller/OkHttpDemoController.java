package io.spldeolin.bestpractice.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.spldeolin.bestpractice.entity.RequestResult;
import io.spldeolin.bestpractice.input.IdNameInput;
import lombok.extern.log4j.Log4j2;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("okhttp")
@Log4j2
public class OkHttpDemoController {

    /**
     * 被OKHTTP框架请求的目标接口
     */
    @GetMapping("get_me/{id}")
    public RequestResult getMe(@PathVariable("id") Long id, @RequestParam("p") Integer page) {
        return RequestResult.success("YOU GOT ME. " + id + " and " + page);
    }

    /**
     * 试验：OKHTTP发送GET请求
     */
    @GetMapping("to_get")
    public RequestResult toGet() {
        try {
            Long id = 11L;
            Integer page = 2;

            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder().url("http://localhost:8080/okhttp/get_me/" + id + "?p=" + page).build();
            Response response = client.newCall(request).execute();
            String resp = response.body().string();

            return RequestResult.success(resp);
        } catch (IOException e) {
            return RequestResult.failure(e.getMessage());
        }
    }

    /**
     * 被OKHTTP框架请求的目标接口
     */
    @PostMapping("post_me")
    public RequestResult postMe(@RequestBody IdNameInput input) {
        return RequestResult.success("YOU POSTED ME. " + input);
    }

    /**
     * 试验：OKHTTP发送POST请求
     */
    @GetMapping("to_post")
    public RequestResult toPost() {
        try {
            IdNameInput input = new IdNameInput(11L, "汉字姓名");

            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(input);

            OkHttpClient client = new OkHttpClient();
            okhttp3.RequestBody body =
                    okhttp3.RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder().url("http://localhost:8080/okhttp/post_me/").post(body).build();
            Response response = client.newCall(request).execute();
            String resp = response.body().string();

            return RequestResult.success(resp);
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }

    /**
     * 被OKHTTP框架请求的目标接口
     */
    @PostMapping("form_post_me")
    public RequestResult formPostMe(IdNameInput input) {
        return RequestResult.success("YOU POSTED ME. " + input);
    }

    /**
     * 试验：OKHTTP发送POST请求
     */
    @GetMapping("to_post_form")
    public RequestResult toPostForm() {
        try {
            Long id = 11L;
            String name = "简体姓名";

            OkHttpClient client = new OkHttpClient();
            okhttp3.RequestBody body = new FormBody.Builder().add("id", id.toString()).add("name", name).build();
            Request request = new Request.Builder().url("http://localhost:8080/okhttp/form_post_me/").post(body).build();
            Response response = client.newCall(request).execute();
            String resp = response.body().string();

            return RequestResult.success(resp);
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }

}
