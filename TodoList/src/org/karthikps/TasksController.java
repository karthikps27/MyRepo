package org.karthikps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TasksController {
	Map<Integer,Tasks> tasks = new LinkedHashMap<Integer,Tasks>();
	Integer count = 0;
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	TaskJDBCTemplate taskJdbcTemplate = (TaskJDBCTemplate)context.getBean("taskJdbcTemplate");
	
	@RequestMapping(value="/tasks", method = RequestMethod.GET)
	public ModelAndView student() {
		return new ModelAndView("tasks","command", new Tasks());
	}
	
	@RequestMapping(value="/addTasks", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute("SpringWeb")Tasks task, ModelMap map) {
		ModelAndView modelAndView = new ModelAndView("addTasks");			
		//count++;
		//tasks.put(count,task);
		taskJdbcTemplate.insertTasks(task.getTaskSummary(), task.getPriority(), task.getDateOfCreation(),task.getTaskStatus());
		
		//System.out.println(task.getTaskSummary());
		return new ModelAndView("tasks","command", new Tasks());
	}
	
	@RequestMapping(value="/getTasks", method = RequestMethod.GET)
	public ModelAndView getStudent() {
		
		ModelAndView modelAndView = new ModelAndView("result");
		
		List<Tasks> taskList = taskJdbcTemplate.listTasks();
		
		modelAndView.addObject("tasks", taskList);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteTasks", method = RequestMethod.GET, params = {"id"})	
	public ModelAndView deleteStudent(@RequestParam(value="id") String id) {
		ModelAndView modelAndView = new ModelAndView("result");
		
		/*Integer idInt = Integer.parseInt(id);
		tasks.remove(idInt);
		modelAndView.addObject("tasks",tasks);*/
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.delete(idInt);
		List<Tasks> taskList = taskJdbcTemplate.listTasks();
		modelAndView.addObject("tasks",taskList);
		return modelAndView;		
	}
	
	@RequestMapping(value="/markAsComplete", method = RequestMethod.GET, params = {"id"})
	public ModelAndView markAsComplete(@RequestParam(value="id") String id) {
		ModelAndView modelAndView = new ModelAndView("result");
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.modifyStatus(idInt, TaskStatus.COMPLETED);
		List<Tasks> taskList = taskJdbcTemplate.listTasks();
		modelAndView.addObject("tasks",taskList);
		return modelAndView;
	}
	
	@RequestMapping(value="/markAsInProgress", method = RequestMethod.GET, params = {"id"})
	public ModelAndView markAsInProgress(@RequestParam(value="id") String id) {
		ModelAndView modelAndView = new ModelAndView("result");
		Integer idInt = Integer.parseInt(id);
		taskJdbcTemplate.modifyStatus(idInt, TaskStatus.INPROGRESS);
		List<Tasks> taskList = taskJdbcTemplate.listTasks();
		modelAndView.addObject("tasks",taskList);
		return modelAndView;
	}
}
