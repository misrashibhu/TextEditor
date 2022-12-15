import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener
{
    JFrame f;
    JTextArea t;

    editor()
    {
        f=new JFrame("notepad");
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e)
        {

        }
        t=new JTextArea();
        JMenuBar menu=new JMenuBar();

        JMenu file=new JMenu("File");

        JMenuItem m1=new JMenuItem("New");
        JMenuItem m2=new JMenuItem("Open");
        JMenuItem m3=new JMenuItem("Save");
        JMenuItem m4=new JMenuItem("Print");

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);

        JMenu edit=new JMenu("Edit");

        JMenuItem m5=new JMenuItem("Copy");
        JMenuItem m6=new JMenuItem("Paste");
        JMenuItem m7=new JMenuItem("Cut");

        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        edit.add(m5);
        edit.add(m6);
        edit.add(m7);

        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);

        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1600,900);
        f.show();
    }

    public void actionPerformed(ActionEvent e)
    {
        String s=e.getActionCommand();
        if(s.equals("New"))
        {
            t.setText("New Text");
        }
        else if(s.equals("Open"))
        {
            JFileChooser j=new JFileChooser("Untitled");
            int r=j.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                String filepath=j.getSelectedFile().getAbsolutePath();
                File fi=new File(filepath);
                FileReader fr= null;
                try {
                    fr = new FileReader(fi);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                BufferedReader br=new BufferedReader(fr);
                String s1=""; String s2="";
                try {
                    s2=br.readLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                while(true)
                {
                    try {
                        if (!((s1=br.readLine())!=null)) break;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    s2=s2+'\n'+s1;
                }
                t.setText(s2);
            }
        }
        else if(s.equals("Save"))
        {
            JFileChooser j=new JFileChooser("Untitled");
            int r=j.showSaveDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                String filepath=j.getSelectedFile().getAbsolutePath();
                File fi=new File(filepath);
                FileWriter fw= null;
                try {
                    fw = new FileWriter(fi);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                BufferedWriter bw=new BufferedWriter(fw);
                try {
                    bw.write(t.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    bw.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    bw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Print"))
        {

        }
        else if(s.equals("Cut"))
        {
            t.cut();
        }
        else if(s.equals("Copy"))
        {
            t.copy();
        }
        else if(s.equals("Paste"))
        {
            t.paste();
        }
        else if(s.equals("Close"))
        {
            f.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        editor e=new editor();
    }
}
