
package za.ac.tut.entity;

import java.sql.Date;

/**
 *
 * @author sifiso
 */
public class Employee {
    
    private Integer idNo;
    private String name;
    private String surname;
    private Character gender;
    private Date dob;

    public Employee() {
    }

    public Employee(Integer idNo, String name, String surname, Character gender, Date dob) {
        this.idNo = idNo;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
    }

    public Integer getIdNo() {
        return idNo;
    }

    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Employee{" + "idNo=" + idNo + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", dob=" + dob + '}';
    }
    
    
}
