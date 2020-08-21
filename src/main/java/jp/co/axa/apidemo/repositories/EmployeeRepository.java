package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long> {

    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeesByFirstName(String firstName);

    List<Employee> findEmployeesByLastName(String lastName);
}
