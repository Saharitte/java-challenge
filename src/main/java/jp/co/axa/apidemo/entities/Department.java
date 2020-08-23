package jp.co.axa.apidemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * department entity
 */
@Entity
@Table(name = "DEPARTMENT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEP_ID")
    private Long depId;

    @Column(name = "DEP_NAME")
    private String deptName;

    @Column(name = "DEP_CODE")
    private String deptCode;

    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;


    public List<Employee> getEmployeeList() {

        if (null == employeeList) {
            employeeList = new ArrayList<>();


        }
        return employeeList;
    }

}
