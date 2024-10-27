package int202.exam2.Repositories;

import int202.exam2.Entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, String> {
}
