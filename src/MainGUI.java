import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CalendarObjects.CalendarTop;
import CalendarObjects.TimeSlot;

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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private CalendarTop mainCalendar;

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
		mainCalendar = new CalendarTop();
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
		leftPanel.add(textField);//date box
		textField.setText("MM/DD/YYYY");
		
			
		JComboBox comboBox = new JComboBox(TimeSlot.getTimeArray());
		leftPanel.add(comboBox);//tiemslot
		
		JComboBox comboBox_1 = new JComboBox(mainCalendar.getRoomArray());//room
		leftPanel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox(mainCalendar.getTeacherArray());//teacher
		leftPanel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox(mainCalendar.getClassEntryArray());//classentry
		leftPanel.add(comboBox_3);
		
		
		textField.setColumns(10);
		
		JComboBox comboBox_5 = new JComboBox();
		leftPanel.add(comboBox_5);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(25, 8, 0, 0));
		
		JLabel columnLabel_blank = new JLabel(" ");
		columnLabel_blank.setHorizontalAlignment(SwingConstants.CENTER);
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
			JLabel rowLabel_i = new JLabel(TimeSlot.convertSlotTime(i/8));
			rowLabel_i.setHorizontalAlignment(SwingConstants.CENTER);
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
		btnAddButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JButton btnRemoveButton = new JButton("Remove Event");
		btnRemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		rightPanel.add(btnRemoveButton);
		
		
		JButton btnNewButton = new JButton("Generate Next year");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rightPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Generate last year");
		rightPanel.add(btnNewButton_1);
		
		JButton btnDetailView = new JButton("Detail view");
		rightPanel.add(btnDetailView);
		
		JLabel lblNewLabel = new JLabel("");
		rightPanel.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Forward 1 Week");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rightPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Backward 1 Week");
		rightPanel.add(btnNewButton_3);
		
		

	}

}
