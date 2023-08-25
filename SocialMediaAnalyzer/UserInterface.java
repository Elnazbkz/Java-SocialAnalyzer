package SocialMediaAnalyzer;
import java.io.IOException; //thrown error when there's an issue with input/output operations
import java.time.LocalDateTime; // date and time without a time zone
import java.time.format.DateTimeFormatter; // format and parse dates and time based on selected pattern
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class UserInterface {
        private Scanner scanner;
        private SocialMediaAnalyzer analyzer;

        public UserInterface() {
            this.scanner = new Scanner(System.in);
            this.analyzer = new SocialMediaAnalyzer();
        }

        private void loadPostsFromCsv() {
            try {
                List<Post> posts = CsvParser.parsePostsFromCsv();
                analyzer.addAllPosts(posts);
            } catch (IOException e) {
                System.out.println("Error loading posts from CSV file: " + e.getMessage());
            }
        }

        private static void displayMainMenu() {
            System.out.println("---------------------------------------------------");
            System.out.println("> Select from main menu");
            System.out.println("----------------------------------------------------");
            System.out.println("1) Add a social media post");
            System.out.println("2) Delete an existing social media post");
            System.out.println("3) Retrieve a social media post");
            System.out.println("4) Retrieve the top N posts with most likes");
            System.out.println("5) Retrieve the top N posts with most shares");
            System.out.println("6) Exit");
            System.out.print("Please select: ");
        }

        //// render questions to add a post
        private static void addPost(Scanner scanner, SocialMediaAnalyzer analyzer) {
            int id;
            //// check the ID to be unique
            while (true) {
                System.out.print("Please provide the post ID: ");
                try {
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()

                    // Check if the ID is non-negative
                    if (id < 0) {
                        System.out.println("Invalid ID. Please provide a non-negative value.");
                        continue;
                    }
                    Post post = analyzer.retrievePost(id);
                    // Check if the ID already exists
                    if (post != null) {
                        System.out.println("A post with the provided ID already exists. Please choose a different ID.");
                        continue;
                    }
                    break; // ID is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please provide a valid integer ID.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            //scanner.nextLine(); // Consume the newline left by nextInt()
            System.out.print("Please provide the post content: ");
            String content = scanner.nextLine();
            System.out.print("Please provide the post author: ");
            String author = scanner.nextLine();

            int likes;
            while (true) {
                System.out.print("Please provide the number of likes of the post: ");
                try {
                    likes = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                    
                    // Check if likes are non-negative
                    if (likes < 0) {
                        System.out.println("Invalid number of likes. Please provide a non-negative value.");
                        continue;
                    }
                    
                    break; // Likes are valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please provide a valid integer value.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            int shares;
            while (true) {
                System.out.print("Please provide the number of shares of the post: ");
                try {
                    shares = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                    
                    // Check if shares are non-negative
                    if (shares < 0) {
                        System.out.println("Invalid number of likes. Please provide a non-negative value.");
                        continue;
                    }
                    
                    break; // Likes are valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please provide a valid integer value.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            LocalDateTime dateTime = null;
            while (true) {
                System.out.print("Please provide the date and time of the post in the format of DD/MM/YYYY HH:MM: ");
                String dateTimeStr = scanner.nextLine();
                
                try {
                    dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    break; // Break the loop if parsing succeeds
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format. Please provide the date and time in the format of DD/MM/YYYY HH:MM.");
                }
            }
            Post post = new Post(id, content, author, likes, shares, dateTime);
            analyzer.addPost(post);
            System.out.println("The post has been added to the collection!");
        }

        //// render questions to remove a post by ID
        private static void removePost(Scanner scanner, SocialMediaAnalyzer analyzer) {
            System.out.print("Please provide the post ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            //boolean deleted = analyzer.removePost(id);
            if(analyzer.removePost(id)) {
                System.out.println("The post has been removed from the collection!");
            }
            else {
                System.out.println("Sorry the post does not exist in the collection!");
            }
            
        }

        //// render questions to retreive a post by ID
        private static void retrievePost(Scanner scanner, SocialMediaAnalyzer analyzer) {
            System.out.print("Please provide the post ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            Post post = analyzer.retrievePost(id);
            if (post != null) {
                System.out.println("Retrieved post: " + post);
            } else {
                System.out.println("Sorry, the post does not exist in the collection!");
            }
        }

        //// render questions to retreive top n posts by likes
        private static void retrieveTopNPostsByLikes(Scanner scanner, SocialMediaAnalyzer analyzer) {
            System.out.print("Please specify the number of posts to retrieve (N): ");
            int n = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            List<Post> topPosts = analyzer.getTopNPostsByLikes(n);
            System.out.println("The top-" + n + " posts with the most likes are:");
            displayPosts(topPosts);
        }

        //// render questions to retreive top n posts by shares
        private static void retrieveTopNPostsByShares(Scanner scanner, SocialMediaAnalyzer analyzer) {
            System.out.print("Please specify the number of posts to retrieve (N): ");
            int n = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            List<Post> topPosts = analyzer.getTopNPostsByShares(n);
            System.out.println("The top-" + n + " posts with the most shares are:");
            displayPosts(topPosts);
        }

        /// Display post details
        private static void displayPosts(List<Post> posts) {
            for (int i = 0; i < posts.size(); i++) {
                System.out.println((i + 1) + ") " + posts.get(i));
            }
        }
        

        public void start() {
            loadPostsFromCsv(); // this returns the posts.
            System.out.println("   ");
            System.out.println("Welcome to Social Media Analyzer!");
            int choice;
            do {
                displayMainMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()
                try {
                    switch (choice) {
                        case 1:
                            addPost(scanner, analyzer);
                            break;
                        case 2:
                            removePost(scanner, analyzer);
                            break;
                        case 3:
                            retrievePost(scanner, analyzer);
                            break;
                        case 4:
                            retrieveTopNPostsByLikes(scanner, analyzer);
                            break;
                        case 5:
                            retrieveTopNPostsByShares(scanner, analyzer);
                            break;
                        case 6:
                            System.out.println("Thanks for using Social Media Analyzer!");
                            break;
                        default:
                            System.out.println("Invalid menu option. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (choice != 6);
            
            scanner.close();
        }

        
    }