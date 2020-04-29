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
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTextField;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new GridLayout(8, 1, 0, 0));
		
		textField = new JTextField();
		leftPanel.add(textField);
		
		JComboBox comboBox = new JComboBox(Day);
		leftPanel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(TimeSlot);
		leftPanel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox(Teacher);
		leftPanel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox(ClassEntry);
		leftPanel.add(comboBox_3);
		
		JButton btnAddButton = new JButton("Add Event");
		leftPanel.add(btnAddButton);
		
		JButton btnRemoveButton = new JButton("Remove Event");
		leftPanel.add(btnRemoveButton);
		
		
		textField.setColumns(10);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(24, 7, 0, 0));
		
		for (int i = 0; i < 168; i++) {
			JButton btnNewButton_i = new JButton("New button");
			btnNewButton_i.setText(String.valueOf(i));
			centerPanel.add(btnNewButton_i);
		}
		
		JScrollPane scrollPane = new JScrollPane(centerPanel);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		contentPane.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new GridLayout(8, 0, 0, 0));
		
		JComboBox comboBox_4 = new JComboBox();
		rightPanel.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		rightPanel.add(comboBox_5);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel.add(lblNewLabel_6);
	}

}
