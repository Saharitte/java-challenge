package jp.co.axa.apidemo.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import jp.co.axa.apidemo.ApiDemoApplication;
import jp.co.axa.apidemo.dto.DepartmentDto;
import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.services.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiDemoApplication.class)
@AutoConfigureMockMvc

@AutoConfigureTestDatabase
public class DepartmentControllerTest {


    List<Department> listDep;


    @Autowired
    private MockMvc mvc;

    private Department department;

    private DepartmentDto departmentDto;


    @Mock
    private DepartmentService departmentService;

    @Before
    public void setUp() {

        listDep = mock(ArrayList.class);

        department = createTestDepartment(1L, "Human Resoures", "HR");

        listDep.add(department);

        departmentDto = DepartmentDto.builder()
                .depId(department.getDepId())
                .deptName(department.getDeptName())
                .deptCode(department.getDeptCode()).build();


    }


    @Test
    public void whenCreateEmployee_thenStatus201() throws Exception {

        when(departmentService.isExistByNameDepartment(departmentDto.getDeptName())).thenReturn(false);

        when(departmentService.saveDepartment(departmentDto)).thenReturn(department);

        mvc.perform(post("/api/v1/departments/create").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)

                .content(asJsonString(departmentDto)))

                .andDo(print())

                .andExpect(status().isCreated());
    }


    @Test
    public void givenDepartments_whenGetDepartments_thenStatus200() throws Exception {

        doReturn(Lists.newArrayList(departmentDto)).when(departmentService).getAllDepartment();

        mvc.perform(get("/api/v1/employees/all"))

                .andExpect(status().isOk())

                .andDo(print());

    }


    @Test
    public void givenDeparmtents_whenGetDepartments_thenStatus200() throws Exception {

        doReturn(Lists.newArrayList(departmentDto)).when(departmentService).getAllDepartment();

        mvc.perform(get("/api/v1/departments/all"))

                .andDo(print())

                .andExpect(status().isOk());
    }


    private Department createTestDepartment(Long id, String depName, String depCode) {

        return Department.builder()
                .depId(id)
                .deptName(depName)
                .deptCode(depCode).build();
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
