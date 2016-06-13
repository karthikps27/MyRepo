package org.karthikps;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskMapper implements RowMapper<Tasks> {

	@Override
	public Tasks mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Tasks task = new Tasks();
		task.setTaskId(rs.getInt("id"));
		task.setTaskSummary(rs.getString("taskSummary"));
		task.setPriority(rs.getInt("taskPriority"));
		task.setDateOfCreation(rs.getString("taskCreation"));
		task.setTaskStatus(rs.getInt("taskStatus"));
		return task;
	}

}
