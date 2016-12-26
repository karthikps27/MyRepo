package org.karthikps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskControllerJson {

	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	TaskJDBCTemplate taskJdbcTemplate = (TaskJDBCTemplate)context.getBean("taskJdbcTemplate");
	
	
	@RequestMapping(value="/getTaskJson")
	public List<Tasks> getAllTasksAjax() {
		List<Tasks> allTasks = taskJdbcTemplate.listTasks();
		return allTasks;
	}
	
	@RequestMapping(value="/markAsCompleteRest", method = RequestMethod.GET, params = {"id","comments"})
	public List<Tasks> markAsComplete(@RequestParam(value="id") String id, @RequestParam(value="comments") String comments) {
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.modifyStatus(idInt, TaskStatus.COMPLETED,comments);
		List<Tasks> allTasks = taskJdbcTemplate.listTasks();
		return allTasks;
	}
	
	@RequestMapping(value="/studentJson")
	public List<Tasks> getResultsByAjax() {
		
		List<Tasks> students = new ArrayList<Tasks>();
		Tasks student = new Tasks();
		student.setComments("fjdsljfs");
		student.setDateOfCreation(new Date());
		student.setLogged(true);
		student.setPriority(3);
		student.setTaskId(4);
		student.setTaskStatus(3);
		student.setTaskSummary("kfdsjakfjskla fdsafs");
		students.add(student);
		return students;
	}
	
	@RequestMapping(value="/markAsInProgressRest", method = RequestMethod.GET, params = {"id"})
	public List<Tasks> markAsInProgress(@RequestParam(value="id") String id) {		
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.modifyStatus(idInt, TaskStatus.INPROGRESS,"");
		List<Tasks> allTasks = taskJdbcTemplate.listTasks();
		return allTasks;
	}
	
	@RequestMapping(value="/moveTaskToLogRest", method = RequestMethod.GET, params = {"id"})
	public List<Tasks> moveTaskToLog(@RequestParam(value="id") String id) {
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.moveTasksToLog(idInt);
		List<Tasks> allTasks = taskJdbcTemplate.listTasks();
		return allTasks;
	}
	
	@RequestMapping(value="/getLoggedTasksRest", method = RequestMethod.GET)
	public List<Tasks> getLoggedTasks() {
		List<Tasks> allLoggedTasks = taskJdbcTemplate.getAllLoggedTasks();
		return allLoggedTasks;
	}
	
	@RequestMapping(value="/getQueriedTasksRest", method = RequestMethod.GET, params={"fromDate","toDate","priority"})
	public List<Tasks> getQueriedTasks(@RequestParam(value="fromDate") String fromDate, 
			@RequestParam(value="toDate") String toDate, @RequestParam(value="priority") String priority) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDateObj = new Date();
		Date toDateObj = new Date();
		try {
		fromDateObj = format.parse(fromDate);
		toDateObj = format.parse(toDate);
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
		List<Tasks> filteredTasks = taskJdbcTemplate.getQueriedTasks(fromDateObj, toDateObj, Integer.parseInt(priority));
		return filteredTasks;
	}
	
	@RequestMapping(value="/getTaskDetailsRest", method = RequestMethod.GET, params={"id"})
	public Tasks getTaskDetails(@RequestParam(value="id") String id) {
		Tasks taskDetails = taskJdbcTemplate.getTaskDetails(Integer.parseInt(id));
		return taskDetails;
	}
	
	@RequestMapping(value="/deleteTasksRest", method = RequestMethod.GET, params = {"id"})	
	public List<Tasks> deleteTasksRest(@RequestParam(value="id") String id) {
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.delete(idInt);
		List<Tasks> taskList = taskJdbcTemplate.listTasks();
		return taskList;		
	}
	
	@RequestMapping(value="/updateTask", method = RequestMethod.GET, params={"id","priority","summary","comments"})
	public List<Tasks> updateTaskRest(@RequestParam(value="id") String id,
			@RequestParam(value="priority") String priority,
			@RequestParam(value="summary") String summary,
			@RequestParam(value="comments") String comments) {
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.modifyTaskDetails(Integer.parseInt(id), Integer.parseInt(priority), summary, comments);
		List<Tasks> allTasks = taskJdbcTemplate.listTasks();
		return allTasks;
	}
}
