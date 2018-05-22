package com.demo;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.model.*;
import com.repository.*;
import com.util.AlipayConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	FoodIndentRepository foodIndentRepository;
	@Autowired
	FoodRepository foodRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	IndentRepository indentRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	private IndentDetailRepository indentDetailRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public  void test(){
		User user=userRepository.findById(1).get();
		Food food=foodRepository.findById(1).get();
		Cart cart=new Cart(1,user,food,3);
		cartRepository.save(cart);
	}
	@Test
	public void getFoodList(){


	}
	@Test
	public void getCartList(){
		User user=userRepository.findById(1).get();
		Map<Integer,Cart> map=user.getCartMap();
		System.out.println(map.get(1).getCount());
	}
	@Test
	public void insertIndent(){
		User user=userRepository.findById(1).get();
		Indent indent=new Indent(1,user,new Date(),14,false,1);
		indentRepository.save(indent);
	}
	@Test
	public void getIndentDetail(){
		Indent indent=indentRepository.findById(3).get();
		Map<Integer,IndentDetail> map=indent.getIndentDetailMap();
		for(int key:map.keySet()){
			System.out.println(map.get(key).getSumPrice());
		}
	}

	@Test
	public void getWaitIndent(){
		User user=userRepository.findById(1).get();
		List<FoodIndent> list=foodIndentRepository.findByUser(user);
		Iterator<FoodIndent> indentIterator=list.iterator();
		while(indentIterator.hasNext()){
			System.out.println(indentIterator.next().getDate());
		}
	}
	@Test
	public void getIndentUser(){
		FoodIndent foodIndent=foodIndentRepository.findById(1).get();
		System.out.println(foodIndent.getIndent().getSumPrice());
	}
	@Test
	public void addCart(){

		Food food=foodRepository.findById(1).get();
		User user=userRepository.findById(1).get();
		Cart cart=new Cart(((int) cartRepository.count())+1,user,food,2);
		cartRepository.save(cart);
	}

	@Test
	public void payTest(){
		User user=userRepository.findById(1).get();
		long a=userRepository.count();
		System.out.println(a);
		Map<Integer,Cart> map=user.getCartMap();
		int sum=0;
		for(int key:map.keySet()){
			sum=sum+map.get(key).getFood().getPrice()*map.get(key).getCount();
		}
		Indent indent=new Indent((int)indentRepository.count()+1,user,new Date(),sum,false,1);
		System.out.println(indent.getId());
		indentRepository.save(indent);
		for(int key:map.keySet()){
			IndentDetail indentDetail=new IndentDetail((int) indentDetailRepository.count()+1,map.get(key).getCount(),map.get(key).getCount()*map.get(key).getFood().getPrice(),null,false, foodRepository.findById(map.get(key).getId()).get(), indent);
			indentDetailRepository.save(indentDetail);
		}
		for(int key:map.keySet()){
			cartRepository.delete(map.get(key));
		}
	}
	@Test
	public void deliver(){
		User user=userRepository.findAll().iterator().next();
//		Indent indent=indentRepository.findById(1).get();
//		indent.setDeliverType(true);
//		indent.setState(2);
//		indentRepository.save(indent);
//		FoodIndent foodIndent=new FoodIndent((int)foodIndentRepository.count()+1, new Date(),false,false,user,indent);
//		foodIndentRepository.save(foodIndent);
	}
	@Test
	public void createUser(){
		User user=new User("tony","hz","1234","pass","1234");
		userRepository.save(user);
	}
}
