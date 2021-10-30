package model;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Employee> employees;
    private Connection con;

    public Database() {employees = new LinkedList<Employee>();}

    private void addEmployee(Employee person) {
        employees.add(person);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void connect() throws SQLException {
        if (con!=null) return;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:sqlite:db/stuff.db";
        con = DriverManager.getConnection(url);
    }

    public void disconnect () {
        if (con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void count() throws SQLException {
        String sql = "SELECT count(*) FROM employee";
        PreparedStatement sqlStmt = con.prepareStatement(sql);
        ResultSet checkResult = sqlStmt.executeQuery();

        checkResult.next();
        int count = checkResult.getInt(1);

        System.out.println("Count for person is " + count + ".");

        sqlStmt.close();
    }

    public void getFromDatabase() {
        employees.clear();

        String sql = "select * from employee";

        try {
            PreparedStatement sqlStmt = con.prepareStatement(sql);
            ResultSet set = sqlStmt.executeQuery();
            while (set.next()) {
                Employee temp = new Employee(set.getString("name"), set.getString("department"),
                    set.getInt("age"), set.getString("phone"),
                    set.getString("position"), set.getString("gender"));

                addEmployee(temp);
            }

            sqlStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeToDatabase(Employee employee) {
        String name = employee.getName();
        int age = employee.getAge();
        String phone = employee.getPhone();
        String gender = employee.getGender();
        String position = employee.getPosition();
        String department = employee.getDepartment();

        String sql = "insert into employee(name, department, age, phone, position, gender) values ('"+name+"','"+department+"','"+age+"','"+phone+"','"+position+"','"+gender+"')";
        System.out.println("SQL = " + sql);

        try {
            PreparedStatement sqlStmt = con.prepareStatement(sql);
            sqlStmt.execute();
            sqlStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getFromDatabase();
    }

}

