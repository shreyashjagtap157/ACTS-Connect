package com.connect.acts.ActsConnectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(generator = "uuid2")
  @UuidGenerator
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int batchYear;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  @Enumerated(EnumType.STRING)
  private Course courseType; // New enum field

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BatchSemester batchSemester;

  @ManyToMany(mappedBy = "likedByUsers")
  private Set<Post> likedPosts = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Post> posts = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comments = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "user_followers",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "follower_id"))
  private Set<User> followers = new HashSet<>();

  @Column
  private String company;

  @Column
  private String profilePictureUrl;

  // Add any additional fields or methods if necessary
}