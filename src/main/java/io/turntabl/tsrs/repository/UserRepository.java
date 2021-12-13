package io.turntabl.tsrs.repository;

import io.turntabl.tsrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    List<User> findAll();


    @Override
    void delete(User user);
}
