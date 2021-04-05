package com.vaadin.swrpgd6.app.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("test")
public class TestView extends VerticalLayout
{
    public TestView()
    {

        Button button = new Button("Character Form");
        add(button);
    }

}
