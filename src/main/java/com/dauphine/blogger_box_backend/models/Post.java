package com.dauphine.blogger_box_backend.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private UUID categoryId;
    private LocalDateTime createdDate;

    // Constructeur vide (nécessaire pour Spring)
    public Post() {}

    // Constructeur complet (corrige l'erreur de la ligne 32 du Controller)
    public Post(UUID id, String title, String content, UUID categoryId, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
    }

    // Getter manuel (corrige l'erreur de la ligne 25 du Controller)
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    // Autres getters nécessaires pour l'affichage
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public UUID getCategoryId() { return categoryId; }
}