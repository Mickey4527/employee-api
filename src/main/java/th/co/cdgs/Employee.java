package th.co.cdgs;

import jakarta.inject.Named;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e ORDER BY e.id", 
                hints = @jakarta.persistence.QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Employee {
    
    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", 
                allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "employee_seq")

    private Integer id;

    @Column(name = "first_name", length = 100)
    private String firstname;

    @Column(name = "last_name", length = 100)
    private String lastname;

    @Column(name = "department", length = 100)
    private String department;

    @Column(name = "gender", length = 1)
    private String gender;

    public Employee() {
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getFirstName(){
        return firstname;
    }
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }
    public String getLastName(){
        return lastname;
    }
    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

}
