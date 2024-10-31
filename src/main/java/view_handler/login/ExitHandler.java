package main.java.view_handler.login;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.LoginText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitHandler extends ActionHandler {

    public ExitHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (AppController controller: controllers) {
            controller.save();
        }

        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        CommonText commonText = new CommonText();
        Object[] options = {commonText.getYes(), commonText.getNo()};
        int option = JOptionPane.showOptionDialog(appFrame,
                new LoginText().getConfirmExitQuestion(),
                commonText.getExit(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
