package view;

import controller.Controller;
import item.Student;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import java.util.ArrayList;

public class TableOfStudents extends Composite{
    private Table table;
    private Controller controller;
    private int maxStudentsOnPage = 10;
    private int pageNumber = 0;
    private Text numOfStudentsText;
    private Label pagesIndicatorLabel;

    private ArrayList<Student> searchStudents;

    public TableOfStudents(Composite parent, int style, Controller controller) {

        super(parent, style);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 8;
        gridLayout.marginTop = 10;
        gridLayout.marginBottom = 10;
        gridLayout.marginLeft = 5;
        gridLayout.marginRight = 5;
        this.setLayout(gridLayout);

        table = new Table(this, style);
        table.setHeaderVisible(true);
        String[] titles = {"Name", "Group",
                "Sickness Absence Number",
                "Other Reason Absence Number",
                "No Reason Absence Number",
        };

        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }

        GridData gridTable = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridTable.horizontalSpan = 8;
        table.setLayoutData(gridTable);

        this.controller = controller;

        Button firstPageButton = new Button(this, SWT.PUSH);
        firstPageButton.setText("<<");
        firstPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                pageNumber = 0;
                if (searchStudents.isEmpty()) {
                    updateTable();
                } else updateTable(searchStudents);
            }
        });

        Button previousPageButton = new Button(this, SWT.PUSH);
        previousPageButton.setText("<");
        previousPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (pageNumber - 1 >= 0) {
                    pageNumber--;
                    if (searchStudents.isEmpty()) {
                        updateTable();
                    } else updateTable(searchStudents);
                }
            }
        });

        Button nextPageButton = new Button(this, SWT.PUSH);
        nextPageButton.setText(">");
        nextPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (searchStudents.isEmpty()) {
                    if (pageNumber + 1 < Math.ceil((double) controller.getStudents().size() / maxStudentsOnPage)) {
                        pageNumber++;
                        updateTable();
                    }
                } else {
                    if (pageNumber + 1 < Math.ceil((double) searchStudents.size() / maxStudentsOnPage)) {
                        pageNumber++;
                        updateTable(searchStudents);
                    }
                }
            }
        });

        Button lastPageButton = new Button(this, SWT.PUSH);
        lastPageButton.setText(">>");
        lastPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (searchStudents.isEmpty()) {
                    pageNumber = (int) Math.ceil((double) controller.getStudents().size() / maxStudentsOnPage) - 1;
                    updateTable();
                } else {
                    pageNumber = (int) Math.ceil((double) searchStudents.size() / maxStudentsOnPage - 1);
                    updateTable(searchStudents);
                }
            }
        });

        Label numOfStudentsLabel = new Label(this, SWT.NONE);
        numOfStudentsLabel.setText("Students per page:");

        numOfStudentsText = new Text(this, SWT.NONE);
        numOfStudentsText.setText("1");

        searchStudents = new ArrayList<Student>();

        ModifyListener modifyListener = new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                try {
                    maxStudentsOnPage = Integer.parseInt(numOfStudentsText.getText());
                    pageNumber = 0;
                    if (searchStudents.isEmpty()) {
                        updateTable();
                    } else updateTable(searchStudents);
                } catch (Exception exception) {
                    exception.getStackTrace();
                }
            }
        };

        numOfStudentsText.addModifyListener(modifyListener);

        Label pagesIndicatorLabelName = new Label(this, SWT.NONE);
        pagesIndicatorLabelName.setText("Current page / All pages:");

        pagesIndicatorLabel = new Label(this, SWT.NONE);
        pagesIndicatorLabel.setText("1/1");

        this.pack();
        table.pack();
        super.pack();
    }

    private void fillTheTable(ArrayList<Student> students) {
        table.removeAll();
        for (Student student : students) {
            try {
                if (student.getName() == null)
                    throw new Exception("NULL student!");
            } catch (Exception ex) {
                System.exit(1);
            }

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, student.getName());
            item.setText(1, Integer.toString(student.getGroup()));
            item.setText(2, Integer.toString(student.getSicknessAbsenceCount()));
            item.setText(3, Integer.toString(student.getOtherReasonAbsenceCount()));
            item.setText(4, Integer.toString(student.getNoReasonAbsenceCount()));
        }
    }

    public void updateTable() {
        ArrayList<Student> students = getStudentPage(pageNumber, maxStudentsOnPage, controller.getStudents());
        fillTheTable(students);
        pagesIndicatorLabel.setText((pageNumber + 1) + "/" + (int) Math.ceil((double) controller.getStudents().size() / maxStudentsOnPage));
        table.pack();
        this.pack();
    }


    public void updateTable(ArrayList<Student> studentsList) {
        searchStudents = studentsList;
        ArrayList<Student> students = getStudentPage(pageNumber, maxStudentsOnPage, studentsList);
        fillTheTable(students);
        pagesIndicatorLabel.setText((pageNumber + 1) + "/" + (int) Math.ceil((double) searchStudents.size() / maxStudentsOnPage));
        table.pack();
        this.pack();
    }

    private ArrayList<Student> getStudentPage(int index, int numOfStudentsOnPage, ArrayList<Student> students) {
        ArrayList<ArrayList<Student>> pages = calculatePages(numOfStudentsOnPage, students);
        if (!pages.isEmpty()) {
            return pages.get(index);
        } else {
            ArrayList<Student> page = new ArrayList<Student>();
            return page;
        }
    }

    private ArrayList<ArrayList<Student>> calculatePages(int numOfStudentsOnPage, ArrayList<Student> students) {
        ArrayList<ArrayList<Student>> pages = new ArrayList<ArrayList<Student>>();
        int numOfPages = (int) Math.ceil((double) students.size() / numOfStudentsOnPage);

        for (int j = 0; j < numOfPages; j++) {
            ArrayList<Student> studentPage = new ArrayList<Student>();
            for (int i = numOfStudentsOnPage * j; i < numOfStudentsOnPage * j + numOfStudentsOnPage; i++) {
                try {
                    studentPage.add(students.get(i));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            pages.add(studentPage);
        }
        return pages;


    }

}