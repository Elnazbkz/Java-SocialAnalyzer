package SocialMediaAnalyzer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// define a Post class based on structure of the excel file data
public class Post {
    private int id;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private LocalDateTime dateTime;

    // constructor
    public Post(int id, String content, String author, int likes, int shares, LocalDateTime dateTime) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        this.dateTime = dateTime;
    }

    ///// getters
    public int getId() { // returns Post ID
        return id;
    }

    public String getContent() { // returns Post Content
        return content;
    }

    public String getAuthor() { // returns Post Author
        return author;
    }

    public int getLikes() { // returns Post Likes count
        return likes;
    }

    public int getShares() { // returns Post Shares count
        return shares;
    }

    public LocalDateTime getDateTime() { 
        return dateTime;
    }

    //// Setters
    public void setId(int id) { // set Post ID
        this.id = id;
    }

    public void setContent(String content) { // set Post Content
        this.content = content;
    }

    public void setAuthor(String author) { // set Post Author
        this.author = author;
    }

    public void setLikes(int likes) { // set Post Likes count
        this.likes = likes;
    }

    public void setShares(int shares) { // set Post Shares count
        this.shares = shares;
    }

    public void setDateTime(LocalDateTime dateTime) {  // set date and time
        this.dateTime = dateTime;
    }

    public String getFormattedDateTime() { // format date and time to selected pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return id + " | " + content + " | " + likes;
    }
}