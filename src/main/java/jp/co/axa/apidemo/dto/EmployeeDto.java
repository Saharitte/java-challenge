package jp.co.axa.apidemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {


    private Long id;


    private String firstName;


    private String lastName;


    private String address;


    private String email;


    private Integer salary;

    private DepartmentDto department;


}
