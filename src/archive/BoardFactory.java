/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

import archive.NorseBoard;
import archive.Board;
import archive.EgyptBoard;
import archive.GreekBoard;

/**
 *
 * @author vipinsharma
 */
public class BoardFactory {
    public static Board returnBoard(String culture){
        switch(culture){
            case "1":
                return new GreekBoard();
            
            case "2":
                return new NorseBoard();
                
            case "3":
                return new EgyptBoard();            
        }
        
        return null;
    }
}
