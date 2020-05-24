package menu;

import controller.Controller;
import item.Student;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import view.TableOfStudents;
import java.util.ArrayList;

public class SearchStudent implements Listener{

    private Controller controller;
    private Shell parent;

    public SearchStudent(Shell parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent, SWT.SHELL_TRIM | SWT.RESIZE);
        child.setText("Search...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label nameLabel = new Label(child, SWT.NONE);
        nameLabel.setText("Student's name:");

        Text nameText = new Text(child, SWT.NONE);
        nameText.setLayoutData(new RowData(200, 20));

        Button nameCheck = new Button(child, SWT.CHECK);
        nameCheck.setText("Search by name");

        Label groupNumberLabel = new Label(child, SWT.NONE);
        groupNumberLabel.setText("Group number:");

        Text groupNumberText = new Text(child, SWT.NONE);
        groupNumberText.setLayoutData(new RowData(200, 20));

        Button groupNumberCheck = new Button(child, SWT.CHECK);
        groupNumberCheck.setText("Search by group number");

        Label sicknessAbsenceLabel = new Label(child, SWT.NONE);
        sicknessAbsenceLabel.setText("Sickness absence number:");

        Text sicknessAbsenceText = new Text(child, SWT.NONE);
        sicknessAbsenceText.setLayoutData(new RowData(200, 20));

        Button sicknessAbsenceCheck = new Button(child, SWT.CHECK);
        sicknessAbsenceCheck.setText("Search by sickness absence number");

        Label otherReasonAbsenceLabel = new Label(child, SWT.NONE);
        otherReasonAbsenceLabel.setText("Other reason absence number:");

        Text otherReasonAbsenceText = new Text(child, SWT.NONE);
        otherReasonAbsenceText.setLayoutData(new RowData(200, 20));

        Button otherReasonAbsenceCheck = new Button(child, SWT.CHECK);
        otherReasonAbsenceCheck.setText("Search by other reason absence number");

        Label noReasonAbsenceLabel = new Label(child, SWT.NONE);
        noReasonAbsenceLabel.setText("No reason absence number:");

        Text noReasonAbsenceText = new Text(child, SWT.NONE);
        noReasonAbsenceText.setLayoutData(new RowData(200, 20));

        Button noReasonAbsenceCheck = new Button(child, SWT.CHECK);
        noReasonAbsenceCheck.setText("Search by no reason absence number");

        Label totalAbsenceLabel = new Label(child, SWT.NONE);
        totalAbsenceLabel.setText("Total absence number:");

        Text totalAbsenceText = new Text(child, SWT.NONE);
        totalAbsenceText.setLayoutData(new RowData(200, 20));

        Button totalAbsenceCheck = new Button(child, SWT.CHECK);
        totalAbsenceCheck.setText("Search by total absence number");

        Button proceedButton = new Button(child, SWT.PUSH);
        proceedButton.setText("Search");

        TableOfStudents table = new TableOfStudents(child, SWT.NONE, controller);

        Listener proceedListener = new Listener() {

            @Override
            public void handleEvent(Event e) {
                ArrayList<Student> students = new ArrayList<Student>();

                if (nameCheck.getSelection()) {
                    if(!nameText.getText().isEmpty()) {
                        String name = nameText.getText();
                        students.addAll(controller.findByName(name, controller.getStudents()));
                    }
                }

                if (groupNumberCheck.getSelection()) {
                    if(!groupNumberText.getText().isEmpty()) {
                        int group = Integer.parseInt(groupNumberText.getText());
                        students.addAll(controller.findByGroup(group, controller.getStudents()));
                    }
                }

                if (sicknessAbsenceCheck.getSelection()) {
                    if(!sicknessAbsenceText.getText().isEmpty()) {
                        int sickness = Integer.parseInt(sicknessAbsenceText.getText());
                        students.addAll(controller.findBySicknessAbsence(sickness, controller.getStudents()));
                    }
                }

                if (otherReasonAbsenceCheck.getSelection()) {
                    if(!otherReasonAbsenceText.getText().isEmpty()) {
                        int other = Integer.parseInt(otherReasonAbsenceText.getText());
                        students.addAll(controller.findByOtherReasonAbsence(other, controller.getStudents()));
                    }
                }

                if (noReasonAbsenceCheck.getSelection()) {
                    if(!noReasonAbsenceText.getText().isEmpty()) {
                        int none = Integer.parseInt(noReasonAbsenceText.getText());
                        students.addAll(controller.findByNoReasonAbsence(none, controller.getStudents()));
                    }
                }

                if (totalAbsenceCheck.getSelection()) {
                    if(!totalAbsenceText.getText().isEmpty()) {
                        int absence = Integer.parseInt(totalAbsenceText.getText());
                        students.addAll(controller.findByTotalAbsence(absence, controller.getStudents()));
                    }
                }
                table.updateTable(students);
            }
        };

        proceedButton.addListener(SWT.MouseDown, proceedListener);

        child.open();
    }
}
