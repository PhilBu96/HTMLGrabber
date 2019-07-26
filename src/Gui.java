import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Gui extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private JButton grabBtn, saveBtn;
    private JTextField sourceField;
    private JTextField outputField;
    private JFileChooser jfc;
    private String targetFile;
    private int width = 1000;
    private int heigth = 700;

    public Gui() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("HTML-Grabber");
        frame.setSize(width, heigth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        try {
            frame.setIconImage((Image) ImageIO.read(new File("rsc\\icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setFocusable(true);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setSize(width, heigth);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);
        frame.add(panel);
        panel.setVisible(true);

        grabBtn = new JButton("Grab!");
        panel.add(grabBtn);
        grabBtn.setBounds(width/4, heigth/2,width/2, heigth/4);
        grabBtn.setFont(new Font("Arial", Font.BOLD, 60));
        grabBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HTMLGrab.grabHtml(sourceField.getText(), outputField.getText());
                    System.exit(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    System.out.println("Fehler beim Speichern der Datei!");
                }
            }
        });

        sourceField = new JTextField();
        sourceField.setFont(new Font("Arial", Font.PLAIN, 30));
        sourceField.setBounds(width/16, heigth/16, width-(width/8), heigth/8);
        sourceField.setFocusable(true);
        sourceField.setVisible(true);
        panel.add(sourceField);
        sourceField.setText("Link zur HTML-Seite...");
        sourceField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sourceField.setText("");
            }
        });

        outputField = new JTextField();
        outputField.setFont(new Font("Arial", Font.PLAIN, 30));
        outputField.setBounds(width/16, heigth/4, sourceField.getWidth()*2/3, heigth/8);
        outputField.setEditable(false);
        outputField.setFocusable(true);
        panel.add(outputField);
        outputField.setText("Zieldatei...");

        saveBtn = new JButton("Durchsuchen...");
        panel.add(saveBtn);
        saveBtn.setBounds(sourceField.getWidth() - sourceField.getWidth()/4, heigth/4, sourceField.getWidth()*2/7, heigth/8);
        saveBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        saveBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jfc = new JFileChooser();
                    jfc.showSaveDialog(null);
                    targetFile = jfc.getSelectedFile().getAbsolutePath() + ".html";
                    System.out.println("Zielpfad erfasst: " + targetFile);
                    outputField.setText(targetFile);
                } catch (Exception e1) {
                    System.out.println("Fehler beim Erfassen des Zielpfades!");
                }
            }
        });

    }

}
