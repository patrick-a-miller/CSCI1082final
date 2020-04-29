import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
		
	
		//user must type out date
		textField_1 = new JTextField();
		leftPanel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("MM/DD/YYYY");
		//room box
		JComboBox comboBox_1 = new JComboBox();
		leftPanel.add(comboBox_1);
		leftPanel.add(comboBox_2);
		//teacher box
		JComboBox comboBox_5 = new JComboBox();
		leftPanel.add(comboBox_5);
		//class box
		JComboBox comboBox_4 = new JComboBox();
		leftPanel.add(comboBox_4);
		
				
		
		textField.setColumns(10);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(25, 8, 0, 0));
		
		JLabel columnLabel_blank = new JLabel(" ");
		JLabel columnLabel1 = new JLabel("Sun");
		columnLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel columnLabel2 = new JLabel("Mon");
		columnLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel columnLabel3 = new JLabel("Tue");
		columnLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel columnLabel4 = new JLabel("Wed");
		columnLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel columnLabel5 = new JLabel("Thu");
		columnLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel columnLabel6 = new JLabel("Fri");
		columnLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel columnLabel7 = new JLabel("Sat");
		columnLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		
		centerPanel.add(columnLabel_blank);
		centerPanel.add(columnLabel1);
		centerPanel.add(columnLabel2);
		centerPanel.add(columnLabel3);
		centerPanel.add(columnLabel4);
		centerPanel.add(columnLabel5);
		centerPanel.add(columnLabel6);
		centerPanel.add(columnLabel7);
		
		int button_count = 0;
		for (int i = 0; i < 192; i++) {
						if(i%8==0) {
			JLabel rowLabel_i = new JLabel(Integer.toString(i/8));
			centerPanel.add(rowLabel_i);
			}else {
			JButton btnNewButton_i = new JButton("New button");
			button_count++;
			btnNewButton_i.setText(String.valueOf(button_count));
			btnNewButton_i.setContentAreaFilled(false);
			centerPanel.add(btnNewButton_i);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane(centerPanel);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		contentPane.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new GridLayout(8, 0, 0, 0));
		
		JButton btnAddButton = new JButton("Add Event");
		rightPanel.add(btnAddButton);
		
		JButton btnRemoveButton = new JButton("Remove Event");
		rightPanel.add(btnRemoveButton);
		
		JButton btnNewButton = new JButton("Generate Next Year");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rightPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Generate Last Year");
		rightPanel.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		
		//date labels at the top
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
