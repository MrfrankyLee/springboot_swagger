package com.springboot.swagger.mapper;

import com.springboot.swagger.pojo.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lixiaole
 * @date 2018/11/2
 * @Description
 */
@Repository
public interface OrdersMapper {
    List<Orders> findOrdersUserResultMap();
}
