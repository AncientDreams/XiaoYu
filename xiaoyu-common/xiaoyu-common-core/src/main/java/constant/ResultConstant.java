package constant;

/**
 * <p>
 * 微服务响应 常量
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/15 0015 11:24
 */
public interface ResultConstant {

    String SUCCESS_CODE = "00";

    String FAIL_CODE = "99";

    String SUCCESS = "成功";

    String FAIL = "失败";

    String GRANT_SUCCESS = "授权成功";

    String GRANT_FAIL = "授权失败";

    String QUERY_SUCCESS = "查询成功";

    String QUERY_FAIL = "查询失败";

    String SAVE_SUCCESS = "保存成功";

    String SAVE_FAIL = "保存失败";

    String UPDATE_SUCCESS = "更新成功";

    String UPDATE_FAIL = "更新失败";

    String REMOVE_SUCCESS = "删除成功";

    String REMOVE_FAIL = "删除失败";

    String EXCEPTION = "发生异常";

    String LOGIN_SUCCESS = "登录成功";

    String CHECK_PASSWORD_FAIL = "两次输入的密码不一致";

    String USER_NOT_EXIST = "用户不存在";

    String USER_EXIST = "用户已存在";

    String SERVER_ERROR = "服务器内部错误";

    String LOGIN_ERROR = "用户名或密码不正确";

    String ROLE_EXIST = "角色已存在";
}
