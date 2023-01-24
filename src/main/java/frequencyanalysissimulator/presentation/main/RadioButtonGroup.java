package frequencyanalysissimulator.presentation.main;

import javax.swing.*;
import java.awt.*;

class RadioButtonGroup {
    private JPanel panel;
    private ButtonGroup group;

    RadioButtonGroup() {
        panel = new JPanel(new FlowLayout());
        group = new ButtonGroup();
    }

    JPanel getPanel() {
        return panel;
    }

    JRadioButton addOption(String name, Action a, String description) {
        JRadioButton b = new JRadioButton(name);
        b.setAction(a);
        b.setToolTipText(description);

        group.add(b);
        panel.add(b);

        return b;
    }

    String getSelection() {
        return ((JRadioButton) group.getSelection()).getText();
    }
}
