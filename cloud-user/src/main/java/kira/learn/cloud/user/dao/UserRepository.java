package kira.learn.cloud.user.dao;

import kira.learn.cloud.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 16:10
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {


    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 查询结果
     */
    Optional<User> getByUsername(String userName);


}