package menu;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import view.Window;

public class AddStudent implements Listener {
    private Controller controller;
    private Window window;
    private Shell parent;

    public AddStudent(Shell parent, Controller controller, Window window) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent);
        child.setText("Adding...");

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

        Label groupLabel = new Label(child, SWT.NONE);
        groupLabel.setText("Input group number:");

        Text groupText = new Text(child, SWT.NONE);
        groupText.setLayoutData(new RowData(200, 20));

        Label sicknessLabel = new Label(child, SWT.NONE);
        sicknessLabel.setText("Input sickness absence:");

        Text sicknessNext = new Text(child, SWT.NONE);
        sicknessNext.setLayoutData(new RowData(200, 20));

        Label otherLabel = new Label(child, SWT.NONE);
        otherLabel.setText("Input other reason absence:");

        Text otherText = new Text(child, SWT.NONE);
        otherText.setLayoutData(new RowData(200, 20));

        Label noLabel = new Label(child, SWT.NONE);
        noLabel.setText("Input no reason absence:");

        Text noText = new Text(child, SWT.NONE);
        noText.setLayoutData(new RowData(200, 20));

        Button proceedButton = new Button(child, SWT.PUSH);
        proceedButton.setText("Add");
        proceedButton.setLayoutData(new RowData(100, 30));

        Listener proceedListener = new Listener() {

            @Override
            public void handleEvent(Event arg0) {

                String stName = nameText.getText();
                int stGroup = Integer.parseInt(groupText.getText());
                int stSickness = Integer.parseInt(sicknessNext.getText());
                int stOther = Integer.parseInt(otherText.getText());
                int stNone = Integer.parseInt(noText.getText());

                controller.addStudent(stName,
                        stGroup,
                        stSickness,
                        stOther,
                        stNone);
                window.updateTable();
                child.dispose();
            }

        };
        proceedButton.addListener(SWT.MouseDown, proceedListener);

        child.pack();
        child.open();
    }

}
