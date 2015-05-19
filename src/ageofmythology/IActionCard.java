/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

/**
 *
 * @author vipinsharma
 */
public interface IActionCard {
    public void selectCard(ImagePanel actionCard);
    public void playCard(ImagePanel actionCard);
    public ImagePanel returnCard();
    public boolean ifCardPlayed();
}
