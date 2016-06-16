package org.karthikps;

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
	public void insertTasks(String taskSummary, Integer priority, String dateOfCreation, Integer taskStatus) {
		String sql = "insert into tasklist(tasksummary,taskpriority,taskcreation,taskStatus,logged) values (?,?,?,?,?)";
		jdbcTemplate.update(sql,taskSummary,priority,dateOfCreation,TaskStatus.CREATED,false);
		return;
	}

	@Override
	public void modifyStatus(Integer taskId, Integer status) {
		//System.out.println(status);
		String sql = "update tasklist set taskStatus = ? where id = ?";
		jdbcTemplate.update(sql,status,taskId);
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
}
