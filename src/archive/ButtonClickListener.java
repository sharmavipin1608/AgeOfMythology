/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

import archive.Board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author vipinsharma
 */
public class ButtonClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button clicked");
            System.out.println(e.getActionCommand());
            Board board = BoardFactory.returnBoard(e.getActionCommand());
            
            //board.initializeBoard();
            
            //culture.setVisible(false);
        }
        
    }
