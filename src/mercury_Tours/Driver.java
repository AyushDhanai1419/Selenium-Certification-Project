package mercury_Tours;

import java.io.IOException;

public interface Driver {


	public void navigateToFirstUrl(String url) throws Exception;
	public void enterUsername(String locator, String value, String param);
	public void enterPassword(String locator, String value, String param);
	public void submit(String locator, String value);
	public void screenShot() throws IOException, InterruptedException;
	public void ClickOnNext(String locator, String value);
	public void FillingEntries(String locator, String value, String param);

	
	public void closeAllBrowsers() throws Exception;

}
