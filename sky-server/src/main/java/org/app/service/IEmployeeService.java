package org.app.service;


import com.mybatisflex.core.service.IService;
import org.app.dto.EmployeeDTO;
import org.app.dto.EmployeeLoginDTO;
import org.app.dto.EmployeePageQueryDTO;
import org.app.entity.EmployeeEntity;
import org.app.result.PageResult;

/**
 * 员工信息 服务层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
public interface IEmployeeService extends IService<EmployeeEntity> {

    EmployeeEntity login(EmployeeLoginDTO employeeLoginDTO);

    void saveDto(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

}