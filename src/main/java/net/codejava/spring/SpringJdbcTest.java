package net.codejava.spring;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class SpringJdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcURL = "jdbc:mysql://localhost:3306";
		String username = "root";
		String pass = "0607032162";
		
		try {
			Connection jdbcConnect = DriverManager.getConnection(jdbcURL,username,pass);
			
			if(jdbcConnect != null) {
				System.out.println("Succesfully connected to databse");
				//Creating Employees table
				List<String> sqlExec = new ArrayList<String>();
				sqlExec.add("DROP DATABASE IF EXISTS testDB");
				sqlExec.add("CREATE DATABASE testDB");
				sqlExec.add("USE testDB");
				sqlExec.add("CREATE TABLE employee("
						+ "employeeID int not null,"
						+ "lastName varchar(255),"
						+ "firstName varchar(255),"
						+ "deptID int not null,"
						+ "primary key(employeeID))");
				System.out.println("Succesfully created employee table");
				sqlExec.add("CREATE TABLE department("
						+ "deptID int not null,"
						+ "departmentName varChar(255),"
						+ "primary key(deptID))");
				System.out.println("Succesfully created department table");
				//Adding dummy employee entries
				sqlExec.add("INSERT INTO employee(employeeID, lastName, firstName, deptID)"
						+ "values (1,\"Most\",\"Max\",1)");
				sqlExec.add("INSERT INTO employee(employeeID, lastName, firstName, deptID)"
						+ "values (2,\"Smith\",\"Mike\",1)");
				sqlExec.add("INSERT INTO employee(employeeID, lastName, firstName, deptID)"
						+ "values (3,\"Doe\",\"John\",1)");
				sqlExec.add("INSERT INTO employee(employeeID, lastName, firstName, deptID)"
						+ "values (4,\"Carlos\",\"Janny\",2)");
				//Adding dummy departments
				sqlExec.add("INSERT INTO department(deptID,departmentName)"
						+ "values (1,\"Math\")");
				sqlExec.add("INSERT INTO department(deptID,departmentName)"
						+ "values (2,\"Art\")");
				//Joining the tables together
				String query = "SELECT * FROM testdb.employee INNER JOIN testdb.department ON employee.deptID=department.deptID";
				Statement statement = jdbcConnect.createStatement();
				for(String i: sqlExec)	statement.executeUpdate(i);
				ResultSet result = statement.executeQuery(query);
				while(result.next()) System.out.println(result.getString(2)+" "+result.getString(3)+" "+result.getString(6));
				
				jdbcConnect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
