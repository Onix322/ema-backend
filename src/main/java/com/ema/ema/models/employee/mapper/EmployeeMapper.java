package com.ema.ema.models.employee.mapper;

import com.ema.ema.models.employee.Employee;
import com.ema.ema.models.employee.dto.EmployeeDto;
import org.mapstruct.factory.Mappers;

/** EmployeeMapper */
public interface EmployeeMapper {

  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  Employee fromDto(Employee employee);

  EmployeeDto toDto(EmployeeDto dto);
}
