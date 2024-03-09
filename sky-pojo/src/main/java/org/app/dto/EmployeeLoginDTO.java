package org.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 作者:疏狂难除
 * 时间:2024-03-09
 */
@Data
@Schema(description = "员工登录时传递的数据模型")
public class EmployeeLoginDTO {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
