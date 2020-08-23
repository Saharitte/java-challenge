package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.DepartmentDto;
import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/create")
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentDto departmentdto) {

        if (null == departmentdto) {


            LOG.info("object is null");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        if (departmentService.isExistByNameDepartment(departmentdto.getDeptName())) {

            LOG.info("departement  exists");


            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

        departmentService.saveDepartment(departmentdto);

        LOG.info("departement successuflly created");


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/update/{deptId}")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentDto departmentdto, @PathVariable(name = "deptId") Long deparmentId) {

        if (null == departmentdto) {

            LOG.info("object is null");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        if (!departmentService.isExistDepartment(deparmentId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }
        departmentService.updateDepartment(departmentdto);

        LOG.info("departement successfully updated");

        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @DeleteMapping("/{deparmentId}")
    public ResponseEntity<Void> deleteDeparment(@PathVariable(name = "deparmentId") Long deparmentId) {


        if (!departmentService.isExistDepartment(deparmentId)) {


            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }
        departmentService.removeDepartment(deparmentId);

        LOG.info("departement successfully deleted");


        return new ResponseEntity<>(HttpStatus.OK);

    }


    @DeleteMapping("/removedeptdata/{deparmentId}")
    public ResponseEntity<Void> removeDepartmentAndAllEmp(@PathVariable(name = "deparmentId") Long deparmentId) {

        if (!departmentService.isExistDepartment(deparmentId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }
        departmentService.removeDepartmentAndAllEmp(deparmentId);

        LOG.info("departement successfully deleted");


        return new ResponseEntity<>(HttpStatus.OK);


    }


    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {


        return new ResponseEntity(departmentService.getAllDepartment(), HttpStatus.OK);


    }


    @GetMapping("/deptbyname/{deptName}")
    public ResponseEntity<DepartmentDto> getDepartmentByName(@PathVariable(name = "deptName") String name) {

        if (!departmentService.isExistByNameDepartment(name)) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }

        return new ResponseEntity(departmentService.getDepartmentByName(name), HttpStatus.OK);

    }

    @GetMapping("/deptbyid/{deptId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "deptId") Long id) {

        if (!departmentService.isExistDepartment(id)) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }
        return new ResponseEntity(departmentService.getDepartmentById(id), HttpStatus.OK);

    }

    @GetMapping("/deptbycode/{deptCode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable(name = "deptCode") String code) {


        if (!departmentService.isExistByCodeDepartment(code)) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }
        return new ResponseEntity(departmentService.getDepartmentByCode(code), HttpStatus.OK);

    }


    @GetMapping("/{deptName}/employeesbydeptname")
    public ResponseEntity<EmployeeDto> getAllEmployeeByDeptName(@PathVariable(name = "deptName") String name) {

        if (!departmentService.isExistByNameDepartment(name)) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }

        if (departmentService.getAllEmployeeByDeptName(name).isEmpty() || departmentService.getAllEmployeeByDeptName(name) == null) {

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

        return new ResponseEntity(departmentService.getAllEmployeeByDeptName(name), HttpStatus.OK);

    }


    @GetMapping("/{deptCode}/employeesbydeptcode")
    public ResponseEntity<EmployeeDto> getAllEmployeeByDeptCode(@PathVariable(name = "deptCode") String code) {

        if (!departmentService.isExistByCodeDepartment(code)) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }
        if (departmentService.getAllEmployeeByDeptCode(code).isEmpty() || departmentService.getAllEmployeeByDeptCode(code) == null) {

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

        return new ResponseEntity(departmentService.getAllEmployeeByDeptCode(code), HttpStatus.OK);

    }


    @GetMapping("/{deptId}/employeesbydeptid")
    public ResponseEntity<EmployeeDto> getAllEmployeeByDeptId(@PathVariable(name = "deptId") Long id) {

        if (!departmentService.isExistDepartment(id)) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
        if (departmentService.getAllEmployeeByDeptId(id).isEmpty() || departmentService.getAllEmployeeByDeptId(id) == null) {

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity(departmentService.getAllEmployeeByDeptId(id), HttpStatus.OK);

    }


}
