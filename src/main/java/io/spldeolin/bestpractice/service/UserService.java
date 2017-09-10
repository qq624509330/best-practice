package io.spldeolin.bestpractice.service;

import java.util.List;
import io.spldeolin.bestpractice.po.UserPo;

public interface UserService {

    public void batchCreate() throws Exception;

    public List<UserPo> getBatch() throws Exception;

    /** 获取昵称与参数相同的所有用户 */
    public List<UserPo> getUserByNickname(String nickname) throws Exception;

    /** 获取密码与参数相同的所有用户 */
    public List<UserPo> getUserByPassword(String password) throws Exception;

    public List<UserPo> createUser(String nickname, String password) throws Exception;

}
