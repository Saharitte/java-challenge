package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> retrieveEmployees();

    Boolean isExist(Long employeeId);

    Employee getEmployee(Long employeeId);

    Employee getEmployeeByEmail(String email);

    List<Employee> getEmployeeByFstName(String firstName);

    List<Employee> getEmployeeByLstName(String lastName);

    void saveEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    void updateEmployee(EmployeeDto employeeDto, Long employeeId);

    EmployeeDto empToDto(Employee employee);

    Employee dtoToEmp(EmployeeDto employeeDto);


    boolean isExistByEmail(String email);
}