package com.yadeah.minichat.mapper;

import com.yadeah.minichat.common.model.AccountModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {

    int insert(@Param("account") AccountModel account);

    /***
     * 根据账户id/账号/手机号/邮箱获取账户信息
     * note：入参只有一个不为空
     * @param accountId 账户id
     * @param account 账号
     * @param mobile 手机号
     * @param email 邮箱
     * @return AccountModel
     */
    AccountModel selectAccountModelByAccountIdOrAccountOrMobileOrEmail(@Param("accountId") String accountId,
                                                                       @Param("account") String account,
                                                                       @Param("mobile") String mobile,
                                                                       @Param("email") String email);

    /***
     * 根据账号/手机号/邮箱和密码获取账户信息
     * @param account 账号/邮箱/手机号
     * @param password 密码
     * @return AccountModel
     */
    AccountModel selectAccountModelByPassword(@Param("account") String account,
                                              @Param("password") String password);

    /***
     * 根据账号/手机号/邮箱获取账户信息
     * @param account 账号/邮箱/手机号
     * @return AccountModelList
     */
    List<AccountModel> selectAccountModelsByAccountOrMobileOrEmail(@Param("account") String account);
}
