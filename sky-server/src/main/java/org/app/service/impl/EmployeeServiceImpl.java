package org.app.service.impl;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;

import org.app.constant.Message;
import org.app.constant.Password;
import org.app.context.BaseContext;
import org.app.dto.EmployeeDTO;
import org.app.dto.EmployeeLoginDTO;

import org.app.dto.EmployeePageQueryDTO;
import org.app.entity.EmployeeEntity;
import org.app.exception.AccountLockException;
import org.app.exception.AccountNotFoundException;
import org.app.mapper.EmployeeMapper;
import org.app.result.PageResult;
import org.app.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.util.DigestUtils;
import org.app.entity.table.EmployeeEntityTableDef;


import java.util.List;

import static org.app.constant.Status.DISABLE;

/**
 * 员工信息 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeEntity> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public EmployeeEntity login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        EmployeeEntity employeeEntity1 = employeeMapper
                .selectOneByQuery(
                        QueryWrapper
                                .create()
                                .where(EmployeeEntityTableDef.EMPLOYEE_ENTITY.USERNAME.eq(username)));
        // 是否存在
        if (employeeEntity1 == null) {
            throw new AccountNotFoundException(Message.ACCOUNT_NOT_FOUND);
        }
        // 密码是否正确 md5加密
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employeeEntity1.getPassword())) {
            throw new AccountNotFoundException(Message.PASSWORD_ERROR);
        }
        // 是否被锁定
        if(employeeEntity1.getStatus()==DISABLE){
            throw new AccountLockException(Message.ACCOUNT_LOCKED);
        }
        return employeeEntity1;

    }

    @Override
    public void saveDto(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO, employeeEntity);
        String password=DigestUtils.md5DigestAsHex(Password.DEFAULT_PASSWORD.getBytes());
        employeeEntity.setPassword(password);
        // TODO 创建人 更新人
        employeeEntity.setCreateUser(BaseContext.getCurrentId());
        employeeEntity.setUpdateUser(BaseContext.getCurrentId());
        int insert = employeeMapper.insert(employeeEntity,true);
        if (insert == 0) {
            throw new RuntimeException(Message.SAVE_FAILED);
        }

    }
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        Page<EmployeeEntity> paginate = employeeMapper.paginate((Number) employeePageQueryDTO.getPage(), (Number) employeePageQueryDTO.getPageSize(), new QueryWrapper());
        long totalPage = paginate.getTotalPage();
        List<EmployeeEntity> records = paginate.getRecords();
        return new PageResult(totalPage, records);

    }


}