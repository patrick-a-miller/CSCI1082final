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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textDateField;
	private CalendarTop mainCalendar;
	private static final int SCROLL_GRID_SIZE = 192;
	private JButton[] scrollButtonArray = new JButton[SCROLL_GRID_SIZE];
	private JLabel columnLabel_blank;
	private JLabel columnLabel1;
	private JLabel columnLabel2;
	private JLabel columnLabel3;
	private JLabel columnLabel4;
	private JLabel columnLabel5;
	private JLabel columnLabel6;
	private JLabel columnLabel7;

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

		textDateField = new JTextField();
		textDateField.setActionCommand("textDateField");
		textDateField.addActionListener(new TextListener());
		leftPanel.add(textDateField);// date box
		textDateField.setText("MM/DD/YYYY");

		JComboBox comboBox = new JComboBox(TimeSlot.getTimeArray());
		leftPanel.add(comboBox);// tiemslot

		JComboBox comboBox_1 = new JComboBox(mainCalendar.getRoomArray());// room
		leftPanel.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox(mainCalendar.getTeacherArray());// teacher
		leftPanel.add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox(mainCalendar.getClassEntryArray());// classentry
		leftPanel.add(comboBox_3);

		textDateField.setColumns(10);

		JComboBox comboBox_5 = new JComboBox();
		leftPanel.add(comboBox_5);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(25, 8, 0, 0));

		columnLabel_blank = new JLabel(" ");
		columnLabel_blank.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel1 = new JLabel("Sun");
		columnLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel2 = new JLabel("Mon");
		columnLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel3 = new JLabel("Tue");
		columnLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel4 = new JLabel("Wed");
		columnLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel5 = new JLabel("Thu");
		columnLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel6 = new JLabel("Fri");
		columnLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel7 = new JLabel("Sat");
		columnLabel7.setHorizontalAlignment(SwingConstants.CENTER);

		int dayHighlight = mainCalendar.getSelectedDay().getDay().get(Calendar.DAY_OF_WEEK);
		setScrollLabelColor(dayHighlight);

		centerPanel.add(columnLabel_blank);
		centerPanel.add(columnLabel1);
		centerPanel.add(columnLabel2);
		centerPanel.add(columnLabel3);
		centerPanel.add(columnLabel4);
		centerPanel.add(columnLabel5);
		centerPanel.add(columnLabel6);
		centerPanel.add(columnLabel7);

		initializeScrollButtons(centerPanel, dayHighlight);

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
		btnDetailView.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DetailedView(mainCalendar.getSelectedTimeSlot());
				
			}
		});

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

	private void setScrollLabelColor(int dayHighlight) {

		columnLabel1.setBackground(Color.LIGHT_GRAY);
		columnLabel2.setBackground(Color.LIGHT_GRAY);
		columnLabel3.setBackground(Color.LIGHT_GRAY);
		columnLabel4.setBackground(Color.LIGHT_GRAY);
		columnLabel5.setBackground(Color.LIGHT_GRAY);
		columnLabel6.setBackground(Color.LIGHT_GRAY);
		columnLabel7.setBackground(Color.LIGHT_GRAY);
		columnLabel1.setOpaque(true);
		columnLabel2.setOpaque(true);
		columnLabel3.setOpaque(true);
		columnLabel4.setOpaque(true);
		columnLabel5.setOpaque(true);
		columnLabel6.setOpaque(true);
		columnLabel7.setOpaque(true);

		switch (dayHighlight) {
		case 1:
			columnLabel1.setBackground(Color.WHITE);
			break;
		case 2:
			columnLabel2.setBackground(Color.WHITE);
			break;
		case 3:
			columnLabel3.setBackground(Color.WHITE);
			break;
		case 4:
			columnLabel4.setBackground(Color.WHITE);
			break;
		case 5:
			columnLabel5.setBackground(Color.WHITE);
			break;
		case 6:
			columnLabel6.setBackground(Color.WHITE);
			break;
		case 7:
			columnLabel7.setBackground(Color.WHITE);
			break;
		}

	}

	private void initializeScrollButtons(JPanel centerPanel, int dayHighlight) {
		int button_count = 0;
		for (int i = 0; i < SCROLL_GRID_SIZE; i++) {
			if (i % 8 == 0) {
				JLabel rowLabel_i = new JLabel(TimeSlot.convertSlotTime(i / 8));
				rowLabel_i.setHorizontalAlignment(SwingConstants.CENTER);
				centerPanel.add(rowLabel_i);
			} else {
				JButton btnNewButton_i = new JButton("New button");
				button_count++;
				if (i % 8 == dayHighlight) {
					btnNewButton_i.setText(String.valueOf(button_count));
				} else {
					btnNewButton_i.setText(String.valueOf(button_count));
				}

				scrollButtonArray[i] = btnNewButton_i;
				centerPanel.add(btnNewButton_i);
			}
		}
		
		updateScrollButtonColor(scrollButtonArray,dayHighlight);

	}

	private void updateScrollButtonColor(JButton[] scrollButtonArray, int dayHighlight) {

		for (int i = 0; i < SCROLL_GRID_SIZE; i++) {
			if(scrollButtonArray[i]!=null) {
			if (i % 8 == dayHighlight) {
				scrollButtonArray[i].setBackground(Color.WHITE);
			} else {
				scrollButtonArray[i].setBackground(Color.LIGHT_GRAY);
			}}

		}

	}

	public class TextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand() == "textDateField") {
				String dateString =textDateField.getText();
				String REGEX = "^[0-1]?[0-9]/[0-3]?[0-9]/[0-9][0-9][0-9][0-9]$";
				Pattern pattern = Pattern.compile(REGEX);
				Pattern splitChar = Pattern.compile("/");
				
				if (!(pattern.matcher(dateString).find())) {
					System.out.println("Invalid Date format");
				}else {
				String[] dateSplit = splitChar.split(dateString);
				
				String yearString = mainCalendar.adjustDateInput(dateSplit[2], dateSplit[0], dateSplit[1], "0");
				
				
				mainCalendar.selectDay(yearString);
				textDateField.setText(mainCalendar.getSelectedDay().textFieldFormat());
				}
				
				int dayHighlight = mainCalendar.getSelectedDay().getDay().get(Calendar.DAY_OF_WEEK);
				setScrollLabelColor(dayHighlight);
				updateScrollButtonColor(scrollButtonArray,dayHighlight);
				
			}

		}

	}

}
