import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CalendarObjects.CalendarTop;
import CalendarObjects.ClassEntry;
import CalendarObjects.Room;
import CalendarObjects.Teacher;
import CalendarObjects.TimeSlot;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textDateField;
	private CalendarTop mainCalendar;
	private static final int SCROLL_GRID_SIZE = 192;
	private JButton[] scrollButtonArray = new JButton[SCROLL_GRID_SIZE];

	JComboBox comboBox;
	JComboBox comboBox_1;
	JComboBox comboBox_2;
	JComboBox comboBox_3;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setupWindowListener();
		setBounds(100, 100, 1215, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new GridLayout(8, 1, 0, 0));

		// date box
		textDateField = new JTextField();
		textDateField.setActionCommand("textDateField");
		textDateField.addActionListener(new TextListener());
		leftPanel.add(textDateField);
		textDateField.setText(mainCalendar.getSelectedDay().textFieldFormat());

		// timeslot
		comboBox = new JComboBox(TimeSlot.getTimeArray());
		comboBox.setActionCommand("TimeComboBox");
		comboBox.addActionListener(new ComboBoxListener());
		leftPanel.add(comboBox);

		// room
		comboBox_1 = new JComboBox(mainCalendar.getRoomArray());
		comboBox_1.setActionCommand("RoomComboBox");
		comboBox_1.addActionListener(new ComboBoxListener());
		leftPanel.add(comboBox_1);

		// teacher
		comboBox_2 = new JComboBox(mainCalendar.getTeacherArray());
		leftPanel.add(comboBox_2);

		// classentry
		comboBox_3 = new JComboBox(mainCalendar.getClassEntryArray());
		leftPanel.add(comboBox_3);

		textDateField.setColumns(10);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(25, 8, 0, 0));

		String labelText[] = getScrollLabelText();
		columnLabel_blank = new JLabel(" ");
		columnLabel_blank.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel1 = new JLabel(labelText[0]);
		columnLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel2 = new JLabel(labelText[1]);
		columnLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel3 = new JLabel(labelText[2]);
		columnLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel4 = new JLabel(labelText[3]);
		columnLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel5 = new JLabel(labelText[4]);
		columnLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel6 = new JLabel(labelText[5]);
		columnLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		columnLabel7 = new JLabel(labelText[6]);
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
		btnAddButton.addActionListener(new PanelButtonListener());

		JButton btnRemoveButton = new JButton("Remove Event");
		btnRemoveButton.addActionListener(new PanelButtonListener());
		rightPanel.add(btnRemoveButton);

		JLabel lblSpacer1 = new JLabel("");
		rightPanel.add(lblSpacer1);

		JLabel lblSpacer2 = new JLabel("");
		rightPanel.add(lblSpacer2);

		JButton btnDetailView = new JButton("Detail view");
		rightPanel.add(btnDetailView);
		btnDetailView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DetailedView(mainCalendar.getSelectedTimeSlot());
				
			}
		});
	}


	private void setupWindowListener() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				mainCalendar.writeEventsToFile();
				dispose();
			}
			
		});
	}

	private String[] getScrollLabelText() {
		String[] weekLabels = mainCalendar.getSelectedWeekLabelText();
		return weekLabels;
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

	private void updateScrollLabelText() {
		String labelText[] = getScrollLabelText();

		columnLabel1.setText(labelText[0]);
		columnLabel2.setText(labelText[1]);
		columnLabel3.setText(labelText[2]);
		columnLabel4.setText(labelText[3]);
		columnLabel5.setText(labelText[4]);
		columnLabel6.setText(labelText[5]);
		columnLabel7.setText(labelText[6]);

	}

	private void initializeScrollButtons(JPanel centerPanel, int dayHighlight) {
		String[] buttonText = mainCalendar.getWeekRoomButtonText();
		int button_count = 0;
		for (int i = 0; i < SCROLL_GRID_SIZE; i++) {
			if (i % 8 == 0) {
				JLabel rowLabel_i = new JLabel(TimeSlot.convertSlotTime(i / 8));
				rowLabel_i.setHorizontalAlignment(SwingConstants.CENTER);
				centerPanel.add(rowLabel_i);
			} else {
				String buttonCommand = extractButtonCommand(buttonText[button_count]);
				String buttonDisplayText = extractButtonText(buttonText[button_count]);

				JButton btnNewButton_i = new JButton(buttonDisplayText);
				scrollButtonArray[i] = btnNewButton_i;
				btnNewButton_i.setActionCommand(buttonCommand);
				btnNewButton_i.addActionListener(new ScrollButtonListener());
				centerPanel.add(btnNewButton_i);
				button_count++;
			}
		}

		updateScrollButtonColor(dayHighlight);

	}

	private String extractButtonCommand(String buttonText) {
		int firstIndex = buttonText.indexOf(".");
		int secondIndex = buttonText.indexOf(".", firstIndex + 1);
		return buttonText.substring(0, secondIndex);
	}

	private String extractButtonText(String buttonText) {
		int firstIndex = buttonText.indexOf(".");
		int secondIndex = buttonText.indexOf(".", firstIndex + 1);
		return buttonText.substring(secondIndex + 1);

	}

	private void updateScrollButtonText() {
		String[] buttonText = mainCalendar.getWeekRoomButtonText();
		int button_count = 0;
		for (int i = 0; i < SCROLL_GRID_SIZE; i++) {
			if (i % 8 != 0) {
				String buttonCommand = extractButtonCommand(buttonText[button_count]);
				String buttonDisplayText = extractButtonText(buttonText[button_count]);
				scrollButtonArray[i].setText(buttonDisplayText);
				scrollButtonArray[i].setActionCommand(buttonCommand);
				button_count++;
			}

		}
	}

	private void updateScrollButtonColor(int dayHighlight) {
		int time = comboBox.getSelectedIndex();
		for (int i = 0; i < SCROLL_GRID_SIZE; i++) {
			if (scrollButtonArray[i] != null) {
				if (i % 8 == dayHighlight) {
					if(i/8==time) {
						scrollButtonArray[i].setBackground(Color.CYAN);
					}else {
					scrollButtonArray[i].setBackground(Color.WHITE);
					}
				} else {
					scrollButtonArray[i].setBackground(Color.LIGHT_GRAY);
				}
			}

		}

	}

	public class TextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand() == "textDateField") {
				String dateString = textDateField.getText();
				String REGEX = "^[0-1]?[0-9]/[0-3]?[0-9]/[0-9][0-9][0-9][0-9]$";
				Pattern pattern = Pattern.compile(REGEX);
				Pattern splitChar = Pattern.compile("/");

				if (!(pattern.matcher(dateString).find())) {
					System.out.println("Invalid Date format");
				} else {
					String[] dateSplit = splitChar.split(dateString);

					String yearString = mainCalendar.adjustDateInput(dateSplit[2], dateSplit[0], dateSplit[1], "0");

					mainCalendar.selectDay(yearString);
					textDateField.setText(mainCalendar.getSelectedDay().textFieldFormat());
				}
				// update GUI elements with data corresponding to new date
				int dayHighlight = mainCalendar.getSelectedDay().getDay().get(Calendar.DAY_OF_WEEK);
				updateScrollLabelText();
				setScrollLabelColor(dayHighlight);
				updateScrollButtonText();
				updateScrollButtonColor(dayHighlight);

			}

		}

	}

	public class ComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonCommand = e.getActionCommand();
			if (buttonCommand == "RoomComboBox") {
				Room roomSelection = (Room) comboBox_1.getSelectedItem();
				mainCalendar.changeSelectedRoom(roomSelection);
				updateScrollButtonText();
			}else if(buttonCommand== "TimeComboBox") {
				String yearString=buildYearString();
				Room roomSelection =(Room) comboBox_1.getSelectedItem();
				mainCalendar.selectTimeSlot(yearString, roomSelection);
				updateScrollButtonText();
				int dayHighlight = mainCalendar.getSelectedDay().getDay().get(Calendar.DAY_OF_WEEK);
				updateScrollButtonColor(dayHighlight);
			}

		}
	}
	
	private String buildYearString() {
		int index = comboBox.getSelectedIndex();
		String dateString = mainCalendar.getSelectedDay().getYearMonthDayHourFormat();
		int dateNumber = Integer.parseInt(dateString);
		dateNumber=dateNumber/100*100;
		dateNumber+=index;
		dateString=Integer.toString(dateNumber);
		return dateString;
	}

	public class ScrollButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Pattern splitCommand = Pattern.compile("\\.");
			String buttonCommand = e.getActionCommand();
			String[] buttonSplit = splitCommand.split(buttonCommand);
			mainCalendar.selectTimeSlot(buttonSplit[0], buttonSplit[1]);
			textDateField.setText(mainCalendar.getSelectedDay().textFieldFormat());
			comboBox.setSelectedIndex(Integer.parseInt(buttonSplit[0].substring(8)));
			int dayHighlight = mainCalendar.getSelectedDay().getDay().get(Calendar.DAY_OF_WEEK);
			setScrollLabelColor(dayHighlight);
			updateScrollButtonText();
			updateScrollButtonColor(dayHighlight);

		}

	}

	public class PanelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String buttonCommand = e.getActionCommand();
				if (buttonCommand == "Add Event") {
				String yearString = buildYearString();
				Room roomSelection =(Room) comboBox_1.getSelectedItem();
				mainCalendar.selectRoom(yearString, roomSelection);
				int time = comboBox.getSelectedIndex();
				ClassEntry classEntry=(ClassEntry) comboBox_3.getSelectedItem();
				Teacher teacher = (Teacher) comboBox_2.getSelectedItem();
				mainCalendar.getSelectedCalendarRoom().addTimeSlot(time, classEntry, teacher);
				updateScrollButtonText();
			} else if (buttonCommand == "Remove Event") {
				String yearString = buildYearString();
				Room roomSelection =(Room) comboBox_1.getSelectedItem();
				mainCalendar.selectRoom(yearString, roomSelection);
				int time = comboBox.getSelectedIndex();
				mainCalendar.getSelectedCalendarRoom().removeTimeSlot(time);
				
				updateScrollButtonText();

			}

		}

	}

}
