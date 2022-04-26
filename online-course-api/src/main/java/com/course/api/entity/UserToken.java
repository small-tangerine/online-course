package com.course.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * token记录
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_token")
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 用户ID
     */
    @TableField("username")
    private String username;

    /**
     * 类型 1用户端 2管理端
     */
    @TableField("type")
    private Integer type;

    /**
     * token
     */
    @TableField("token")
    private String token;

    /**
     * 过期时间
     */
    @TableField("expired_at")
    private LocalDateTime expiredAt;

    /**
     * 登录时间
     */
    @TableField("login_at")
    private LocalDateTime loginAt;


}
