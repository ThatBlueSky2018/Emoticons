package org.pancakeapple.constant;

public class RBACConstant {
    //请求认证
    public static final String AUTH_HEADER ="Authorization";
    public static final String AUTH_TOKEN="Token ";
    public static final String DEFAULT_ROLE="USER";

    //jwt令牌配置信息
    public static final String JWT_SIGN_KEY="emoticons";
    public static final Long JWT_EXPIRE=43200000L; //12小时

    //jwt令牌claims
    public static final String USER_ID="id";
    public static final String USER_NAME="username";
    public static final String USER_ROLES="roles";
}
