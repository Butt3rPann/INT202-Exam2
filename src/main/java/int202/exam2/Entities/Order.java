package int202.exam2.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "classicmodels")
public class Order {
    @Id
    @Column(name = "orderNumber", nullable = false)
    private Integer id;

    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "requiredDate", nullable = false)
    private LocalDate requiredDate;

    @Column(name = "shippedDate")
    private LocalDate shippedDate;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Lob
    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customerNumber;

//    @OneToMany(mappedBy = "order")
//    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

}