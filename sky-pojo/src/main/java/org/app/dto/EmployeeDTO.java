package org.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 作者:疏狂难除
 * 时间:2024-03-09
 */
@Data
@Schema(description = "新增员工信息数据传输对象")
public class EmployeeDTO implements Serializable {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "性别")
    private String sex;
    @Schema(description = "身份证号")
    private String idNumber;

}
