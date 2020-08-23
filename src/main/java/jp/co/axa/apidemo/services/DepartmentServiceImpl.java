package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.DepartmentDto;
import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeService employeeService;

    @Override
    public Department saveDepartment(DepartmentDto departmentDto) {

        return departmentRepository.save(dtoToDept(departmentDto));

    }

    @Override
    public boolean isExistDepartment(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public boolean isExistByCodeDepartment(String code) {
        return departmentRepository.findDepartmentByDeptName(code) != null;
    }

    @Override
    public boolean isExistByNameDepartment(String name) {
        return departmentRepository.findDepartmentByDeptName(name) != null;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {


        return deptToDto(departmentRepository.save(dtoToDept(departmentDto)));
    }

    @Override
    public void removeDepartment(Long id) {

        if (!(departmentRepository.findById(id).get().getEmployeeList().isEmpty() || departmentRepository.findById(id).get().getEmployeeList() == null))

            getAllEmployeeByDeptId(id).forEach(employee -> {
                        employee.setDepartment(null);
                        employeeService.saveEmployee(employeeService.dtoToEmp(employee));
                    }
            );

        departmentRepository.deleteById(id);
    }

    @Override
    public void removeDepartmentAndAllEmp(Long id) {

        if (!(departmentRepository.findById(id).get().getEmployeeList().isEmpty() || departmentRepository.findById(id).get().getEmployeeList() == null))
            getAllEmployeeByDeptId(id).forEach(employee -> employeeService.deleteEmployee(employee.getId()));

        departmentRepository.deleteById(id);

    }

    @Override
    public List<DepartmentDto> getAllDepartment() {

        return departmentRepository.findAll().stream().map(department -> {

            return deptToDto(department);

        }).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        return deptToDto(departmentRepository.findDepartmentByDeptName(name));
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        return deptToDto(departmentRepository.findById(id).get());
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        return deptToDto(departmentRepository.findDepartmentByDeptCode(code));
    }

    @Override
    public List<EmployeeDto> getAllEmployeeByDeptName(String name) {

        return getDepartmentByName(name).getEmployeeDtoList();

    }

    @Override
    public List<EmployeeDto> getAllEmployeeByDeptCode(String code) {
        return getDepartmentByCode(code).getEmployeeDtoList();
    }

    @Override
    public List<EmployeeDto> getAllEmployeeByDeptId(Long id) {
        return getDepartmentById(id).getEmployeeDtoList();
    }


    public Department dtoToDept(DepartmentDto departmentDto) {


        return Department.builder()
                .depId(departmentDto.getDepId())
                .deptName(departmentDto.getDeptName())
                .deptCode(departmentDto.getDeptCode())
                .employeeList(departmentDto.getEmployeeDtoList() != null ? departmentDto.getEmployeeDtoList().stream().map(employeeDto -> employeeService.dtoToEmp(employeeDto)).collect(Collectors.toList()) : null)

                .build();

    }


    public DepartmentDto deptToDto(Department department) {

        return DepartmentDto.builder()

                .depId(department.getDepId())

                .deptName(department.getDeptName())

                .deptCode(department.getDeptCode())

                .employeeDtoList(department.getEmployeeList() != null ? department.getEmployeeList().stream().map(employee -> employeeService.empToDto((employee))).collect(Collectors.toList()) : null)

                .build();

    }

}
