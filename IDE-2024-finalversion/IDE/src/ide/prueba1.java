
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Jordi
 */
public class prueba1 extends javax.swing.JFrame {

    /**
     * Creates new form prueba1
     */
    public prueba1() {
        initComponents();
        inicializarAtributos();
        agregarListener();
        inicializarPalabrasClave();
        agregarParentesisListener();
        agregarCorcheteListener();
        bDesencriptar.setEnabled(false);
    }
    
    
    // Variables globales
    StyledDocument doc;
    SimpleAttributeSet defaultAttr;
    private Timer timer;
    private static final String miCadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZáàabcçdeéèfghiíìjklmnñoóòpqrstuúùvwxyz0123456789!@#$%^&*()[]{}";
    private boolean estaEncriptado = false; // Control de estado del texto



    // Mapa de palabras clave y colores
    private final Map<String, Color> palabrasClave = new HashMap<>();

    private void inicializarAtributos() {
        doc = jTextPane1.getStyledDocument();

        // Estilo predeterminado (color negro)
        defaultAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(defaultAttr, Color.WHITE);
    }

    
    private void inicializarPalabrasClave() {
        // Asignar colores a cada palabra clave
        palabrasClave.put("1", new Color(79, 231, 107));    // Verde
        palabrasClave.put("2", new Color(79, 231, 107));    // Verde
        palabrasClave.put("3", new Color(79, 231, 107));    // Verde
        palabrasClave.put("4", new Color(79, 231, 107));    // Verde
        palabrasClave.put("5", new Color(79, 231, 107));    // Verde
        palabrasClave.put("6", new Color(79, 231, 107));    // Verde
        palabrasClave.put("7", new Color(79, 231, 107));    // Verde
        palabrasClave.put("8", new Color(79, 231, 107));    // Verde
        palabrasClave.put("9", new Color(79, 231, 107));    // Verde
        palabrasClave.put("<", new Color(200, 191, 231)); // Morado
        palabrasClave.put(">", new Color(200, 191, 231)); // Morado
        palabrasClave.put("=", new Color(200, 191, 231)); // Morado
        palabrasClave.put("+", new Color(200, 191, 231)); // Morado
        palabrasClave.put("-", new Color(200, 191, 231)); // Morado
        palabrasClave.put("*", new Color(200, 191, 231)); // Morado
        palabrasClave.put("/", new Color(200, 191, 231)); // Morado
        palabrasClave.put("true", new Color(200, 191, 231)); // Morado
        palabrasClave.put("false", new Color(200, 191, 231)); // Morado
        palabrasClave.put("fun", new Color(255, 201, 20));  // Naranja
        palabrasClave.put("var ", new Color(200, 191, 231)); // Morado
        palabrasClave.put("val ", new Color(200, 191, 231)); // Morado
        palabrasClave.put("Int", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("String", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Float", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Byte", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Boolean", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Double", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Long", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Short", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Array", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("List", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("MutableList", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Set", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("MutableSet", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("Map", new Color(231, 55, 108));  // Rojo
        palabrasClave.put("MutableMap", new Color(231, 55, 108));  // Rojo
    }
    
      private void agregarListener() {
        doc.addDocumentListener(new DocumentListener() {
          
            public void insertUpdate(DocumentEvent e) {
                resetTimer();
            }
           
            public void removeUpdate(DocumentEvent e) {
                resetTimer();
            }
         
            public void changedUpdate(DocumentEvent e) {
                resetTimer();
            }
        });
    }
    
    private void resetTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(300, e -> cambiarColor()); // Espera 300 ms
        timer.setRepeats(false);
        timer.start();
    }
    
      private void cambiarColor() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Obtenemos el texto del documento
                String text = doc.getText(0, doc.getLength());

                // Limpiar los estilos existentes
                doc.setCharacterAttributes(0, text.length(), defaultAttr, true);

                // Resaltar cada palabra clave con su color correspondiente
                for (Map.Entry<String, Color> entry : palabrasClave.entrySet()) {
                    String palabra = entry.getKey();
                    Color color = entry.getValue();
                    SimpleAttributeSet highlightAttr = new SimpleAttributeSet();
                    StyleConstants.setForeground(highlightAttr, color);

                    int index = text.indexOf(palabra);
                    while (index >= 0) {
                        doc.setCharacterAttributes(index, palabra.length(), highlightAttr, false);
                        index = text.indexOf(palabra, index + palabra.length());
                    }
                }
            } catch (BadLocationException ex) {
            }
        });
    }
      
       private void agregarParentesisListener() {
        jTextPane1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    // Obtenemos el carácter que se acaba de insertar
                    int offset = e.getOffset();
                    String text = e.getDocument().getText(offset, 1);

                    // Si el carácter es un paréntesis de apertura
                    if (text.equals("(")) {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                // Insertamos el paréntesis de cierre justo después
                                e.getDocument().insertString(offset + 1, ")", null);
                                // Colocamos el cursor entre los paréntesis
                                jTextPane1.setCaretPosition(offset + 1);
                            } catch (BadLocationException ex) {
                            }
                        });
                    }
                } catch (BadLocationException ex) {
                }
            }
            @Override
            
              public void removeUpdate(DocumentEvent e) {
                // Usamos invokeLater para evitar modificar el documento durante la notificación
                SwingUtilities.invokeLater(() -> {
                    try {
                        // Obtenemos el offset de eliminación
                        int offset = e.getOffset();

                        // Verificamos si el siguiente carácter es un paréntesis de cierre ')'
                        if (offset < e.getDocument().getLength()) {
                            String nextChar = e.getDocument().getText(offset, 1);
                            if (nextChar.equals(")")) {
                                // Eliminamos el paréntesis de cierre si corresponde
                                e.getDocument().remove(offset, 1);
                            }
                        }
                    } catch (BadLocationException ex) {
                    }
                });
            }
            @Override
            
            public void changedUpdate(DocumentEvent e) {
                // No relevante para texto plano
            }
        });
    }
       
    private void agregarCorcheteListener() {
        jTextPane1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    // Obtenemos el carácter que se acaba de insertar
                    int offset = e.getOffset();
                    String text = e.getDocument().getText(offset, 1);

                    // Si el carácter es un paréntesis de apertura
                    if (text.equals("{")) {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                // Insertamos el paréntesis de cierre justo después
                                e.getDocument().insertString(offset + 1, "}", null);
                                // Colocamos el cursor entre los paréntesis
                                jTextPane1.setCaretPosition(offset + 1);
                            } catch (BadLocationException ex) {
                            }
                        });
                    }
                } catch (BadLocationException ex) {
                }
            }

            @Override
           public void removeUpdate(DocumentEvent e) {
                // Usamos invokeLater para evitar modificar el documento durante la notificación
                SwingUtilities.invokeLater(() -> {
                    try {
                        // Obtenemos el offset de eliminación
                        int offset = e.getOffset();

                        // Verificamos si el siguiente carácter es un paréntesis de cierre ')'
                        if (offset < e.getDocument().getLength()) {
                            String nextChar = e.getDocument().getText(offset, 1);
                            if (nextChar.equals("}")) {
                                // Eliminamos el paréntesis de cierre si corresponde
                                e.getDocument().remove(offset, 1);
                            }
                        }
                    } catch (BadLocationException ex) {
                    }
                });
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No relevante para texto plano
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        bEncriptar = new javax.swing.JButton();
        bDesencriptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextPane1.setBackground(new java.awt.Color(153, 153, 153));
        jTextPane1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTextPane1);

        bEncriptar.setText("Encriptar");
        bEncriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEncriptarActionPerformed(evt);
            }
        });

        bDesencriptar.setText("Desencriptar");
        bDesencriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDesencriptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bEncriptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bDesencriptar)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEncriptar)
                    .addComponent(bDesencriptar))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    // Método para encriptar
    private String encriptar(String texto, String clave) {
        StringBuilder textoEncriptado = new StringBuilder();
        int claveIndex = 0;

        for (char c : texto.toCharArray()) {
            int textoPos = miCadena.indexOf(c);
            if (textoPos != -1) { // Solo encripta si el carácter está en la cadena
                int clavePos = miCadena.indexOf(clave.charAt(claveIndex % clave.length()));
                int nuevaPos = (textoPos + clavePos) % miCadena.length();
                textoEncriptado.append(miCadena.charAt(nuevaPos));
                claveIndex++;
            } else {
                textoEncriptado.append(c); // Mantiene caracteres que no están en la cadena
            }
        }
        return textoEncriptado.toString();
    }

    // Método para desencriptar
    private String desencriptar(String texto, String clave) {
        StringBuilder textoDesencriptado = new StringBuilder();
        int claveIndex = 0;

        for (char c : texto.toCharArray()) {
            int textoPos = miCadena.indexOf(c);
            if (textoPos != -1) { // Solo desencripta si el carácter está en la cadena
                int clavePos = miCadena.indexOf(clave.charAt(claveIndex % clave.length()));
                int nuevaPos = (textoPos - clavePos + miCadena.length()) %miCadena.length(); // Asegura que la posición sea positiva
                textoDesencriptado.append(miCadena.charAt(nuevaPos));
                claveIndex++;
            } else {
                textoDesencriptado.append(c); // Mantiene caracteres que no están en el alfabeto
            }
        }
        return textoDesencriptado.toString();
    }
    
    private void bEncriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEncriptarActionPerformed
         String texto = jTextPane1.getText();
        String clave = JOptionPane.showInputDialog("Introduce la clave para encriptar:");

        String textoEncriptado = encriptar(texto, clave);
        jTextPane1.setText(textoEncriptado);
        estaEncriptado = true; // Actualiza el estado a encriptado
        actualizarBotones(); // Actualiza el estado de los botones
    }//GEN-LAST:event_bEncriptarActionPerformed

    private void bDesencriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDesencriptarActionPerformed
        String texto = jTextPane1.getText();
        String clave = JOptionPane.showInputDialog("Introduce la clave para desencriptar:");

        String textoDesencriptado = desencriptar(texto, clave);
        jTextPane1.setText(textoDesencriptado);
        estaEncriptado = false; // Actualiza el estado a desencriptado
        actualizarBotones(); // Actualiza el estado de los botones
    
    }//GEN-LAST:event_bDesencriptarActionPerformed

    private void actualizarBotones() {
       // Habilitar o deshabilitar los botones según el estado del texto
        bEncriptar.setEnabled(!estaEncriptado);
        bDesencriptar.setEnabled(estaEncriptado);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(prueba1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prueba1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prueba1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prueba1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prueba1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDesencriptar;
    private javax.swing.JButton bEncriptar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
