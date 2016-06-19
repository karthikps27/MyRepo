package org.karthikps;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.transaction.PlatformTransactionManager;

public interface TaskDAO {

	/*
	 * This method is to initialize the DB connection details
	 */
	public void setDataSource(DataSource ds);
	
	/*
	 * This is to initialize transaction
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager);
	
	/*
	 * This method is to add tasks to DB.
	 */
	public void insertTasks(String taskSummary, Integer priority, Date dateOfCreation, Integer taskStatus);
	
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
	
	/*
	 * This method is used to move the completed tasks to the log
	 */
	public void moveTasksToLog(Integer taskId);
	
	/*
	 * Access the logged tasks
	 */
	public List<Tasks> getAllLoggedTasks();
	
	/*
	 * Access Querried tasks
	 */
	public List<Tasks> getQueriedTasks(Date fromDate, Date toDate, Integer priority);
}
