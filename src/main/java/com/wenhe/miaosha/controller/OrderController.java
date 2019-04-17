package com.wenhe.miaosha.controller;

import com.wenhe.miaosha.model.MiaoshaUser;
import com.wenhe.miaosha.model.OrderInfo;
import com.wenhe.miaosha.redis.RedisService;
import com.wenhe.miaosha.result.CodeMsg;
import com.wenhe.miaosha.result.Result;
import com.wenhe.miaosha.service.GoodsService;
import com.wenhe.miaosha.service.MiaoshaUserService;
import com.wenhe.miaosha.service.OrderService;
import com.wenhe.miaosha.vo.GoodsVo;
import com.wenhe.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;

	@Autowired
	RedisService redisService;

	@Autowired
	OrderService orderService;

	@Autowired
	GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
									  @RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }

}
