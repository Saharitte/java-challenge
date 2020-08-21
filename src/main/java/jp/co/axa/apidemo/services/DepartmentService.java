package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.DepartmentDto;
import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.entities.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    void removeDepartment(Long id);

    void removeDepartmentAndAllEmp(Long id);


    boolean isExistDepartment(Long id);

    boolean isExistByCodeDepartment(String code);

    boolean isExistByNameDepartment(String name);


    List<DepartmentDto> getAllDepartment();

    DepartmentDto getDepartmentByName(String name);

    DepartmentDto getDepartmentById(Long id);

    DepartmentDto getDepartmentByCode(String code);

    List<EmployeeDto> getAllEmployeeByDeptName(String name);

    List<EmployeeDto> getAllEmployeeByDeptCode(String code);

    List<EmployeeDto> getAllEmployeeByDeptId(Long id);


}
