package cn.iocoder.yudao.module.system.controller.admin.auth.vo;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import cn.iocoder.yudao.module.system.enums.social.SocialTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

// 描述 API 文档中的字段信息，description 属性提供了字段的详细描述。
@Schema(description = "管理后台 - 账号密码登录 Request VO，如果登录并绑定社交用户，需要传递 social 开头的参数")
// 这是 Lombok 提供的注解，自动生成所有字段的 getter、setter 方法，以及 toString、equals 和 hashCode 方法。
@Data
// 这是 Lombok 提供的注解，自动生成无参构造方法。
@NoArgsConstructor
// 这是 Lombok 提供的注解，自动生成全参构造方法。
@AllArgsConstructor
// 这是 Lombok 提供的注解，自动生成建造者模式的 Builder 类。
// 使用@Builder注解（链式调用）：
//      AuthLoginReqVO authLoginReqVO = AuthLoginReqVO.builder()
//          .username("yudaoyuanma")
//          .password("buzhidao")
//          .captchaVerification("PfcH6mgr8tpXuMWFjvW6YVaqrswIuwmWI5dsVZSg7sGpWtDCUbHuDEXl3cFB1+VvCC/rAkSwK8Fad52FSuncVg==")
//          .socialType(10)
//          .socialCode("1024")
//          .socialState("9b2ffbc1-7425-4155-9894-9d5c08541d62")
//          .build();
// 不使用@Builder注解：
//        AuthLoginReqVO authLoginReqVO = new AuthLoginReqVO();
//        authLoginReqVO.setUsername("yudaoyuanma");
//        authLoginReqVO.setPassword("buzhidao");
//        authLoginReqVO.setCaptchaVerification("PfcH6mgr8tpXuMWFjvW6YVaqrswIuwmWI5dsVZSg7sGpWtDCUbHuDEXl3cFB1+VvCC/rAkSwK8Fad52FSuncVg==");
//        authLoginReqVO.setSocialType(10);
//        authLoginReqVO.setSocialCode("1024");
//        authLoginReqVO.setSocialState("9b2ffbc1-7425-4155-9894-9d5c08541d62");
@Builder
public class AuthLoginReqVO {

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudaoyuanma")
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "buzhidao")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    // ========== 图片验证码相关 ==========

    @Schema(description = "验证码，验证码开启时，需要传递", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "PfcH6mgr8tpXuMWFjvW6YVaqrswIuwmWI5dsVZSg7sGpWtDCUbHuDEXl3cFB1+VvCC/rAkSwK8Fad52FSuncVg==")
    @NotEmpty(message = "验证码不能为空", groups = CodeEnableGroup.class)
    private String captchaVerification;

    // ========== 绑定社交登录时，需要传递如下参数 ==========

    @Schema(description = "社交平台的类型，参见 SocialTypeEnum 枚举值", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @InEnum(SocialTypeEnum.class)
    private Integer socialType;

    @Schema(description = "授权码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String socialCode;

    @Schema(description = "state", requiredMode = Schema.RequiredMode.REQUIRED, example = "9b2ffbc1-7425-4155-9894-9d5c08541d62")
    private String socialState;

    /**
     * 开启验证码的 Group
     */
    public interface CodeEnableGroup {}

    // 用于 Java Bean Validation 中，指定一个布尔方法必须返回 true 才能通过验证
    @AssertTrue(message = "授权码不能为空")
    public boolean isSocialCodeValid() {
        return socialType == null || StrUtil.isNotEmpty(socialCode);
    }

    @AssertTrue(message = "授权 state 不能为空")
    public boolean isSocialState() {
        return socialType == null || StrUtil.isNotEmpty(socialState);
    }

}