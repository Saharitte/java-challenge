package jp.co.axa.apidemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long depId;


    private String deptName;


    private String deptCode;


    private List<EmployeeDto> employeeDtoList;


}
