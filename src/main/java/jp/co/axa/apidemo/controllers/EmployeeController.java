package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees() {

        return new ResponseEntity(employeeService.retrieveEmployees(), HttpStatus.OK);

    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {

        if (!employeeService.isExist(employeeId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


        return new ResponseEntity(employeeService.getEmployee(employeeId), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {


        if (employeeService.isExist(employee.getId())) {

            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

        employeeService.saveEmployee(employee);

        LOG.info("Employee Saved Successfully");

        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {

        if (!employeeService.isExist(employeeId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        employeeService.deleteEmployee(employeeId);

        LOG.info("Employee DELETED Successfully");

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeDto employee,
                                               @PathVariable(name = "employeeId") Long employeeId) {
        if (!employeeService.isExist(employeeId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


        employeeService.updateEmployee(employee, employeeId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable(name = "email") String email) {

        if (!employeeService.isExistByEmail(email)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity(employeeService.getEmployeeByEmail(email), HttpStatus.OK);

    }

    @GetMapping("/byFirstName/{firstName}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByFstName(@PathVariable(name = "firstName") String firstName) {

        if (employeeService.getEmployeeByFstName(firstName).isEmpty() || employeeService.getEmployeeByFstName(firstName) == null) {


            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity(employeeService.getEmployeeByFstName(firstName).stream().map(employee -> employeeService.empToDto(employee)).collect(Collectors.toList()), HttpStatus.OK);

    }


    @GetMapping("/byLastName/{lastName}")
    ResponseEntity<List<EmployeeDto>> getEmployeeByLstName(@PathVariable(name = "lastName") String lastName) {

        List<Employee> listEmp = employeeService.getEmployeeByLstName(lastName);

        if (listEmp.isEmpty() || listEmp == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity(listEmp.stream().map(employee -> employeeService.empToDto(employee)).collect(Collectors.toList()), HttpStatus.OK);

    }

}

