package GUI;

import javax.swing.*;

public class Estresamiento {
    public static void main(String[] args){
        JFrame[] arrFrame = new JFrame[50];

        for (int i = 0; i<50; i++) {
            //arrFrame[i] = new JFrame("Pegale" + i);
             String name = "user" + i;
            //arrFrame[i].setContentPane(new Pegale(name).getPanelMain());
           // arrFrame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //arrFrame[i].pack();
            //arrFrame[i].setLocation(50, 350);
            //arrFrame[i].setVisible(true);
            Pegale p = new Pegale(name);
            p.start();
        }

    }

}
