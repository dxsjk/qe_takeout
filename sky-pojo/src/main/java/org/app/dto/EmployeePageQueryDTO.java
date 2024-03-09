package org.app.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * 作者:疏狂难除
 * 时间:2024-03-09
 */
@Data
@AllArgsConstructor
@Schema(description = "员工分页查询DTO")
public class EmployeePageQueryDTO implements Serializable {

    @Schema(description = "员工姓名")
    private String name;
    @Schema(description = "页码")
    private int page;
    @Schema(description = "每页显示记录数")
    private int pageSize;
}
