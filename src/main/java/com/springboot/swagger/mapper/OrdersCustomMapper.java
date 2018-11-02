package com.springboot.swagger.mapper;

import com.springboot.swagger.pojo.OrdersCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lixiaole
 * @date 2018/11/2
 * @Description
 */
@Repository
public interface OrdersCustomMapper {
    /** 查询订单，关联查询用户信息 */
    public List<OrdersCustom> findOrdersUser();

}
