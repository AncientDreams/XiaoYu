package constant;

/**
 * <p>
 * 认证服务常量
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/16 0016 9:36
 */
public interface AuthConstant {

    /**
     * 对称秘钥
     */
    String SALINGER = "XiaoYu";

    String AUTH_KEY = "XiaoYu-Auth";

    /**
     * 网关响应消息
     */
    String LACK_TOKEN = "令牌缺失，拒绝访问";

    String DECRYPTION_FAILURE = "解密失败，请求未授权";
}

