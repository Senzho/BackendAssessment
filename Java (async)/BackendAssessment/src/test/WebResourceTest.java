package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import AsynchronousExecution.WebResource;

class WebResourceTest {
	private WebResource webRsource;
	
	public WebResourceTest() {
		
	}

	@Test
	void testSaveFileAsyncPositive() {
		this.webRsource = new WebResource("https://en.wikipedia.org/wiki/Main_Page");
		boolean savedFile = this.webRsource.saveFileAsync();
		assertEquals(true, savedFile);
	}
	@Test
	void testSaveFileAsyncNegative() {
		this.webRsource = new WebResource("my name is victor");
		boolean savedFile = this.webRsource.saveFileAsync();
		assertEquals(false, savedFile);
	}
}
