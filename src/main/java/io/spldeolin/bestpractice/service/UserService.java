package io.spldeolin.bestpractice.service;

import java.util.List;

import io.spldeolin.bestpractice.po.User;

public interface UserService {

    void batchCreate() throws Exception;

    List<User> getBatch() throws Exception;

}
