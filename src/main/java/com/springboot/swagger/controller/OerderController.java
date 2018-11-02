package com.springboot.swagger.controller;

import com.springboot.swagger.mapper.OrdersMapper;
import com.springboot.swagger.pojo.Orders;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixiaole
 * @date 2018/11/2
 * @Description
 */
@RequestMapping("/orders")
@RestController
@Api(tags="订单API")
public class OerderController {
   /* @Resource
    private OrdersCustomMapper ordersCustomMapper;
*/
    @Resource
    private OrdersMapper ordersMapper;

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public List<Orders> findAll(){
        return ordersMapper.findOrdersUserResultMap();
    }
}
