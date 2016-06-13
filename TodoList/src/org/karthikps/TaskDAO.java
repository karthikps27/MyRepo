package org.karthikps;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public interface TaskDAO {

	/*
	 * This method is to initialize the DB connection details
	 */
	public void setDataSource(DataSource ds);
	
	/*
	 * This method is to add tasks to DB.
	 */
	public void insertTasks(String taskSummary, Integer priority, String dateOfCreation, Integer taskStatus);
	
	/*
	 * This method is to fetch all the tasks.
	 */
	public List<Tasks> listTasks();
	
	/*
	 * This method is to delete the task based on the provided task ID.
	 */
	public void delete(Integer taskId);	
	
	/*
	 * This method is to modify the status of the task.
	 */

	public void modifyStatus(Integer taskId, Integer status);
}
