package medved.java.springsecurity.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
public class BasePerson implements Serializable {
    private String name;
    private String surname;
    private int age;
}
