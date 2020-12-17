package test;
/*
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.TaskService;
import com.example.demo.Tasks;
*/
class ControllerTest {

	/*
	@Autowired private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskService;
	
	@Test
	public void List_All() throws Exception{
		when(taskService.findAll()).thenReturn(
				Arrays.asList(new Tasks(new Long(1), "Task 1 done", "Finished"),
						new Tasks(new Long(2), "Task 2 TODO", "Ongoing"))
				);
		RequestBuilder request = MockMvcRequestBuilders.get("/tasks").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("[{'id' :1, 'description':'Task 5 done','status':Finished'},{'id' :2, 'description':'Task 6 TODO','status':Ongoing'}")).andReturn();		
	}
	*/
	
	/*
	@Test
	public void Find_Id() throws Exception{
		Tasks prueba = new Tasks(new Long(1), "Hecha espectácular", "Finished");
		Optional<Tasks> task = Optional.of(prueba);
		
		when(taskService.findById(new Long(2)).thenReturn(task));
		
	}
	*/
}
