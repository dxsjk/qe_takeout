package org.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.app.constant.JwtClaims;
import org.app.dto.EmployeeDTO;
import org.app.dto.EmployeeLoginDTO;
import org.app.dto.EmployeePageQueryDTO;
import org.app.entity.EmployeeEntity;
import org.app.result.PageResult;
import org.app.result.Result;
import org.app.utils.JWTUtils;
import org.app.properities.JwtProperty;
import org.app.vo.EmployeeLoginVo;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.app.service.IEmployeeService;

import java.util.HashMap;

/**
 * 员工信息 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/admin/employee")
@Tag(name = "员工相关接口", description = "员工相关接口")
@Slf4j
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private JwtProperty jwtProperty;

    // 登录
    @PostMapping("/login")
    @Operation(summary="员工登录")
    public Result<EmployeeLoginVo> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        EmployeeEntity employee = employeeService.login(employeeLoginDTO);
        // 设置jwt
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaims.EMP_ID, employee.getId());
        String jwt = JWTUtils.createJWT(
                jwtProperty.getAdminSecretKey(),
                jwtProperty.getAdminTtl(),
                claims);
        EmployeeLoginVo employeeLoginVo = EmployeeLoginVo.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(jwt)
                .build();
        return Result.success(employeeLoginVo);
    }

    // 退出
    @PostMapping("/logout")
    @Operation(summary="员工退出")
    public Result<String> logout() {
        return Result.success("退出成功");
    }
    //新增员工
    @PostMapping
    @Operation( summary = "新增员工")
    public Result<String> add(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveDto(employeeDTO);
        log.info("新增员工：{}", employeeDTO);
        return Result.success("新增成功");
    }
    // 分页查询员工
    @GetMapping("/page")
    @Operation(summary="分页查询员工")
    public Result<PageResult> page(@ParameterObject EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询员工：{}", employeePageQueryDTO);
        return Result.success(employeeService.pageQuery(employeePageQueryDTO));
    }

}