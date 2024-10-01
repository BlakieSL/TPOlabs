package com.example.lab04blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);
        ArticleRepository articleRepository = context.getBean(ArticleRepository.class);
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);
//initialize roles
        Set<Role> roles = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            Role role = new Role();
            role.setName("Role " + i);
            roleRepository.save(role);
            roles.add(role);
        }
        List<Role> rolesList = new ArrayList<>(roles);
//initialize users
        Set<User> users = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            User user = new User();
            user.setEmail("user" + i + "@gmail.com");
            int amount = random.nextInt(6);
            Set<Role> randomRoles = new HashSet<>();
            for (int j = 0; j < amount; j++) {
                Role randomRole = rolesList.get(random.nextInt(rolesList.size()));
                randomRoles.add(randomRole);
            }
            user.setRoles(randomRoles);
            userRepository.save(user);
            users.add(user);
        }
//initialize blogs
        Set<Blog> blogs = new HashSet<>();
        int userIndex = 0;
        for (User user : users) {
            Blog blog = new Blog();
            blog.setName("Blog " + (++userIndex));

            blog.setManager(user);
            user.setManagedBlog(blog);

            blogRepository.save(blog);
            userRepository.save(user);

            blogs.add(blog);
        }
//initialize articles
        List<Article> articles = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Article article = new Article();
            article.setTitle("Article " + i);
            articleRepository.save(article);
            articles.add(article);
        }
        for (User user : users) {
            int articlesCount = random.nextInt(6);
            for (int i = 0; i < articlesCount; i++) {
                int articleIndex = random.nextInt(articles.size());
                Article article = articles.get(articleIndex);
                article.setAuthor(user);
                user.getArticles().add(article);
            }
            userRepository.save(user);
        }
        for (Blog blog : blogs) {
            int articlesCount = random.nextInt(6);
            for (int i = 0; i < articlesCount; i++) {
                int articleIndex = random.nextInt(articles.size());
                Article article = articles.get(articleIndex);
                article.setBlog(blog);
                blog.getArticles().add(article);
            }
            blogRepository.save(blog);
        }


       for(Article article : articleRepository.findAllArticlesByAuthorId(1L)){
           Long blogId = (article.getBlog() == null) ? null : article.getBlog().getId();
           System.out.println(article.getId() + " " + article.getTitle() + " " +
                   article.getAuthor().getId() + " " + blogId);
       }
       articleRepository.removeAllArticlesByAuthorId(1L);
       //all other repositories have same methods;
        for(Role role : roleRepository.findAllRolesByUserId(1L)){
            System.out.println(role.getId() + " " + role.getName());
        }
        roleRepository.removeRoleById(1L);
    }
}
