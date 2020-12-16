package com.cikp.mall.service;

import com.cikp.mall.common.api.CommonResult;

/**
 * @ClassName UmsMemberService
 * @Description 会员管理
 * @Author ccy
 * @Date 2020/12/16 10:40
 * @Version 1.0
 **/
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

}
