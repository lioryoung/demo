package com.controller;

import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IndentRepository indentRepository;
    @Autowired
    FoodIndentRepository foodIndentRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    PinIndentRepository pinIndentRepository;
    @Autowired
    IndentDetailRepository indentDetailRepository;

    @RequestMapping("/cart")
    public String toCart(HttpSession session,Model model){
        User user1=(User)session.getAttribute("user");
        User user=userRepository.findById(user1.getId()).get();
        Map<Integer,Cart> map=user.getCartMap();
        List<Cart> cartList=new ArrayList<>();
        System.out.println("购物车数量："+map.size());
        for(int key:map.keySet()){
            cartList.add(map.get(key));
        }
        model.addAttribute("cartList",cartList);
        return "cart";
    }
    @RequestMapping("/loginAction")
    @ResponseBody
    public boolean loginAction(HttpServletRequest request,HttpSession session){
        String phone=request.getParameter("phone");
        String pwd=request.getParameter("pwd");
        User user=userRepository.findByPhone(phone);
        if (user==null){
            return false;
        }
        session.setAttribute("user",user);
        return true;
    }
    @RequestMapping("/deliverOrder")
    public String toDeliverOrder(Model model,HttpSession session){

        List<Indent> list=indentRepository.findByState(2);
        User user1=(User)session.getAttribute("user");
        User user=userRepository.findById(user1.getId()).get();
        List<FoodIndent> list1=foodIndentRepository.findByUser(user);
        model.addAttribute("StuWaitIndent",list);
        model.addAttribute("StuFinishIndent",list1);
        return "deliverOrder";
    }
    @RequestMapping("/index")
    //跳转至首页然后再这里吧要显示的菜品加进来
    public String toIndex(Model model){

        Iterable<Food> iterable=foodRepository.findAll();
        List<Food> foodList=new ArrayList<>();
        iterable.forEach(single->foodList.add(single));
        model.addAttribute("foodList",foodList);
        return "index";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/order")
    public String toOrder(Model model,HttpSession session){
        User user1=(User)session.getAttribute("user");
        User user=userRepository.findById(user1.getId()).get();
        Map<Integer,Indent> map=user.getIndentMap();
        List<Indent> indentList=new ArrayList<>();
        for(int key:map.keySet()){
            indentList.add(map.get(key));
        }
        model.addAttribute("myOrder",indentList);
        return "order";
    }
    @RequestMapping("/orderdes/{indentId}")
    public String toOrderdes(@PathVariable int indentId,Model model){
        Indent indent=indentRepository.findById(indentId).get();
        model.addAttribute("currentIndent",indent);
        Map<Integer,IndentDetail> map=indent.getIndentDetailMap();
        List<IndentDetail> list=new ArrayList<>();
        for(int key:map.keySet()){
            list.add(map.get(key));
        }
        model.addAttribute("detailList",list);
        return "orderdes";
    }
    @RequestMapping("/orderdesForSchool")
    public String toOrderdesForSchool(){
        return "orderdesForSchool";
    }
    //跳转到个人主页并把user加到model
    @RequestMapping("/person")
    public String toPerson(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        model.addAttribute("user",user);
        return "person";
    }
    @RequestMapping("/waitOrder")
    public String toWaitOrder(){
        return "waitOrder";
    }

    @RequestMapping("/addCart")
    @ResponseBody
    public boolean addCart(@RequestBody List<TempCart> list,HttpSession session){
        Iterator<TempCart> iterator=list.iterator();
        while (iterator.hasNext()){
            TempCart tempCart=iterator.next();
            Food food=foodRepository.findById(tempCart.getId()).get();
            User user=(User)session.getAttribute("user");
            //User user=(User)session.getAttribute("user");
            Cart cart=new Cart(((int) cartRepository.count())+1,user,food,tempCart.getCount());
            cartRepository.save(cart);
        }
        return true;
    }
    @RequestMapping("/reAction")
    @ResponseBody
    public boolean reAction(HttpServletRequest request){
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String alipay=request.getParameter("alipay");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        System.out.println(address);
        User user=new User(name,address,alipay,password,phone);
        userRepository.save(user);
        System.out.println(user.getName());
        return true;
    }
    @RequestMapping("/wantDeliver/{indentId}")
    public String wantDeliver(@PathVariable int indentId,HttpSession session){
        User user1=(User)session.getAttribute("user");
        User user=userRepository.findById(user1.getId()).get();
        Indent indent=indentRepository.findById(indentId).get();
        indent.setDeliverType(true);
        indent.setState(2);
        indentRepository.save(indent);
        FoodIndent foodIndent=new FoodIndent((int)foodIndentRepository.count()+1, new Date(),false,false,user,indent);
        foodIndentRepository.save(foodIndent);
        return "forward:/deliverOrder";
    }
    @RequestMapping("/finishDeliver")
    @ResponseBody
    public boolean finishDeliver(HttpServletRequest request){
        int id=Integer.valueOf(request.getParameter("id"));
        FoodIndent foodIndent=foodIndentRepository.findById(id).get();
        foodIndent.setState(true);
        foodIndentRepository.save(foodIndent);
        Indent indent=foodIndent.getIndent();
        indent.setState(3);
        indentRepository.save(indent);
        return true;

    }
    @RequestMapping("/managerComfire")
    @ResponseBody
    public boolean manageComfire(HttpServletRequest request){
        int id=Integer.valueOf(request.getParameter("id"));
        Indent indent=indentRepository.findById(id).get();
        indent.setState(2);
        indentRepository.save(indent);
        return true;

    }

    @RequestMapping("/deleteCart/{cartId}")
    @ResponseBody
    public boolean manageComfire(@PathVariable int cartId){
        Cart cart=cartRepository.findById(cartId).get();
        cartRepository.delete(cart);
        return true;

    }
    @RequestMapping("/pin")
    public String toPin(Model model){
        Iterable<PinIndent> iterator=pinIndentRepository.findAll();
        List<PinIndent> list=new ArrayList<>();
        iterator.forEach(single->list.add(single));
        model.addAttribute("pinIndent",list);
        return "pin";
    }
    @RequestMapping("/addPin/{pinid}")
    public String addPin(@PathVariable int cartId){
        PinIndent pinIndent=pinIndentRepository.findById(cartId).get();
        pinIndent.setNowNum(pinIndent.getNowNum()+1);
        pinIndentRepository.save(pinIndent);
        return "pin";
    }
    @RequestMapping("/re")
    public String toRe(){
        return "re";
    }
    @RequestMapping("/commentAction")
    @ResponseBody
    public void comment(HttpServletRequest request){
        int id=Integer.valueOf(request.getParameter("id"));
        String comment=request.getParameter("comment");
        IndentDetail indentDetail=indentDetailRepository.findById(id).get();
        indentDetail.setComment(comment);
        indentDetail.setCommentState(true);
        indentDetailRepository.save(indentDetail);

    }

    @RequestMapping("/comment")
    public String toComment(Model model){
        List<IndentDetail> list=indentDetailRepository.findByCommentState(true);
        model.addAttribute("commentList",list);
        return "comment";
    }
    @RequestMapping("changePro")
    @ResponseBody
    public boolean changePro(HttpServletRequest request,HttpSession session){
        String name=request.getParameter("name");
        String alipay=request.getParameter("alipay");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        User user=(User)session.getAttribute("user");
        user.setName(name);
        user.setAddress(address);
        user.setAlipay(alipay);
        if(password!=null&&password.length()!=0) {
            user.setPassword(password);
        }
        userRepository.save(user);
        return true;

    }

}
