package org.karthikps;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TaskJDBCTemplate implements TaskDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void delete(Integer taskId) {
		String sql = "delete from tasklist where id = ?";
		jdbcTemplate.update(sql,taskId);
		return;
	}

	@Override
	public List<Tasks> listTasks() {
		String sql = "select * from tasklist";
		List<Tasks> allTasks = jdbcTemplate.query(sql,new TaskMapper());
		return allTasks;
	}

	@Override
	public void insertTasks(String taskSummary, Integer priority, String dateOfCreation, Integer taskStatus) {
		String sql = "insert into tasklist(tasksummary,taskpriority,taskcreation,taskStatus) values (?,?,?,?)";
		jdbcTemplate.update(sql,taskSummary,priority,dateOfCreation,TaskStatus.CREATED);
		return;
	}

	@Override
	public void modifyStatus(Integer taskId, Integer status) {
		System.out.println(status);
		String sql = "update tasklist set taskStatus = ? where id = ?";
		jdbcTemplate.update(sql,status,taskId);
		return;
	}

}
