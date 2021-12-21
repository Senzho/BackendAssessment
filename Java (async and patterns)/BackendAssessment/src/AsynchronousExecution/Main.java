package AsynchronousExecution;

import java.util.Scanner;

public class Main {
	public void saveFile() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter url: ");
		String url = scanner.nextLine();
		scanner.close();
		WebResource webResource = new WebResource(url);
		boolean result = webResource.saveFileAsync();
		if (result) {
			System.out.println("File saved");
		} else {
			System.out.println("File not saved");
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.saveFile();
	}

}
