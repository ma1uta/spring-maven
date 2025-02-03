package io.github.ma1uta.demo.service;

import io.github.ma1uta.demo.model.Address;
import io.github.ma1uta.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployees() {
        return List.of(
            new Employee(
                1L,
                "name1",
                "surname1",
                List.of(
                    new Address(
                    1L,
                    "street1",
                    "city1",
                    "state1",
                    "zip1",
                    "country1"
                    ),
                    new Address(
                        2L,
                        "street2",
                        "city2",
                        "state2",
                        "zip2",
                        "country2"
                    )
                )
            ),
            new Employee(
                2L,
                "name2",
                "surname2",
                List.of(
                    new Address(
                        3L,
                        "street3",
                        "city3",
                        "state3",
                        "zip3",
                        "country3"
                    ),
                    new Address(
                        4L,
                        "street4",
                        "city4",
                        "state4",
                        "zip4",
                        "country4"
                    )
                )
            )
        );
    }
}
