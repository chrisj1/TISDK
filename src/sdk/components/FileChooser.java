package sdk.components;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A Generic File Chooser
 * @author Chris Jerrett
 *
 */
public class FileChooser extends JFrame
{

	private JPanel contentPane;
	private JFileChooser chooser;

	/**
	 * Creates a new File chooser
	 * @param filter the filter to filter files
	 * @param type the type of the dialog
	 * @param actionListener what to do when button is clicked
	 */
	public FileChooser(Filter filter, int type, ActionListener actionListener)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		chooser = new JFileChooser();
		chooser.setDialogType(type);
		chooser.addActionListener(actionListener);

		chooser.setFileFilter(filter);
		contentPane.add(chooser);

		setVisible(true);
	}
}
