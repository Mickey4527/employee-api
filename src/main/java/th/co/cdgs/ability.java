package th.co.cdgs;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "ability")
@NamedQuery(name = "ability.findAll", query = "SELECT a FROM ability a ORDER BY a.id", 
                hints = @jakarta.persistence.QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable                
public class ability {

    @Id
    @SequenceGenerator(name = "ability_seq", sequenceName = "ability_seq", 
                allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "ability_seq")

    private Integer id;

    @Column(name = "power", length = 100)
    private String power;

    @OneToOne(fetch = FetchType.LAZY) // ใช้ LAZY จะไม่ดึงข้อมูลมาทั้งหมด
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;

    public ability() {
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getPower(){
        return power;
    }
    public void setPower(String power){
        this.power = power;
    }

    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    
}
