/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author agsjohn
 */
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Placeholder {
    public static void addPlaceholder(javax.swing.JPasswordField campoSenha, String placeholder) {
        Color corPlaceholder = Color.GRAY;
        Color corOriginal = campoSenha.getForeground();

        campoSenha.setText(placeholder);
        campoSenha.setForeground(corPlaceholder);
        campoSenha.setEchoChar((char) 0);

        campoSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoSenha.getText().equals(placeholder)) {
                    campoSenha.setText("");
                    campoSenha.setForeground(corOriginal);
                    campoSenha.setEchoChar('•');
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (campoSenha.getText().isEmpty()) {
                    campoSenha.setText(placeholder);
                    campoSenha.setEchoChar((char) 0);
                    campoSenha.setForeground(corPlaceholder);
                }
            }
        });
    }
    
    public static void addPlaceholder(javax.swing.JTextField texto, String placeholder) {
        Color corPlaceholder = Color.GRAY;
        Color corOriginal = texto.getForeground();

        texto.setText(placeholder);
        texto.setForeground(corPlaceholder);

        texto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (texto.getText().equals(placeholder)) {
                    texto.setText("");
                    texto.setForeground(corOriginal);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (texto.getText().isEmpty()) {
                    texto.setText(placeholder);
                    texto.setForeground(corPlaceholder);
                }
            }
        });
    }
}
