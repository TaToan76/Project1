package ReadWriteFile;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTree;

public class Read extends JFrame {

	private JPanel contentPane;
	public JFileChooser fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Read frame = new Read();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Read() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 11, 430, 189);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc;
				FileReader fr=null;
				BufferedReader br=null;
				String s;
				try {
					fc = new JFileChooser();
					int openDialog = fc.showOpenDialog(null);
					if(openDialog==JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath();
						fr = new FileReader(path);
						br = new BufferedReader(fr);
						while((s=br.readLine())!=null) {
							textArea.append(s+"\n");
						}
					}
					br.close();
					fr.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("Error"+e2.getMessage());
				}
			}
		});
		btnOpen.setBounds(112, 235, 89, 23);
		contentPane.add(btnOpen);
		
		JButton btnSave = new JButton("Save As");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc ;
				FileWriter fw =null;
				BufferedWriter bw=null;
				try {
					fc = new JFileChooser();
					int openDialog = fc.showSaveDialog(null);
					if(openDialog==JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath();
						fw = new FileWriter(path);
						bw = new BufferedWriter(fw);
						bw.write(textArea.getText());
						bw.flush();						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("Error"+e2.getMessage());
				}
			}			
		});
		btnSave.setBounds(242, 235, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		
					fc = new JFileChooser();
					int openDialog = fc.showOpenDialog(null);
					if(openDialog==JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath();
						File xoaFile = new File(path);
						if(xoaFile.delete()) {
							JOptionPane.showMessageDialog(null, "Xoá file thành công !");
						} else {
							JOptionPane.showMessageDialog(null, "Xoá file thất bại !");
						}
					}	
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		}
			
		});
		btnDelete.setBounds(377, 235, 89, 23);
		contentPane.add(btnDelete);
	}
}
