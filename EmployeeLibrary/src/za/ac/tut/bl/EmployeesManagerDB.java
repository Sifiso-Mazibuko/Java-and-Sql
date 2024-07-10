
package za.ac.tut.bl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.tut.entity.Employee;

/**
 *
 * @author sifiso
 */
public class EmployeesManagerDB implements DAOInterface<Employee>{
    private Connection connection;

    public EmployeesManagerDB(String dbURL, String username, String password) throws SQLException {
    
        connection = getConnection(dbURL, username, password);
    }

    @Override
    public Boolean add(Employee t) throws SQLException {
        String sql = "INSERT INTO EMPLOYEESTBL(IDNO, NAME, SURNAME, GENDER, DOB)" +
                "VALUES(?,?,?,?,?)";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, t.getIdNo());
        ps.setString(2, t.getName());
        ps.setString(3, t.getSurname());
        ps.setString(4, t.getGender().toString());
        ps.setDate(5, t.getDob());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public Boolean delete(Integer ID) throws SQLException {
        
        String sql = "DELETE FROM EMPLOYEESTBL " +
            "WHERE IDNO=?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ID);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public Boolean update(Employee t) throws SQLException {
        
        String sql = "UPDATE EMPLOYEESTBL SET "+
            "NAME =? "+
            "WHERE IDNO=?";
        
        try{
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, t.getName());
            ps.setInt(2, t.getIdNo());
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException ex){
            System.err.println(ex);
            return false;
        }
        
        return false;
    }

    @Override
    public Employee get(Integer Id) throws SQLException {
        
        String sql = "SELECT IDNO, NAME, SURNAME, GENDER, DOB "+
            "FROM EMPLOYEESTBL "+
            "WHERE IDNO=?";
        
        try{
        
            PreparedStatement ps = connection.prepareStatement(sql);
        
            //restriction
            ps.setInt(1, Id);

            ResultSet rs = ps.executeQuery();

            //the rest of the select 
            if(rs.next()){

                Integer idNo = rs.getInt("IDNO");
                String name = rs.getString("NAME");
                String surname = rs.getString("SURNAME");
                Character gender = rs.getString("GENDER").charAt(0);
                Date dob = rs.getDate("DOB");

                Employee emp = new Employee(idNo, name, surname, gender, dob);
                ps.close();
                return emp;
            }
            else{
                ps.close();
                return null;
            }
        }
        catch(SQLException ex){
        
            System.err.println(ex);
            return null;
        }
        
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        
        List<Employee> employees = new ArrayList<>();
        
        String sql = "SELECT * FROM EMPLOYEESTBL";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
        
            Integer idNo = rs.getInt("IDNO");
            String name = rs.getString("NAME");
            String surname = rs.getString("SURNAME");
            Character gender = rs.getString("GENDER").charAt(0);
            Date dob = rs.getDate("DOB");
            
            Employee emp = new Employee(idNo, name, surname, gender, dob);
            employees.add(emp);
        }
        
        ps.close();
        return employees;
    }
    
    private Connection getConnection(String dbURL, String username, String password) throws SQLException {
        Connection theConnection;
        
        theConnection = DriverManager.getConnection(dbURL, username, password);
        
        return theConnection;
    }
    
}
