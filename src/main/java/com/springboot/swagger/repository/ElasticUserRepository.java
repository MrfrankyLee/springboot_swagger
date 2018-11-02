package com.springboot.swagger.repository;

import com.springboot.swagger.entity.ElasticUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaole
 * @date 2018/10/29
 * @Description
 */
@Repository
public interface ElasticUserRepository extends JpaRepository<ElasticUser,Long> {
}
