package SocialMediaAnalyzer;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SocialMediaAnalyzerTest {
	private SocialMediaAnalyzer analyzer;

	@Before
	public void setUp() throws Exception {
		analyzer = new SocialMediaAnalyzer();
	}
	
	@Test
	public void testGetAllPosts() {
		List<Post> allPosts = analyzer.getAllPosts();
		assertNotNull(allPosts);
	}

	@Test
	public void testAddNewPost() {
		Post post = new Post(133, "Content test", "Test Author", 3000, 30001, LocalDateTime.now());
		analyzer.addPost(post);
		List<Post> posts = analyzer.getAllPosts();
		assertTrue(posts.contains(post));
	}
	
	@Test
	public void testAddAllPosts() {
		List<Post> newPosts = new ArrayList<>();
		newPosts.add(new Post(134, "Content Test 1", "Author 1", 10, 11, LocalDateTime.now()));
		newPosts.add(new Post(135, "Content Test 2", "Author 2", 12, 13, LocalDateTime.now()));
		analyzer.addAllPosts(newPosts);
		List<Post> posts = analyzer.getAllPosts();
		assertTrue(posts.containsAll(newPosts));
	}
	
	@Test
	public void testRemovePost() {
		Post post = new Post(1, "Test Content", "Test Author", 20, 21, LocalDateTime.now());
		analyzer.addPost(post);
		assertTrue(analyzer.removePost(1));
		assertFalse(analyzer.removePost(2));
	}
	
	@Test
	public void TestRetrievedPost() {
		Post post = new Post(1, "Test Content", "Test Author", 20, 21, LocalDateTime.now());
		analyzer.addPost(post);
		
		Post retrievedPost = analyzer.retrievePost(1);
		assertNotNull(retrievedPost);
		assertEquals(post, retrievedPost);
		assertNull(analyzer.retrievePost(2));
	}
	
	@Test
	public void testGetTopNPostsByLikes() {
		Post TopPost1 = new Post(1, "Post with 100K Likes", "Author Test", 100000, 100001, LocalDateTime.now());
		Post TopPost2 = new Post(2, "Post with 68K Likes", "Author Test", 68000, 68001, LocalDateTime.now());
		analyzer.addPost(TopPost1);
		analyzer.addPost(TopPost2);
		
		List<Post> topPosts = analyzer.getTopNPostsByLikes(2);
		
		assertEquals(2, topPosts.size());
		assertEquals(TopPost1, topPosts.get(0));
		assertEquals(TopPost2, topPosts.get(1));
	}
	
	@Test
	public void testGetTopNPostsByShares() {
		Post TopPost1 = new Post(1, "Post with 100K Shares", "Author Test", 2, 100000, LocalDateTime.now());
		Post TopPost2 = new Post(2, "Post with 68K Shares", "Author Test", 2, 68000, LocalDateTime.now());
		analyzer.addPost(TopPost1);
		analyzer.addPost(TopPost2);
		
		List<Post> topPosts = analyzer.getTopNPostsByShares(2);
		
		assertEquals(2, topPosts.size());
		assertEquals(TopPost1, topPosts.get(0));
		assertEquals(TopPost2, topPosts.get(1));
	}

}
