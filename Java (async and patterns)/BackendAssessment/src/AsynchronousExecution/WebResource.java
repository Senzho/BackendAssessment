package AsynchronousExecution;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WebResource {
	private String url;
	
	/***
	 * This method downloads a file from an URL setted in the class
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void getFile() throws FileNotFoundException, IOException {
		try (FileOutputStream out = new FileOutputStream("DownloadedFile.html")) {
			BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);
			}
		}
	}
	
	public WebResource (String url) {
		this.url = url;
	}
	
	/***
	 * This method executes GetFile method asynchronously
	 * @return boolean value indicating if file has been saved
	 */
	public boolean saveFileAsync () {
		boolean saved = false;
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			String result = "OK";
			try {
				getFile();
			} catch (IOException e) {
				result = "NOT_OK";
			}
			return result;
		});
		try {
			saved = future.get().equals("OK");
		} catch (InterruptedException | ExecutionException e) {
			saved = false;
		}
		return saved;
	}
}
