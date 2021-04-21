package view;

import controller.Controller;
import load.SAXLoader;
import menu.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class Window {
    private Controller controller;
    private TableOfStudents tableOfStudents;
    private Display display = new Display();
    private Shell shell = new Shell();

    public Window(Controller controller){
        this.controller = controller;
    }

    public void show(){

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 5;
        gridLayout.marginTop = 10;
        gridLayout.marginBottom = 10;
        gridLayout.marginLeft = 5;
        gridLayout.marginRight = 5;
        shell.setLayout(gridLayout);
        shell.setText("Lab 2, task 3");

        GridData gridButton = new GridData(148, 50);

        Button saveButton = new Button(shell, SWT.PUSH);
        saveButton.setText("Save");
        saveButton.setLayoutData(gridButton);
        SaveStudentList saveStudentList = new SaveStudentList(shell, controller);
        saveButton.addListener(SWT.MouseDown, saveStudentList);

        SAXLoader loader = new SAXLoader();

        Button loadButton = new Button(shell, SWT.PUSH);
        loadButton.setText("Load");
        loadButton.setLayoutData(gridButton);
        LoadStudentList loadStudent = new LoadStudentList(shell, this, controller);
        loadButton.addListener(SWT.MouseDown, loadStudent);


        Button addButton = new Button(shell, SWT.PUSH);
        addButton.setText("Add");
        addButton.setLayoutData(gridButton);
        AddStudent addStudent = new AddStudent(shell, controller, this);
        addButton.addListener(SWT.MouseDown, addStudent);

        Button searchButton = new Button(shell, SWT.PUSH);
        searchButton.setText("Search");
        searchButton.setLayoutData(gridButton);
        SearchStudent searchStudent = new SearchStudent(shell, controller);
        searchButton.addListener(SWT.MouseDown, searchStudent);

        Button deleteButton = new Button(shell, SWT.PUSH);
        deleteButton.setText("Delete");
        deleteButton.setLayoutData(gridButton);
        DeleteStudent deleteStudent = new DeleteStudent(shell, controller, this);
        deleteButton.addListener(SWT.MouseDown, deleteStudent);

        tableOfStudents = new TableOfStudents(shell, SWT.NONE, controller);
        GridData gridTable = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridTable.horizontalSpan = 5;
        tableOfStudents.setLayoutData(gridTable);

        //shell.pack();
        shell.setBounds(500, 200, 800, 600);
        shell.open();
        while(!shell.isDisposed()) {
            if(!display.readAndDispatch()) display.sleep();
        }
    }

    public void updateTable() {
        tableOfStudents.updateTable();
    }
}
