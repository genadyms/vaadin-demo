package com.gmail.genadyms.vaadindemo.view;

import com.gmail.genadyms.vaadindemo.repo.EmployeeRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public MainView(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }
}