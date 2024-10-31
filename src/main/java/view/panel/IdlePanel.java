package main.java.view.panel;

import main.java.text.AccountText;
import main.java.view_handler.ActionHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The welcome panel that displays a picture, username, last login time.
 */
public class IdlePanel extends SwingPanel {

    /**
     * @param actionHandlers handlers for this panel
     * @param username username displayed on the screen
     * @param loginTime last login time
     */
    public IdlePanel(ActionHandler[] actionHandlers, String username, String loginTime) {
        super(actionHandlers);

        JTextArea userInfoText = new JTextArea(String.format(
                new AccountText().getUserInfoTemplate(), username, loginTime));

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(new Insets(100, 10, 300, 10)));
        panel.add(userInfoText);

        try {
            String[] imgPathNames = {"src", "main", "resource", "idle_panel_place_holder.jpg"};
            String imgPath = String.join(File.separator, imgPathNames);
            BufferedImage img = ImageIO.read(new File(imgPath));
            JLabel pic = new JLabel(new ImageIcon(img));
            JPanel picPanel = new JPanel();
            picPanel.add(pic);
            panel.add(picPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(panel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        setHeader(userInfoText);
    }
}
