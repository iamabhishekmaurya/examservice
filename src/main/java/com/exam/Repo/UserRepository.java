package com.exam.Repo;

import com.exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String userName);
    public User findByEmail(String email);
    public User findByPhone(String phone);
}
