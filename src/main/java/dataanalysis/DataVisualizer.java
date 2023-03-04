package dataanalysis;

import java.util.Arrays;

import javax.swing.JFrame;

public class DataVisualizer {
    public DataVisualizer() {
        JFrame t = new JFrame();
        t.setDefaultCloseOperation(0);
        GraphPanel g = new GraphPanel(Arrays.asList(10.0, 20.0, 30.0, 40.0, 56.0, 10.0, 20.0, 30.0));
        t.setVisible(true);
        t.setSize(500, 500);
        t.add(g);
    }

    public static void main(String[] args) {
        new DataVisualizer();
    }
}
