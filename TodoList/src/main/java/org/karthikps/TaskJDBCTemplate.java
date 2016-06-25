package org.karthikps;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TaskJDBCTemplate implements TaskDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager ptm;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		ptm = transactionManager;
	}

	@Override
	public void delete(Integer taskId) {
		String sql = "delete from tasklist where id = ?";
		jdbcTemplate.update(sql,taskId);
		return;
	}

	@Override
	public List<Tasks> listTasks() {
		String sql = "select * from tasklist where logged = '0' order by taskpriority";
		List<Tasks> allTasks = jdbcTemplate.query(sql,new TaskMapper());
		return allTasks;
	}
	
	@Override
	public void insertTasks(String taskSummary, Integer priority, java.util.Date dateOfCreation, Integer taskStatus) {
		String sql = "insert into tasklist(tasksummary,taskpriority,taskCreateDate,taskStatus,logged) values (?,?,?,?,?)";
		jdbcTemplate.update(sql,taskSummary,priority,dateOfCreation,TaskStatus.CREATED,false);
		return;
	}

	@Override
	public void modifyStatus(Integer taskId, Integer status,String comments) {
		//System.out.println(status);
		String sql = "update tasklist set taskStatus = ? where id = ?";
		String commentsSql = "update tasklist set comments = ? where id = ?";
		jdbcTemplate.update(sql,status,taskId);
		jdbcTemplate.update(commentsSql,comments,taskId);
		return;
	}

	@Override
	public void moveTasksToLog(Integer taskId) {
		TransactionDefinition tdef = new DefaultTransactionDefinition();
		TransactionStatus tStatus = ptm.getTransaction(tdef);
		
		try{					
			String sqlupdate = "update tasklist set logged = ? where id = ?";
			
			jdbcTemplate.update(sqlupdate,true,taskId);
			
			ptm.commit(tStatus);
		}
		catch(DataAccessException dae){
			ptm.rollback(tStatus);
			dae.printStackTrace();
		}
		return;
	}

	@Override
	public List<Tasks> getAllLoggedTasks() {
		String sql = "select * from tasklist where logged = '1'";
		List<Tasks> taskList = jdbcTemplate.query(sql,new TaskMapper());
		return taskList;
	}

	@Override
	public List<Tasks> getQueriedTasks(java.util.Date fromDate, java.util.Date toDate, Integer priority) {
		String sql = "select * from tasklist where logged = '1' and taskpriority = ? and taskcreatedate between ? and ?";
		List<Tasks> tasks = jdbcTemplate.query(sql, new Object[]{priority,fromDate,toDate}, new TaskMapper());
		return tasks;
	}

	@Override
	public Tasks getTaskDetails(Integer taskId) {
		String sql = "select * from tasklist where id = ?";
		List<Tasks> tasks = jdbcTemplate.query(sql,new Object[] {taskId}, new TaskMapper());
		return tasks.get(0);
	}
	
}
