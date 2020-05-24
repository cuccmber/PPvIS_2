package menu;

import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class SaveStudentList implements Listener{

    private Shell parent;
    private Controller controller;

    public SaveStudentList(Shell parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }

    @Override
    public void handleEvent(Event e) {
        FileDialog fDialog = new FileDialog(parent, SWT.SAVE);
        fDialog.setFileName("new_list.xml");
        fDialog.setFilterExtensions(new String[]{"*.xml"});
        String outputFile = fDialog.open();
        controller.save(outputFile);
    }

}
