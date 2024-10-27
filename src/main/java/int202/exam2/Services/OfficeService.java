package int202.exam2.Services;

import int202.exam2.Entities.Office;
import int202.exam2.Repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OfficeService {
    public final OfficeRepository repository;

    public OfficeService(OfficeRepository repository) {
        this.repository = repository;
    }

    public Office getOffice(String officeCode) {
        return repository.findById(officeCode).orElse(null);
    }

    public List<Office> getAllOffices() {
        return repository.findAll();
    }

    public Office addOffice(Office office) {
        if (office.getOfficeCode() == null || repository.existsById(office.getOfficeCode()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Office id %d already exist", office.getOfficeCode()));
        return repository.save(office);
    }

    public Office delateOfficeById(String officeCode) {
        Office office = getOffice(officeCode);
        if(office == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't delete, Office id %s doesn't exists", officeCode));
        repository.deleteById(officeCode);
        return office;
    }

    public Office updateOffice(Office office) {
        if(office == null || !repository.existsById(office.getOfficeCode()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't update, Office id %s does not exists", office.getOfficeCode()));
        return repository.save(office);
    }
}
