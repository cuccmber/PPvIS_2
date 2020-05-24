package menu;

import controller.Controller;
import item.Student;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import view.Window;
import java.util.ArrayList;

public class DeleteStudent implements Listener {

    private Controller controller;
    private Window window;
    private Shell parent;

    public DeleteStudent(Shell parent, Controller controller, Window window) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent, SWT.SHELL_TRIM | SWT.RESIZE);
        child.setText("Deletion...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label nameLabel = new Label(child, SWT.NONE);
        nameLabel.setText("Input student's name:");

        Text nameText = new Text(child, SWT.NONE);
        nameText.setLayoutData(new RowData(200, 20));

        Button nameCheck = new Button(child, SWT.CHECK);
        nameCheck.setText("Delete by student's name");

        Label groupNumberLabel = new Label(child, SWT.NONE);
        groupNumberLabel.setText("Input group number:");

        Text groupNumberText = new Text(child, SWT.NONE);
        groupNumberText.setLayoutData(new RowData(200, 20));

        Button groupNumberCheck = new Button(child, SWT.CHECK);
        groupNumberCheck.setText("Delete by group number");

        Label sicknessAbsenceLabel = new Label(child, SWT.NONE);
        sicknessAbsenceLabel.setText("Input sickness absence number:");

        Text sicknessAbsenceText = new Text(child, SWT.NONE);
        sicknessAbsenceText.setLayoutData(new RowData(200, 20));

        Button sicknessAbsenceSearchCheck = new Button(child, SWT.CHECK);
        sicknessAbsenceSearchCheck.setText("Delete by sickness absence number");

        Label otherReasonAbsenceLabel = new Label(child, SWT.NONE);
        otherReasonAbsenceLabel.setText("Input other reason absence number:");

        Text otherReasonAbsenceText = new Text(child, SWT.NONE);
        otherReasonAbsenceText.setLayoutData(new RowData(200, 20));

        Button otherReasonAbsenceCheck = new Button(child, SWT.CHECK);
        otherReasonAbsenceCheck.setText("Delete by other reason absence number");

        Label noReasonAbsenceLabel = new Label(child, SWT.NONE);
        noReasonAbsenceLabel.setText("Input no reason absence number:");

        Text noReasonAbsenceText = new Text(child, SWT.NONE);
        noReasonAbsenceText.setLayoutData(new RowData(200, 20));

        Button noReasonAbsenceCheck = new Button(child, SWT.CHECK);
        noReasonAbsenceCheck.setText("Delete by no reason absence number");

        Label totalAbsenceLabel = new Label(child, SWT.NONE);
        totalAbsenceLabel.setText("Input total absence number:");

        Text totalAbsenceText = new Text(child, SWT.NONE);
        totalAbsenceText.setLayoutData(new RowData(200, 20));

        Button totalAbsenceCheck = new Button(child, SWT.CHECK);
        totalAbsenceCheck.setText("Delete by total absence number");

        Button proceedButton = new Button(child, SWT.NONE);
        proceedButton.setText("Delete");

        Listener proceedListener = new Listener() {
            @Override
            public void handleEvent(Event e) {

                ArrayList<Student> students = new ArrayList<>();

                if (nameCheck.getSelection()) {
                    if(!nameText.getText().isEmpty()) {
                        String name = nameText.getText();
                        students = controller.findByName(name, controller.getStudents());
                    }
                }

                if (groupNumberCheck.getSelection()) {
                    if(!groupNumberText.getText().isEmpty()) {
                        int group = Integer.parseInt(groupNumberText.getText());
                        students.addAll(controller.findByGroup(group, controller.getStudents()));
                    }
                }

                if (sicknessAbsenceSearchCheck.getSelection()) {
                    if(!sicknessAbsenceText.getText().isEmpty()) {
                        int sickness = Integer.parseInt(sicknessAbsenceText.getText());
                        students.addAll(controller.findBySicknessAbsence(sickness, controller.getStudents()));
                    }
                }

                if (otherReasonAbsenceCheck.getSelection()) {
                    if(!otherReasonAbsenceCheck.getText().isEmpty()) {
                        int other = Integer.parseInt(otherReasonAbsenceCheck.getText());
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

                Shell dialog = new Shell(child);
                dialog.setText("Deletion message");
                dialog.setLayout(new FillLayout());
                Label resultLabel = new Label(dialog, SWT.NONE);
                if (students.size() != 0) {
                    controller.deleteStudent(students);
                    window.updateTable();
                    resultLabel.setText("Students deleted: " + students.size());
                } else resultLabel.setText("No match!");
                dialog.pack();
                dialog.open();
            }

        };
        proceedButton.addListener(SWT.MouseDown, proceedListener);
        child.pack();
        child.open();
    }
}
