package com.gmail.genadyms.vaadindemo.view;

import com.gmail.genadyms.vaadindemo.component.EmployeeEditor;
import com.gmail.genadyms.vaadindemo.entity.Employee;
import com.gmail.genadyms.vaadindemo.repo.EmployeeRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final EmployeeRepo employeeRepo;
    private Grid<Employee> grid = new Grid<>(Employee.class);

    private final TextField filter = new TextField("", "Type to filter");
    private final Button addEmployee = new Button("Add new");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addEmployee);

    private final EmployeeEditor editor;

    @Autowired
    public MainView(EmployeeRepo employeeRepo, EmployeeEditor editor) {
        this.employeeRepo = employeeRepo;
        this.editor = editor;
        add(toolbar, grid, editor);


        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> showEmployee(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.edit(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addEmployee.addClickListener(e -> editor.edit(new Employee()));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            showEmployee(filter.getValue());
        });

        showEmployee("");
    }

    private void showEmployee(String name) {
        if (name.isEmpty()) {
            grid.setItems(employeeRepo.findAll());
        } else {
            grid.setItems(employeeRepo.findByName(name));
        }
    }
}