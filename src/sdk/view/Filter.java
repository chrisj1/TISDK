package sdk.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Filter extends FileFilter 
{

	@Override
	public boolean accept(File file) 
	{
		return file.getName().contains(".dat") || file.isDirectory();
	}

	@Override
	public String getDescription() {
		return "";
	}
}
