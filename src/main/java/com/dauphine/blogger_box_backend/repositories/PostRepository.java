package com.dauphine.blogger_box_backend.repositories;

import com.dauphine.blogger_box_backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}