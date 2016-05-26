package sdk.components;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sdk.view.Filter;

public class FileChooser extends JFrame
{

	private JPanel contentPane;
	private JFileChooser chooser;
	private File file;

	/**
	 * Create the frame.
	 */
	public FileChooser(sdk.view.Filter filter, int type, ActionListener actionListener)
	{
		this.setFile(null);
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

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public JFileChooser getChooser()
	{
		return chooser;
	}
}
