package SocialMediaAnalyzer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CsvParserTest {
	private List<Post> posts;

	@Before
	public void setUp() throws IOException {
		posts = CsvParser.parsePostsFromCsv();
	}
 	@Test
	public void testParsePostsFromCsv() {
		assertNotNull(posts);
		assertEquals(6, posts.size());
		
	//// Check post1 data
			Post Post1 = posts.get(0);
			assertEquals(20582, Post1.getId());
			assertEquals("Come and meet us at Building 14 of RMIT.", Post1.getContent());
			assertEquals("SD2C45", Post1.getAuthor());
			assertEquals(10, Post1.getLikes());
			assertEquals(24, Post1.getShares());
			LocalDateTime expectedDateTime = LocalDateTime.parse("12/05/2023 10:10", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	        assertEquals(expectedDateTime, Post1.getDateTime());
	}

}
