package int202.exam2.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PaymentId implements Serializable {
    private static final long serialVersionUID = 5529912809039688483L;
    @Column(name = "customerNumber", nullable = false)
    private Integer customerNumber;

    @Column(name = "checkNumber", nullable = false, length = 50)
    private String checkNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentId entity = (PaymentId) o;
        return Objects.equals(this.checkNumber, entity.checkNumber) &&
                Objects.equals(this.customerNumber, entity.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkNumber, customerNumber);
    }

}