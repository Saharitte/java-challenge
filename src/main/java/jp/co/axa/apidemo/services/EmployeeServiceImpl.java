package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.DepartmentRepository;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> retrieveEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees;
    }

    @Override
    public Boolean isExist(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).get();

    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }

    @Override
    public List<Employee> getEmployeeByFstName(String firstName) {
        return employeeRepository.findEmployeesByFirstName(firstName);
    }

    @Override
    public List<Employee> getEmployeeByLstName(String lastName) {
        return employeeRepository.findEmployeesByLastName(lastName);
    }

    @Override
    public void saveEmployee(Employee employee) {

        if (employee.getDepartment() != null) {

            Department department = departmentRepository.findById(employee.getDepartment().getDepId()).get();

            department.getEmployeeList().add(employee);

            departmentRepository.save(department);

        }
        employeeRepository.save(employee);

    }

    public void deleteEmployee(Long employeeId) {

        Employee employee = getEmployee(employeeId);

        if (employee.getDepartment() != null) {

            Department department = departmentRepository.findById(employee.getDepartment().getDepId()).get();

            department.getEmployeeList().remove(employee);

            departmentRepository.save(department);

        }

        employeeRepository.deleteById(employeeId);
    }


    @Override
    public void updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        Employee oldEmp = employeeRepository.findById(employeeId).get();

        Employee updatedEmp = dtoToEmp(employeeDto);

        if (employeeDto.getDepartment() != null) {
            if (employeeDto.getDepartment().getDepId() != oldEmp.getDepartment().getDepId()) {

                Department oldDep = departmentRepository.findById(oldEmp.getDepartment().getDepId()).get();

                oldDep.getEmployeeList().remove(oldEmp);

                departmentRepository.save(oldDep);


                Department newDep = departmentRepository.findById(employeeDto.getDepartment().getDepId()).get();

                newDep.getEmployeeList().add(updatedEmp);

                departmentRepository.save(newDep);

            }

        }

        employeeRepository.save(updatedEmp);
    }


    public EmployeeDto empToDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();

        BeanUtils.copyProperties(employee, employeeDto);

        return employeeDto;

    }


    public Employee dtoToEmp(EmployeeDto employeeDto) {

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDto, employee);

        return employee;

    }

    @Override
    public boolean isExistByEmail(String email) {

        return employeeRepository.findEmployeeByEmail(email) != null;
    }
}
