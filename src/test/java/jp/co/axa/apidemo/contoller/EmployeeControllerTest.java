package jp.co.axa.apidemo.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import jp.co.axa.apidemo.ApiDemoApplication;
import jp.co.axa.apidemo.dto.EmployeeDto;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.hamcrest.Matchers;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiDemoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class EmployeeControllerTest {


    List<Employee> listEmployee;
    @Mock
    Employee john;
    EmployeeDto johnDto;
    @Autowired
    private MockMvc mvc;
    @Mock
    private EmployeeService employeeService;

    @Before
    public void setUp() {

        listEmployee = mock(ArrayList.class);

        john = createTestEmployee(1L, "John", "smith", "test@test");

        listEmployee.add(john);

        johnDto = EmployeeDto.builder().id(john.getId()).firstName(john.getFirstName()).lastName(john.getLastName()).build();

    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {

        doReturn(listEmployee).when(employeeService).retrieveEmployees();

        mvc.perform(get("/api/v1/employees/all"))

                .andExpect(status().isOk())

                .andExpect(content().string(Matchers.containsString(asJsonString(Lists.newArrayList(john)
                        )
                )))

                .andDo(print());

    }

    @Test
    public void whenCreateEmployee_thenStatus201() throws Exception {

        when(employeeService.isExist(john.getId())).thenReturn(false);

        doNothing().when(employeeService).saveEmployee(john);

        mvc.perform(post("/api/v1/employees/create").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)

                .content(asJsonString(john)))


                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void whenDeleteEmployee_thenStatus200() throws Exception {

        when(employeeService.isExist(john.getId())).thenReturn(true);

        doNothing().when(employeeService).deleteEmployee(john.getId());

        mvc.perform(delete("/api/v1/employees/{employeeId}", john.getId()))

                .andDo(print())
                .andExpect(status().isOk());
    }


    private Employee createTestEmployee(Long id, String firstName, String lastName, String email) {

        return Employee.builder()
                .id(id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName).build();
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
