import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetDicom {
	private static final Log logger = LogFactory.getLog(GetDicom.class);
		
	public void writeLocalFile(String location, InputStream input) throws IOException
	{
		FileOutputStream out = new FileOutputStream(new File(location));
		IOUtils.copy(input, out);
		out.close();
	}
}
