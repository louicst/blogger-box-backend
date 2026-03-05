package com.dauphine.blogger_box_backend.repositories;

import com.dauphine.blogger_box_backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    // 1. On ajoute JOIN FETCH ici pour charger la catégorie en même temps que le post
    @Query("""
        SELECT post 
        FROM Post post 
        JOIN FETCH post.category
        WHERE UPPER(post.title) LIKE UPPER(CONCAT('%', :value ,'%'))
        OR UPPER(post.content) LIKE UPPER(CONCAT('%', :value ,'%'))
    """)
    List<Post> findAllByTitleOrContent(@Param("value") String value);

    // 2. On crée cette méthode pour le "Tout récupérer" sans faire 500 requêtes
    @Query("SELECT p FROM Post p JOIN FETCH p.category")
    List<Post> findAllWithCategory();

    @Query("SELECT p FROM Post p JOIN FETCH p.category WHERE p.category.id = :categoryId")
    List<Post> findAllByCategoryId(@Param("categoryId") UUID categoryId);
}