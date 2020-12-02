package com.gmail.genadyms.vaadindemo.view;

import com.gmail.genadyms.vaadindemo.entity.Employee;
import com.gmail.genadyms.vaadindemo.repo.EmployeeRepo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final EmployeeRepo employeeRepo;

    private Grid<Employee> grid = new Grid<>(Employee.class);

    @Autowired
    public MainView(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
        add(grid);
        grid.setItems(employeeRepo.findAll());
    }
}