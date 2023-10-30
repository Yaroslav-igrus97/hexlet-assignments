package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return posts.get(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        Post oldPost = posts.get(id);
        if (oldPost != null) {
            oldPost.setBody(post.getBody());
            oldPost.setTitle(post.getTitle());
            posts.set(id, oldPost);
            return oldPost;
        } else
            return null;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id) {
        posts.remove(id);
    }
    // END
}
