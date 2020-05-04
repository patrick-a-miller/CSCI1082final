import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CalendarObjects.*;

import javax.swing.JTextArea;

public class DetailedView extends JFrame {

	private JPanel contentPane;

	private TimeSlot timeslot;
	private CalendarTop cal;
	private Teacher teacher;
	private CalendarRoom room;
	private ClassEntry classentry;
	
	
	public TimeSlot getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(TimeSlot timeslot) {
		this.timeslot = timeslot;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DetailedView(TimeSlot slot) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		JTextArea textArea = new JTextArea();
		panel.add(textArea);
		textArea.setText(slot.getRoom().toString() + "\n" + slot.getTeacher().toString() + "\n" + slot.getClassEntry().detailView());
		
	}

	
}
