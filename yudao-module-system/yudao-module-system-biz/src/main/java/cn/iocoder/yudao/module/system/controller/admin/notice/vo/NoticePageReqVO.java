package cn.iocoder.yudao.module.system.controller.admin.notice.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 通知公告分页 Request VO")
@Data
// callSuper = true 表示生成的 equals 和 hashCode 方法会调用父类的方法
// 用于确保父类的字段也被包含在 equals 和 hashCode 方法的比较和计算中
@EqualsAndHashCode(callSuper = true)
public class NoticePageReqVO extends PageParam {

    @Schema(description = "通知公告名称，模糊匹配", example = "芋道")
    private String title;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

}
