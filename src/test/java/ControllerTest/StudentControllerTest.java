/**
* @author: Patrick F
* @Date:Apr 18, 2017
* 
* Test cases for student controller methods
**/

package ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.nxtgear.config.ProjectConfig;
import com.nxtgear.config.RootConfig;
import com.nxtgear.config.WebConfig;
import com.nxtgear.controller.StudentController;
import com.nxtgear.pojo.Student;
import com.nxtgear.service.StudentService;

@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={ProjectConfig.class, WebConfig.class, RootConfig.class})
public class StudentControllerTest {

  // StudentController controller = new StudentController();
	
   @Autowired
   private StudentService studentService;
   
   @Mock
   private StudentService studentServiceMock;

   @InjectMocks
   private StudentController controller;

   private MockMvc mockMvc;
   
   @Before
   public void setup() {

       // Process mock annotations
       MockitoAnnotations.initMocks(this);

       // Setup Spring test in stand alone mode
       this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

   }
	/**
	 * Test case for home page request
	 * 
	 */
	@Test
	public void testHomePage() throws Exception {
		mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/")).andExpect(view().name("students"))
		.andExpect(model().attributeExists("student"))
		.andExpect(model().attributeExists("students"));
	}
	/**
	 * test case for submitting student form
	 * 
	 */
	@Test
	public void testNewStudent() throws Exception{
		Student s = new Student("test", "test", "test", "test", 8);
		MultiValueMap<String, String> studentMap = new LinkedMultiValueMap<String, String>();
		studentMap.add("id", "0");
		studentMap.add("firstName", "test99");
		studentMap.add("lastName", "test");
		studentMap.add("school", "test");
		studentMap.add("gender", "test");
		studentMap.add("age", "99");
		mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/").params(studentMap)).andExpect(view().name("redirect:/"))
		.andExpect(model().attributeExists("student"));
		
		studentService.saveOrUpdate(s);
		Student savedS = studentService.load("test");
		s.setId(savedS.getId());
		Assert.assertEquals(s, savedS);
	}
	/**
	 * Test case for submitting student form with errors
	 * 
	 */
	@Test
	public void testNewStudentError() throws Exception{
		MultiValueMap<String, String> studentMap = new LinkedMultiValueMap<String, String>();
		studentMap.add("id", "0");
		studentMap.add("firstName", "");
		studentMap.add("lastName", "");
		studentMap.add("school", "");
		studentMap.add("gender", "");
		studentMap.add("age", "");
		mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/").params(studentMap)).andExpect(view().name("students"))
		.andExpect(model().attributeExists("student"))
		.andExpect(model().attributeExists("students"))
		.andExpect(model().attributeExists("feedback"));
		
		
	}
	/**
	 * Test case for student edit form 
	 * 
	 */
	@Test
	public void testEditStudent() throws Exception{
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/")).andExpect(view().name("students"))
		.andExpect(model().attributeExists("student"))
		.andExpect(model().attributeExists("students"));
	}
	/**
	 * Test case for submitting student edit form with errors
	 * 
	 */
	@Test
	public void testEditStudentError() throws Exception{
		MultiValueMap<String, String> studentMap = new LinkedMultiValueMap<String, String>();
		studentMap.add("id", "0");
		studentMap.add("firstName", "");
		studentMap.add("lastName", "");
		studentMap.add("school", "");
		studentMap.add("gender", "");
		studentMap.add("age", "");
		mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/").params(studentMap)).andExpect(view().name("students"))
		.andExpect(model().attributeExists("student"))
		.andExpect(model().attributeExists("students"))
		.andExpect(model().attributeExists("feedback"));
	}
	/**
	 * Test case for submitting student edit form 
	 * 
	 */
	@Test
	public void testEditSubmitStudent() throws Exception{
		mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/")).andExpect(view().name("students"))
		.andExpect(model().attributeExists("student"))
		.andExpect(model().attributeExists("students"))
		.andExpect(model().attributeExists("feedback"));
	}
	/**
	 * Test case for deleting student
	 * 
	 */
	@Test
	public void testDeleteStudent() throws Exception{
		mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/")).andExpect(view().name("students"))
		.andExpect(model().attributeExists("feedback"));
	}

}
