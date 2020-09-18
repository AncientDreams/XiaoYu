package com.xiaoyu.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode()
@TableName("system_user")
public class SystemUser {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 加密密码
     */
    @TableField("password")
    private String password;

    /**
     * 密码盐，32位随机数，修改密码时重置
     */
    @TableField("salt")
    private String salt;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 邮件
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 0-正常
     */
    @TableField("status")
    private String status;

    /**
     * 逻辑删除标记
     */
    @TableField("flag")
    private Integer flag;


}
