package int202.exam2.Repositories;

import int202.exam2.Entities.Productline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductlineRepository extends JpaRepository<Productline, String> {
    @Query("SELECT p.productLine FROM Productline p")
    List<String> findAllProductLine();
}