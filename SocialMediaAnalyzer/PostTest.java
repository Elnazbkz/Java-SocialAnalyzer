package SocialMediaAnalyzer;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class PostTest {

	@Test
	public void TestFormattedDateTime() {
		LocalDateTime datetime = LocalDateTime.of(2023,  8, 25, 15, 30);
		Post post = new Post(1, "Test Content", "Test Author", 10, 11, datetime);
		String expectedFormattedDateTime = "25/08/2023 15:30";
		
		assertEquals(expectedFormattedDateTime, post.getFormattedDateTime());
	}

}
