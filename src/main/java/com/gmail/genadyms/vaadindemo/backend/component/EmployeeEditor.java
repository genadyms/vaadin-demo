package com.gmail.genadyms.vaadindemo.backend.component;

import com.gmail.genadyms.vaadindemo.backend.entity.Employee;
import com.gmail.genadyms.vaadindemo.backend.repo.EmployeeRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class EmployeeEditor extends VerticalLayout implements KeyNotifier {
    private final EmployeeRepo employeeRepo;
    private Employee employee;

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField patronymic = new TextField("Patronymic");

    private Button save = new Button("save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("cancel");
    private Button delete = new Button("delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Employee> binder = new Binder<>(Employee.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public EmployeeEditor(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
        add(firstName, lastName, patronymic, actions);
        binder.bindInstanceFields(this);
        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> edit(employee));
        setVisible(false);
    }

    public void edit(Employee inputEmployee) {
        if (inputEmployee == null) {
            setVisible(false);
            return;
        }

        if (inputEmployee.getId() != null) {
            employee = employeeRepo.findById(inputEmployee.getId()).orElse(inputEmployee);
        } else {
            employee = inputEmployee;
        }

        binder.setBean(employee);
        setVisible(true);
        lastName.focus();
    }

    void delete() {
        employeeRepo.delete(employee);
        changeHandler.onChange();
    }

    void save() {
        employeeRepo.save(employee);
        changeHandler.onChange();
    }
}
