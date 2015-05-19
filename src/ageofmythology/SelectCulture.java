/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author vipinsharma
 */
public class SelectCulture implements ActionListener{
    private JFrame culture = new JFrame("Culture Selection");
    private String[] cultures = {"Greek", "Norse", "Egypt"};
    private int cultureChosenIndex;
    
    public SelectCulture()
    {
        culture = new JFrame("Culture Selection");
        culture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        culture.setLayout(new FlowLayout());

        JButton greek = new JButton("Greek");
        greek.setActionCommand(Constants.CULTURE_GREEK);
        greek.addActionListener(this);

        JButton norse = new JButton("Norse");
        norse.setActionCommand(Constants.CULTURE_NORSE);
        norse.addActionListener(this);

        JButton egypt = new JButton("Egypt");
        egypt.setActionCommand(Constants.CULTURE_EGYPT);
        egypt.addActionListener(this);

        culture.add(greek);
        culture.add(norse);
        culture.add(egypt);

        culture.pack();
        
        culture.setVisible(true);
        culture.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("button clicked");
        System.out.println(e.getActionCommand());
        String cultureChosen = e.getActionCommand();

//        if (check) {
//            check = false;
            for (int i = 0; i < 3; i++) {
                if (cultureChosen.equals(cultures[i])) {
                    cultureChosenIndex = i;
                    break;
                }
            }
            //System.out.println("Culture index : " + cultureChosenIndex);
            System.out.println("Culture chosen : " + cultures[cultureChosenIndex]);
            culture.setVisible(false);
        culture.dispose();
        AgeOfMythology.setPlayersInOrder(cultureChosen,cultureChosenIndex);
//        }

//        int start = cpIndex;
//        for (int i = 0; i < 3; i++) {
//            playerShift[i] = start;
//            start++;
//            if (start > 2) {
//                start = 0;
//            }
//        }
//        
//        intializeBoards(e.getActionCommand());
    }
    
    public int returnCultureIndex()
    {
        //System.out.println("return value : " + cultureChosenIndex);
        return cultureChosenIndex;
    }
}
