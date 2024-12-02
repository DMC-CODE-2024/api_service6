package com.glocks.application.repository.app;

import com.glocks.application.entity.app.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
/*
    public User getByUsername(String userName);

    public User getById(long id);

    public List<User> getByUsertype_Id(long id);*/

}
