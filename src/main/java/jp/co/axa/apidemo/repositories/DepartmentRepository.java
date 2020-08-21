package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Long> {

    Department findDepartmentByDeptName(String deptName);

    Department findDepartmentByDeptCode(String code);

}


