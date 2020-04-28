import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox comboBox = new JComboBox();
		panel_2.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_2.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		panel_2.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		panel_2.add(comboBox_4);
		
		JButton btnNewButton = new JButton("Add event");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove event");
		panel_2.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollBar scrollBar = new JScrollBar();
		panel_1.add(scrollBar, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(24, 7, 0, 0));
		
		
		
		for (int i = 0; i < 168; i++) {
			JButton btnNewButton_i = new JButton("New button");
			panel_3.add(btnNewButton_i);
			btnNewButton_i.setText(String.valueOf(i));
		}
		
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea textArea = new JTextArea();
		panel.add(textArea);
		
		JComboBox comboBox_5 = new JComboBox();
		panel.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		panel.add(comboBox_6);
	}

}
