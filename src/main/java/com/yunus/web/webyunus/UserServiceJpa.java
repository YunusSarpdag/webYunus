package com.yunus.web.webyunus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServiceJpa extends JpaRepository<User , Integer> {
}
