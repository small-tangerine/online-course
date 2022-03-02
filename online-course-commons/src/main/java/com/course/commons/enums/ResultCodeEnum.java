package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Gm
 * @date 2020-05-09
 * @Description: 返回错误码
 */
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * 返回结果枚举
     */
    SUCCESS(0, "成功"),
    ERROR(405, "未知异常"),
    EXCEPTION(500, "请求异常"),
    CHECK_ERROR(501, "检查参数错误"),
    USER_ERROR(502, "用户权限错误"),
    HTTP_BAD_METHOD(405, "请求方式不支持"),
    WX_ERROR(506, "调用微信接口异常"),
    TENCENT_ERROR(507, "调用腾讯云接口异常"),
    REDIS_KEY_TIMEOUT(508, "获取redis锁超时"),
    NEED_RESET_PASSWORD(601, "需要重置密码"),
    NEED_LOGIN(40001, "需要登录"),
    NO_AUTHORITY(508, "没有权限"),
    NO_VERIFY_VIEW(50001, "没有认证详情"),
    USERNAME_OR_PASSWORD_ERROR(40003, "用户名或密码错误"),
    TOKEN_EXPIRED(40004, "token已失效"),
    VERIFY_CODE_ERROR(40005, "验证码错误"),
    LOCKED(40006, "账号已被锁定"),
    USER_NOT_EXIST(45006, "用户不存在"),
    USER_PHOTO_NOT_EXIST(45007, "用户没有身份校验图片"),

    SAVE_SUCCESS(0,"保存成功"),
    LIKE_SUCCESS(0,"点赞成功"),
    FAVOR_SUCCESS(0,"收藏成功"),
    CANCEL_SUCCESS(0,"取消成功"),
    UNSUBSCRIBE_SUCCESS(0,"取关成功"),
    FOLLOW_SUCCESS(0,"关注成功"),
    ACTIVATION_SUCCESS(0,"激活成功"),
    EMAIL_SEND_FAILURE(9000,"邮件发送失败"),
    EMAIL_SEND_SUCCESS(0,"邮件发送成功"),
    REGISTER_SUCCESS(0,"注册成功"),
    REGISTER_FAILURE(8010,"注册失败请稍后重试!"),
    ARTICLE_NOT_EXIST(8005,"文章不存在"),
    RESOURCE_NOT_EXIST(8006,"资源不存在"),
    RESET_PASSWORD_SUCCESS(0,"密码重置成功"),
    UPLOAD_SUCCESS(0,"上传成功"),
    QUERY_SUCCESS(0, "查询成功"),
    QUERY_NOT_FOUND(2002, "查无此题"),
    QUERY_ERROR(6005, "查询失败"),
    NOT_ENOUGH_MONEY(2001, "余额不足，请及时充值"),
    SYNC_SUCCESS(0,"同步成功"),
    SYNC_FAILURE(8009,"同步失败"),
    OPTIONS(0000, ""),
    /*成功统一为：0*/
    OPTIONS_SUCCESS(0, "操作成功"),
    /*1 登录、注册相关*/
    GET_VERIFY_CODE_FAILURE(1005,"获取验证码失败,请稍后再试"),
    GET_VERIFY_CODE_SUCCESS(0,"获取验证码成功"),
    VERIFY_CODE_FAILURE(1004,"验证码校验失败"),
    VERIFY_CODE_SUCCESS(0,"验证码校验成功"),
    /*2 用户管理*/
    USER_UNLOCKED_SUCCESS (0,"解锁成功,请登录!"),
    USER_ACCOUNT_PASSWORD_ERROR(1111, "用户账号或密码不正确"),

    USER_ACCOUNT_UNAVAILABLE(1110, "账号已禁用，请联系管理员"),

    USER_ACCOUNT_LOCKED(1109, "账号已锁定"),

    USER_LOGIN_FAILURE(1102, "用户登录失败"),

    USER_LOGIN_VALIDATE_FAILURE(2007,"用户登录验证失败，请稍后重试"),

    USER_LOGIN_SUCCESS(0,"登录成功"),

    USER_LOGIN_OUT_SUCCESS(0,"用户退出成功"),
    USER_LOGIN_OUT_FAILURE(1125,"用户退出失败"),
    UPDATE_SUCCESS(0,"更新成功"),

    UPDATE_FAILURE(1115,"更新失败"),

    OLD_PASSWORD_FAILURE(1122,"旧密码错误"),

    USER_PASSWORD_FAILURE(1124,"用户密码错误"),
    /*3 财务管理*/
    ORDER_CREATE_FAIL(0, "订单创建失败"),

    ORDER_CREATE_SUCCESS(0, "订单创建成功"),

    ORDER_PRODUCT_NOT_ENOUGH(205, "库存不足"),

    ORDER_ILLEGAL(-1, "订单非法操作"),

    ORDER_NOT_EXIST(5, "订单不存在"),

    ORDER_UPDATE_FAIL(0, "订单修改失败"),

    ORDER_CANCEL_FAIL(0, "订单取消失败"),

    ORDER_CANCEL_SUCCESS(0, "订单取消成功"),

    PAY_SUCCESS(0, "支付成功"),

    PAY_FAIL(0, "支付失败"),

    PAY_EXCEPTION(-1, "支付异常"),
    /*4 用户权限及请求、参数校验*/
    UNAUTHORIZED(4001, "用户暂未登录或token已经过期"),

    AUTH_NOT_ENOUGH(4003, "用户权限不足,无法执行该操作"),

    PARAMETER_VALIDATE_FAILURE(4015, "参数校验失败"),

    DATA_FORMAT_FAILURE(4015, "数据格式化失败"),

    MEDIA_TYPE_NOT_SUPPORT(4015, "请求内容类型不支持"),

    METHOD_NOT_SUPPORT(4005, "不支持的请求方式"),

    MISSING_PARAMETER(4000, "请求参数缺少"),

    TYPE_MIS_MATCH(4000, "请求参数不匹配"),

    MESSAGE_NOT_READABLE(4000, "请求参数无法读取"),

    NO_HANDLER_FOUND(4004, "请求不存在"),

    ILLEGAL_OPTIONS(4010, "非法操作"),

    OPTIONS_FAILURE(4011,"操作失败"),

    PERMISSION_NAME_SORT_EXIST(4012,"权限名称或该排序已存在"),
    /*5 支付相关*/


    /*6 系统相关*/
    CATEGORY_EXIST(6100,"公告类别已存在"),
    CUSTOM_FAILURE(6000,"失败"),
    PAGE_VALIDATE_FAILED(6001,"请输入正确的分页信息"),
    DELETE_SUCCESS(0,"删除成功"),
    DELETE_FAILURE(6002,"删除失败"),
    CLEAN_SUCCESS(0,"清空成功"),
    CLEAN_FAILURE(6008,"清空失败"),
    ADD_SUCCESS(0,"添加成功"),
    ADD_FAILURE(6007,"添加失败"),
    FILE_NAME_LENGTH_LIMIT(6003,"文件名长度超限制"),
    FILE_SIZE_LIMIT(6004,"文件大小超限制"),
    FILE_INVALID_EXTENSION(6005,"不支持的文件类型"),
    EXPORT_DATA_FAILURE(6006,"数据导出失败"),
    /*7 平台资源相关*/
    /*8 平台文章相关*/
    /*9 平台论坛相关*/
    /*10 角色相关*/
    ROLE_NAME_TAG_EXIST(1010,"角色名称或标志已存在"),
    /*11 菜单相关*/
    MENU_NAME_SORT_EXIST(1101,"菜单名称或该排序已存在"),
    /*13 字典*/
    DICT_TABLE_FIELD_EXIST(1011,"表名或字段名已存在"),
    DICT_DETAIL_EXIST(1012,"字典详情已存在"),

    CONFIG_NAME_TAG_EXIST(8006,"配置名称或标签已存在");
    @Getter
    private final Integer code;
    @Getter
    private final String desc;
}
