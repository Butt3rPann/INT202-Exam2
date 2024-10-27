package int202.exam2.Services;

import int202.exam2.Entities.Employee;
import int202.exam2.Repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    public final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Page<Employee> getEmployee(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Employee getEmployeeById(int employeeNumber) {
        return repository.findById(employeeNumber).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        if (employee == null || repository.existsById(employee.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't create, employee id %s already exists", employee.getId()));
        return repository.save(employee);
    }

    public Employee deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);
        if(employee == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't delete, Office id %s doesn't exists", id));
        repository.deleteById(id);
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        if (employee == null || !repository.existsById(employee.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't update");
        return repository.save(employee);
    }
}
