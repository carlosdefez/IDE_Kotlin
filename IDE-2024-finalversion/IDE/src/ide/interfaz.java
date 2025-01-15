/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ide;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.Document;


/**
 *
 * @author Nikolas
 */
public class interfaz extends javax.swing.JFrame {
 private JTabbedPane tabbedPane;
    private JTextPane nuevoTextPane;
    private JScrollPane nuevoScrollPane;
     private UndoManager deshacerRehacer;
      
    
    /**
     * Creates new form interfaz
     */
    public interfaz() {
        initComponents();
                
        
        ///////METODOS DE CAMBIO DE COLOR/////////////////
        inicializarAtributos(jTextPane2);
        agregarListener(jTextPane2);
        inicializarPalabrasClave(jTextPane2);
        agregarAutoCompletarComentarios(jTextPane2);
        bDesencriptar.setEnabled(false);
        agregarParentesisListener(jTextPane2);
        agregarCorcheteListener(jTextPane2);
         deshacerRehacer = new UndoManager();
         menuPopup();
         //ActionListener para deshacer y rehacer (shortCuts).
        jTextPane2.getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
            deshacerRehacer.addEdit(e.getEdit());
        });
        Toolkit t= Toolkit.getDefaultToolkit();
       setIconImage (t.getImage(getClass().getResource("/ide/logo emilbus_1.png")));
        
            
        ///////FIN METODOS CAMBIO DE COLOR///////////////
        ///////INICIO DE LINKEADOR//////////////////////
    
        ///////FIN DE LINKEADOR//////////////////////
        
        
        
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////INICIO DEL CAMBIO DE COLORES//////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    private final Map<String, Color> palabrasClave = new HashMap<>();

    private void inicializarAtributos(JTextPane textPane) {
        doc = jTextPane2.getStyledDocument();

        // Estilo predeterminado (color negro)
        defaultAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(defaultAttr, Color.WHITE);
    }
    
  

    public void menuPopup() {
        JPopupMenu popupMenu = new JPopupMenu();

        // Crear items para el menú
        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem pegar = new JMenuItem("Pegar");
        JMenuItem desHacer = new JMenuItem("Deshacer");
        JMenuItem rehacer = new JMenuItem("Rehacer");

        // Añadir acciones para cada ítem del menú
        copiar.addActionListener(e -> jTextPane2.copy());
        cortar.addActionListener(e -> jTextPane2.cut());
        pegar.addActionListener(e -> jTextPane2.paste());
        desHacer.addActionListener(e -> deshacer());  // Llamar al método deshacer
        rehacer.addActionListener(e -> rehacer());    // Llamar al método rehacer

        // Añadir los items al menú emergente
        popupMenu.add(copiar);
        popupMenu.add(cortar);
        popupMenu.add(pegar);
        popupMenu.add(desHacer);
        popupMenu.add(rehacer);

        // Asignar el menú emergente al JTextPane
        jTextPane2.setComponentPopupMenu(popupMenu);

        // Detectar el clic derecho para mostrar el menú
        jTextPane2.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) { // Detectar clic derecho
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    
    
    

    // Método para inicializar el UndoManager en el JTextPane
    private void deshacer(JTextPane JTextPaneCreado) {
        deshacerRehacer = new UndoManager();
        JTextPaneCreado.getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
            deshacerRehacer.addEdit(e.getEdit());
        });
    }

    // Método deshacer
    private void deshacer() {
        try {
            if (deshacerRehacer.canUndo()) {
                deshacerRehacer.undo();
            }
        } catch (CannotUndoException e) {
            e.printStackTrace();
        }
    }

    // Método rehacer
    private void rehacer() {
        try {
            if (deshacerRehacer.canRedo()) {
                deshacerRehacer.redo();
            }
        } catch (CannotRedoException e) {
            e.printStackTrace();
        }
    }

    
    

    
    private void inicializarPalabrasClave(JTextPane textPane) {
        // Asignar colores a cada palabra clave
        palabrasClave.put("0", new Color(36, 191, 246));//Azul
        palabrasClave.put("1", new Color(36, 191, 246));//Azul
        palabrasClave.put("2", new Color(36, 191, 246));//Azul
        palabrasClave.put("3", new Color(36, 191, 246));//Azul
        palabrasClave.put("4", new Color(36, 191, 246));//Azul
        palabrasClave.put("5", new Color(36, 191, 246));//Azul
        palabrasClave.put("6", new Color(36, 191, 246));//Azul
        palabrasClave.put("7", new Color(36, 191, 246));//Azul
        palabrasClave.put("8", new Color(36, 191, 246));//Azul
        palabrasClave.put("9", new Color(36, 191, 246));//Azul
        palabrasClave.put("<", new Color(200, 191, 231));//Morado
        palabrasClave.put(">", new Color(200, 191, 231));//Morado
        palabrasClave.put("=", new Color(200, 191, 231));//Morado
        palabrasClave.put("+", new Color(200, 191, 231));//Morado
        palabrasClave.put("-", new Color(200, 191, 231));//Morado
        palabrasClave.put("*", new Color(200, 191, 231));//Morado
        palabrasClave.put("/", new Color(200, 191, 231));//Morado
        palabrasClave.put("$", new Color(200, 191, 231));//Morado
        palabrasClave.put("true ", new Color(200, 191, 231));//Morado
        palabrasClave.put("false ", new Color(200, 191, 231));//Morado
        palabrasClave.put("fun ", new Color(255, 201, 20));//Naranja
        palabrasClave.put("class ", new Color(255, 201, 20));//Naranja
        palabrasClave.put("for ", new Color(255, 201, 20));//Naranja
        palabrasClave.put("if ", new Color(255, 201, 20));//Naranja
        palabrasClave.put("when ", new Color(255, 201, 20));//Naranja
        palabrasClave.put("do ", new Color(255, 201, 20));//Naranja
        palabrasClave.put("while", new Color(255, 201, 20));//Naranja
        palabrasClave.put("var", new Color(200, 191, 231));//Morado
        palabrasClave.put("val", new Color(200, 191, 231));//Morado
        palabrasClave.put("return", new Color(200, 191, 231)); //Morado
        palabrasClave.put("println", new Color(200, 191, 231)); //Morado
        palabrasClave.put("print", new Color(200, 191, 231)); //Morado
        palabrasClave.put("Int", new Color(255, 115, 96));//Rojo
        palabrasClave.put("String", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Float", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Byte", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Boolean", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Double", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Long", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Short", new Color(255, 115, 96));//Rojo
        palabrasClave.put("Array", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MutableList", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MutableListOf", new Color(255, 115, 96));//Rojo
        palabrasClave.put("ListOf", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MutableSet", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MutableSetOf", new Color(255, 115, 96));//Rojo
        palabrasClave.put("SetOf", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MutableMap", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MutableMapOf", new Color(255, 115, 96));//Rojo
        palabrasClave.put("MapOf", new Color(255, 115, 96));//Rojo
    }
    
      private void agregarListener(JTextPane textPane) {
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
        timer = new Timer(0, e -> cambiarColor()); // Espera 300 ms
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
      private void agregarAutoCompletarComentarios(JTextPane textPane) {
    textPane.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> {
                try {
                    // Obtener el texto antes de la posición actual
                    int offset = e.getOffset();
                    Document doc = e.getDocument();
                    String textoActual = doc.getText(0, offset + 1);

                    // Verificar si se escribió "/*"
                    if (offset >= 1 && textoActual.substring(offset - 1, offset + 1).equals("/*")) {
                        // Insertar "*/" después del cursor
                        doc.insertString(offset + 1, "*/", null);

                        // Mover el cursor al medio
                        textPane.setCaretPosition(offset + 1);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            });
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // No necesitamos implementar esta funcionalidad
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // No necesitamos implementar esta funcionalidad
        }
    });
}

      
       private void agregarParentesisListener(JTextPane textPane) {
    textPane.getDocument().addDocumentListener(new DocumentListener() {
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
                            textPane.setCaretPosition(offset + 1); // Mover el cursor
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
        
            
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
       // Método que crea un panel con el título de la pestaña y un botón de cierre
private JPanel crearTituloPestania(final JTabbedPane jTabbPanel, final JScrollPane panel, String titulo) {
    // Crear un JPanel que contiene el título y el botón de cierre
    JPanel panelPestania = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelPestania.setOpaque(false);

    // Crear un JLabel con el título de la pestaña
    JLabel labelTitulo = new JLabel(titulo);
    panelPestania.add(labelTitulo);

    // Crear un botón de cierre (puedes usar un icono si prefieres)
    JButton botonCerrar = new JButton("x");
    botonCerrar.setPreferredSize(new Dimension(17, 17));
    botonCerrar.setMargin(new Insets(0, 0, 0, 0));
    botonCerrar.setOpaque(false);

    // Añadir un ActionListener para cerrar la pestaña al presionar el botón
    botonCerrar.addActionListener(e -> {
        int index = jTabbPanel.indexOfComponent(panel);
        if (index != -1) {
            jTabbPanel.remove(index);
        }
    });

    panelPestania.add(botonCerrar);

    return panelPestania;
}

    private void agregarCorcheteListener(JTextPane textPane) {
        textPane.getDocument().addDocumentListener(new DocumentListener() {
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
                                jTextPane2.setCaretPosition(offset + 1);
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
    
 
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////FIN DEL CAMBIO DE COLORES//////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////INICIO ENCRIPTACION//////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
     // Variables globales
    StyledDocument doc;
    SimpleAttributeSet defaultAttr;
    private Timer timer;
    private static final String miCadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZáàabcçdeéèfghiíìjklmnñoóòpqrstuúùvwxyz0123456789!@#$%^&*()[]{}";
    private boolean estaEncriptado = false; // Control de estado del texto
    JFileChooser file = new JFileChooser();
    FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.kt)", "kt");
    String nombreArch;
    
    
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
    private void actualizarBotones() {
       // Habilitar o deshabilitar los botones según el estado del texto
        bEncriptar.setEnabled(!estaEncriptado);
        bDesencriptar.setEnabled(estaEncriptado);
    }
   private void abrirArchivo() {
    StringBuilder texto = new StringBuilder();  // Usar StringBuilder para eficiencia
    String aux = "";  // Variable temporal para leer las líneas
    

    try {
        // Crear y configurar el JFileChooser
        file.setDialogTitle("Abrir archivo de texto");
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);  // Solo archivos
        file.setFileFilter(filtro);  // Filtro opcional para solo mostrar archivos de texto

        // Mostrar el diálogo de apertura
        int seleccion = file.showOpenDialog(this);

        // Verificar si el usuario seleccionó un archivo
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File abre = file.getSelectedFile();
            String nombreArch = abre.getName();  // Obtener el nombre del archivo

            // Verificar que el archivo no sea null
            if (abre != null) {
                try (FileReader fr = new FileReader(abre);
                     BufferedReader br = new BufferedReader(fr)) {

                    // Comprobar si la primera pestaña está vacía
                    if (jTabbPanel.getTabCount() > 0 && jTabbPanel.getComponentAt(0) instanceof JScrollPane) {
                        JScrollPane scrollPane = (JScrollPane) jTabbPanel.getComponentAt(0);
                        JViewport viewport = scrollPane.getViewport();
                        JTextPane textPane = (JTextPane) viewport.getView();

                        // Si el JTextPane está vacío, eliminar la primera pestaña
                        if (textPane.getText().trim().isEmpty()) {
                            jTabbPanel.remove(0);
                            textPane.setText(aux);
                              
                  

                   
                   
                        }
                    }

                    // Crear nuevos JTextPane y JScrollPane
                    JTextPane nuevoTextPane = new JTextPane();
                    JScrollPane nuevoScrollPane = new JScrollPane(nuevoTextPane);
                     nuevoTextPane.getDocument().addUndoableEditListener(new UndoableEditListener() {
                public void undoableEditHappened(UndoableEditEvent e) {
                    deshacerRehacer.addEdit(e.getEdit()); // Agregar la edición al UndoManager
                }
            });

                    // Leer el archivo y construir el texto
                    while ((aux = br.readLine()) != null) {
                        texto.append(aux).append("\n");
                    }

                    // Insertar el texto leído en el nuevo JTextPane
                    nuevoTextPane.setText(texto.toString());

                    // Agregar el listener para manejar los paréntesis
                    agregarParentesisListener(nuevoTextPane);
                    agregarCorcheteListener(nuevoTextPane);
                    inicializarPalabrasClave(nuevoTextPane);
                    inicializarAtributos(nuevoTextPane);
                    agregarListener(nuevoTextPane);

                    // Crear un panel personalizado con el nombre del archivo y el botón de cierre
                    JPanel panelConCierre = crearTituloPestania(jTabbPanel, nuevoScrollPane, nombreArch);

                    // Agregar la nueva pestaña con el JScrollPane (contenido) y el panel personalizado (título)
                    jTabbPanel.addTab(null, nuevoScrollPane);
                    jTabbPanel.setTabComponentAt(jTabbPanel.getTabCount() - 1, panelConCierre);

                    // Seleccionar la nueva pestaña
                    jTabbPanel.setSelectedIndex(jTabbPanel.getTabCount() - 1);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al abrir el archivo:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error inesperado:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
   private void abrirArchivoSimple() {
    StringBuilder texto = new StringBuilder();  // Usar StringBuilder para eficiencia
    String aux = "";  // Variable temporal para leer las líneas
    

    try {
        // Crear y configurar el JFileChooser
        file.setDialogTitle("Abrir archivo de texto");
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);  // Solo archivos
        file.setFileFilter(filtro);  // Filtro opcional para solo mostrar archivos de texto

        // Mostrar el diálogo de apertura
        int seleccion = file.showOpenDialog(this);

        // Verificar si el usuario seleccionó un archivo
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File abre = file.getSelectedFile();
            String nombreArch = abre.getName();  // Obtener el nombre del archivo

            // Verificar que el archivo no sea null
            if (abre != null) {
                try (FileReader fr = new FileReader(abre);
                     BufferedReader br = new BufferedReader(fr)) {

                    // Comprobar si la primera pestaña está vacía
                    if (jTabbPanel.getTabCount() > 0 && jTabbPanel.getComponentAt(0) instanceof JScrollPane) {
                        JScrollPane scrollPane = (JScrollPane) jTabbPanel.getComponentAt(0);
                        JViewport viewport = scrollPane.getViewport();
                        JTextPane textPane = (JTextPane) viewport.getView();

                        // Si el JTextPane está vacío, eliminar la primera pestaña
                       // if (textPane.getText().trim().isEmpty()) {
                            //jTabbPanel.remove(0);
                            //textPane.setText(aux);
                              // Leer el archivo y construir el texto
                    while ((aux = br.readLine()) != null) {
                        texto.append(aux).append("\n");
                    }

                    // Insertar el texto leído en el nuevo JTextPane
                    textPane.setText(texto.toString());
                    int index = jTabbPanel.getSelectedIndex(); // Obtén el índice de la pestaña activa.
                    jTabbPanel.setTitleAt(index, nombreArch);
                       // }
                    }

          

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al abrir el archivo:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error inesperado:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void abrirCarpeta(){
         StringBuilder texto = new StringBuilder();  // Usar StringBuilder para eficiencia
    String aux = "";  // Variable temporal para leer las líneas

    try {
        // Crear y configurar el JFileChooser
        
        file.setDialogTitle("Abrir archivo de texto");
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // Solo archivos

        // Filtro opcional para solo mostrar archivos de texto
        
        file.setFileFilter(filtro);

        // Mostrar el diálogo de apertura
        int seleccion = file.showOpenDialog(this);

        // Verificar si el usuario seleccionó un archivo
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File abre = file.getSelectedFile();  // Obtener el archivo seleccionado

            // Asegurarse de que el archivo no sea null
            if (abre != null) {
                // Leer el archivo
                FileReader fr = new FileReader(abre);
                BufferedReader br = new BufferedReader(fr);

                while ((aux = br.readLine()) != null) {
                    texto.append(aux).append("\n");
                }

                // Cerrar el lector
                br.close();

                // Insertar el texto en el JTextPane (por ejemplo, jTextPane1)
                jTextPane2.setText(texto.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Error al abrir el archivo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
     private void guardarComo(){
         String nombre="";
        JFileChooser file=new JFileChooser();
        file.setDialogTitle("Guardar archivo Kotlin");
        
        file.setFileFilter(filtro);
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file.showSaveDialog(this);
        //Guardar archivo con formato kt
        File guarda=file.getSelectedFile();
        if(guarda !=null){
            try {
                FileWriter save=new FileWriter(guarda+".kt");
                save.write(jTextPane2.getText());
                save.close();
            
            
            
            JOptionPane.showMessageDialog(null, "El archivo se guardo correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "El archivo no se guardo correctamente","Informacion",JOptionPane.WARNING_MESSAGE);
            }
        }
     }
    private void guardar(){
          
        String texto = jTextPane2.getText(); // Obtenemos el texto del JTextPane
StringBuilder binario = new StringBuilder();

// Convertir el texto a binario
for (char c : texto.toCharArray()) {
    String binChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');  // Asegura 8 bits
    binario.append(binChar).append(" ");  // Separar cada carácter con un espacio
}
int selectedIndex = jTabbPanel.getSelectedIndex();

// Obtener el nombre del archivo desde el título de la pestaña
String nombre_guardar = jTabbPanel.getTitleAt(selectedIndex);
System.out.print(nombre_guardar);

// Validar que el nombre no sea nulo o vacío
if (nombre_guardar != null && !nombre_guardar.trim().isEmpty()) {
    File archivo = new File(nombre_guardar + ".kt");
    File archivobin = new File(nombre_guardar + ".bin");

    if (!archivo.exists()) { // Si el archivo no existe, ejecutamos guardarComo()
        guardarComo();
        System.out.print("fsgsgs");
        return; // Salimos del método para evitar seguir con el guardado
    }

    BufferedWriter bw = null;

    // Guardar el archivo en formato Kotlin
    try {
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(texto); // Escribimos el texto en el archivo
        bw.close(); // Cerramos el archivo
    } catch (IOException ex) {
        java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    // Guardar el archivo en formato binario
    try {
        bw = new BufferedWriter(new FileWriter(archivobin));
        bw.write(binario.toString()); // Escribimos el texto en binario
        bw.close(); // Cerramos el archivo
    } catch (IOException ex) {
        java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
}}
     public void compilador(){
         
     }
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////FIN ENCRIPTACION//////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////INICIO LINKEADOR//////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        textArea1 = new java.awt.TextArea();
        botonRun = new javax.swing.JButton();
        bEncriptar = new javax.swing.JButton();
        bDesencriptar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbPanel = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuFile = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuGuardarComo = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu4.setText("jMenu4");

        jMenu9.setText("jMenu9");

        jMenu10.setText("jMenu10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        botonRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ide/copiaSin título.png"))); // NOI18N
        botonRun.setBorder(null);
        botonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRunActionPerformed(evt);
            }
        });

        bEncriptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ide/bloqueado.png"))); // NOI18N
        bEncriptar.setBorder(null);
        bEncriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEncriptarActionPerformed(evt);
            }
        });

        bDesencriptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ide/Diseño_sin_título-removebg-preview.png"))); // NOI18N
        bDesencriptar.setActionCommand("bDesencriptar");
        bDesencriptar.setBorder(null);
        bDesencriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDesencriptarActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");

        jTextPane2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(jTextPane2);

        jTabbPanel.addTab("Sin Titulo", jScrollPane1);

        jMenu1.setText("Archivos");

        jMenu6.setText("Open...");

        jMenuFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuFile.setText("File");
        jMenuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuFile);

        jMenuItem14.setText("Folder");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuItem3.setText("Recent Project");
        jMenu6.add(jMenuItem3);

        jMenu1.add(jMenu6);

        jMenu5.setText("Create...");

        jMenuItem11.setText("Project");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem12.setText("File");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenu1.add(jMenu5);

        jMenu7.setText("Project...");

        jMenuItem4.setText("import project");
        jMenu7.add(jMenuItem4);

        jMenuItem15.setText("export project");
        jMenu7.add(jMenuItem15);

        jMenu1.add(jMenu7);

        jMenu8.setText("File...");

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem16.setText("Save");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenuGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuGuardarComo.setText("Save as");
        jMenuGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGuardarComoActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuGuardarComo);

        jMenuItem18.setText("Save  All");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenu1.add(jMenu8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Deshacer");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setText("Rehacer");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("------------------------------");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Cut");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Copy");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Paste");
        jMenu2.add(jMenuItem10);

        jMenuItem19.setText("------------------------------");
        jMenu2.add(jMenuItem19);

        jMenuItem20.setText("Find");
        jMenu2.add(jMenuItem20);

        jMenuItem21.setText("Replace");
        jMenu2.add(jMenuItem21);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("View");

        jMenu11.setText("Themes");

        jMenuItem22.setText("jMenuItem22");
        jMenu11.add(jMenuItem22);

        jMenu3.add(jMenu11);

        jMenu12.setText("Windows");
        jMenu3.add(jMenu12);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRun, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1172, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(botonRun, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(520, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jTabbPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed


    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        rehacer();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void botonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRunActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_botonRunActionPerformed

    private void bDesencriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDesencriptarActionPerformed
    String texto = jTextPane2.getText();
        String mensaje = "<html>Introduce la clave para desEncriptar:<br>";
    
        
      String clave = JOptionPane.showInputDialog(null, mensaje, "Desencriptar:", JOptionPane.INFORMATION_MESSAGE);
      
        // Validar que la clave no sea nula antes de encriptar
    if (clave != null && !clave.isEmpty()) {
        String textoDesencriptado = desencriptar(texto, clave);
        jTextPane2.setText(textoDesencriptado);
        estaEncriptado = false; // Actualiza el estado a encriptado
        actualizarBotones(); // Actualiza el estado de los botones
    } else {
        JOptionPane.showMessageDialog(null, "Clave no válida. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_bDesencriptarActionPerformed
 
    private void bEncriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEncriptarActionPerformed
                                        
    String texto = jTextPane2.getText();
    
    // Mensaje personalizado con HTML
    String mensaje = "<html>Introduce la clave para encriptar:<br>"
                   + "<span style='font-size:8px;'>La clave debe tener al menos 8 caracteres.<br>"
                   + "Usa letras y números para mayor seguridad.</span></html>";

    String clave = JOptionPane.showInputDialog(null, mensaje, "Encriptar Código", JOptionPane.INFORMATION_MESSAGE);
    
    // Validar que la clave no sea nula antes de encriptar
    if (clave != null && !clave.isEmpty()) {
        String textoEncriptado = encriptar(texto, clave);
        jTextPane2.setText(textoEncriptado);
        estaEncriptado = true; // Actualiza el estado a encriptado
        actualizarBotones(); // Actualiza el estado de los botones
    } else {
        JOptionPane.showMessageDialog(null, "Clave no válida. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_bEncriptarActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        guardar();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGuardarComoActionPerformed
        guardarComo();
    }//GEN-LAST:event_jMenuGuardarComoActionPerformed

    private void jMenuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileActionPerformed
        // TODO add your handling code here:
        abrirArchivoSimple();
    }//GEN-LAST:event_jMenuFileActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
       if (jTabbPanel.getTabCount() > 0 && jTabbPanel.getComponentAt(0) instanceof JScrollPane) {
                        JScrollPane scrollPane = (JScrollPane) jTabbPanel.getComponentAt(0);
                        JViewport viewport = scrollPane.getViewport();
                        JTextPane textPane = (JTextPane) viewport.getView();

                        
                        
                 
                         JTextPane nuevoTextPane = new JTextPane();
                    JScrollPane nuevoScrollPane = new JScrollPane(nuevoTextPane);
                     nuevoTextPane.setText("");
                     jTabbPanel.add(nuevoTextPane,nuevoScrollPane);
                     
                     
                    }
      
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        abrirCarpeta();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        deshacer();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem11ActionPerformed
//METODO NUEVO TAB
    public void agregarPestanya(String nombreArchivo, String contenidoArchivo) {
        nuevoTextPane = new JTextPane();
        nuevoScrollPane = new JScrollPane(nuevoTextPane);

        // Asegúrate de que el método crearNumLineas maneje correctamente los parámetros
       // crearNumLineas(nuevoTextPane, nuevoScrollPane, nuevoTextPane.getBackground());

        // Agregar la nueva pestaña
        jTabbPanel.addTab(nombreArchivo, nuevoScrollPane);
        jTabbPanel.setSelectedIndex(jTabbPanel.getTabCount() - 1);

        // Almacenar el archivo en la lista de archivos abiertos
        int index = jTabbPanel.getTabCount() - 1;
       // archivosAbiertos[index] = archivoActual;

        // Quitar bordes de los nuevos JTextPanes y JScrollPanes
        nuevoTextPane.setBorder(BorderFactory.createEmptyBorder());
        nuevoScrollPane.setBorder(BorderFactory.createEmptyBorder());
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
       try {
//             UIManager.setLookAndFeel( new FlatLightLaf() );

            FlatCobalt2IJTheme.setup();

         } catch( Exception ex ) {
             System.err.println( "Failed to initialize LaF" );
         }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDesencriptar;
    private javax.swing.JButton bEncriptar;
    private javax.swing.JButton botonRun;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuFile;
    private javax.swing.JMenuItem jMenuGuardarComo;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane2;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
