package org.karthikps;

import java.sql.Date;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.header.writers.frameoptions.AllowFromStrategy;
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
		String sql = "select * from tasklist where logged = '0' order by taskpriority DESC";
		List<Tasks> allTasks = jdbcTemplate.query(sql,new TaskMapper());
		return allTasks;
	}
	
	@Override
	public void insertTasks(String taskSummary, Integer priority, java.util.Date dateOfCreation, Integer taskStatus) {
		TransactionDefinition tdef = new DefaultTransactionDefinition();
		TransactionStatus tStatus = ptm.getTransaction(tdef);
		try {
			if(taskStatus == null)
				taskStatus=1;
			String sql = "insert into tasklist(tasksummary,taskpriority,taskCreateDate,taskStatus,logged) values (?,?,?,?,?)";
			Object[] params = new Object[] {taskSummary, priority, dateOfCreation, taskStatus,false};
			int[] types = new int[] {Types.VARCHAR, Types.INTEGER, Types.DATE, Types.INTEGER, Types.BOOLEAN};
			int row = jdbcTemplate.update(sql,params,types);
			ptm.commit(tStatus);
		}
		catch(Exception e) {
			ptm.rollback(tStatus);
		}
		return;
	}

	@Override
	public void modifyStatus(Integer taskId, Integer status,String comments) {
		TransactionDefinition tdef = new DefaultTransactionDefinition();
		TransactionStatus tStatus = ptm.getTransaction(tdef);
		try {
			String sql = "update tasklist set taskStatus = ? where id = ?";
			String commentsSql = "update tasklist set comments = ? where id = ?";
			jdbcTemplate.update(sql,status,taskId);
			jdbcTemplate.update(commentsSql,comments,taskId);
			ptm.commit(tStatus);
		}
		catch(Exception e) {
			ptm.rollback(tStatus);		
		}
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

	@Override
	public void modifyTaskDetails(Integer taskId, Integer priority, String summary, String comments) {
		TransactionDefinition tdef = new DefaultTransactionDefinition();
		TransactionStatus tStatus = ptm.getTransaction(tdef);
		try {
			String updateSummary= "update tasklist set taskSummary = ? where id = ?";
			String updatePriority = "update tasklist set taskPriority = ? where id = ?";
			String updateComment = "update tasklist set comments = ? where id = ?";
			jdbcTemplate.update(updateSummary,summary,taskId);
			jdbcTemplate.update(updatePriority,priority,taskId);
			jdbcTemplate.update(updateComment,comments,taskId);
			ptm.commit(tStatus);
		}
		catch(Exception e) {
			ptm.rollback(tStatus);		
		}
		return;
	}
}
