package com.yunus.web.webyunus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PostService extends JpaRepository<Post , Integer> {
}
