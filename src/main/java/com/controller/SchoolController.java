package com.controller;

import com.model.*;
import com.repository.FoodIndentRepository;
import com.repository.FoodRepository;
import com.repository.IndentRepository;
import com.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SchoolController {

    @Autowired
    IndentRepository indentRepository;

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    FoodIndentRepository foodIndentRepository;
    @Autowired
    SchoolRepository schoolRepository;

    @RequestMapping("/SchoolLoginAction")
    @ResponseBody
    public boolean loginAction(HttpServletRequest request,HttpSession session){
        int phone=Integer.valueOf(request.getParameter("phone"));
        String pwd=request.getParameter("pwd");
        School school=schoolRepository.findById(phone).get() ;
        session.setAttribute("school",school);
        return true;
    }
    @RequestMapping("schoolLogin")
    public String schoolLogin(){
        return "SchoolLogin";
    }
    @RequestMapping("/schoolOrder")
    public String toSchoolOrder(Model model){
        Iterable<Indent> indents=indentRepository.findAll();
        List<Indent> list=new ArrayList<>();
        indents.forEach(single->list.add(single));
        model.addAttribute("schoolOrderList",list);
        return "schoolOrder";
    }
    @RequestMapping("/schoolorderdes/{indentId}")
    public String toSchoolOrderdes(@PathVariable int indentId, Model model){
        Indent indent=indentRepository.findById(indentId).get();
        model.addAttribute("schoolIndent",indent);
        Map<Integer,IndentDetail> map=indent.getIndentDetailMap();
        List<IndentDetail> list=new ArrayList<>();
        for(int key:map.keySet()){
            list.add(map.get(key));
        }
        model.addAttribute("schoolIndentDes",list);
        return "orderdesForSchool";
    }
    @RequestMapping("/foodmanager")
    public String tomanager(Model model){
        Iterable<Food> iterable=foodRepository.findAll();
        List<Food> list=new ArrayList<>();
        iterable.forEach(single->list.add(single));
        model.addAttribute("managerFoodList",list);
        return "foodmanage";
    }
    @RequestMapping("/studeliverManager")
    public String toStuDeliMan(Model model){
        List<FoodIndent> list=foodIndentRepository.findByState(true);
        model.addAttribute("stuDelManList",list);
        return "studeliverManager";
    }
    @RequestMapping("/addFood")
    @ResponseBody
    public void addFood(HttpServletRequest httpServletRequest){
        int price=Integer.valueOf(httpServletRequest.getParameter("price"));
        String name=httpServletRequest.getParameter("name");
        int num=(int)foodRepository.count()+1;
        System.out.println("到此一游");
        Food food=new Food(num,name,price,"~/img/food"+num+".png");
        foodRepository.save(food);
    }

}
