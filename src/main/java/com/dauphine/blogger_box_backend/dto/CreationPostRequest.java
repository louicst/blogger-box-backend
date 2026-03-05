package com.dauphine.blogger_box_backend.dto;
import java.util.UUID;

public record CreationPostRequest(String title, String content, UUID categoryId) {}