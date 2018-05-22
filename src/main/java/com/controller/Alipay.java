package com.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.model.Cart;
import com.model.Indent;
import com.model.IndentDetail;
import com.model.User;
import com.repository.*;
import com.sun.tools.javac.api.ClientCodeWrapper;
import com.util.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class Alipay {

    @Autowired
    private AlipayClient client;//支付宝请求sdk客户端
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IndentDetailRepository indentDetailRepository;
    @Autowired
    IndentRepository indentRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    CartRepository cartRepository;
    /**
     * 支付请求
     */
    @GetMapping("/alipay")
    @ResponseBody
    //@RequestMapping("/mypay")
    public String app(HttpSession httpSession) throws AlipayApiException {

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        User user1=(User)httpSession.getAttribute("user");
        User user=userRepository.findById(user1.getId()).get();
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
            IndentDetail indentDetail=new IndentDetail((int) indentDetailRepository.count()+1,map.get(key).getCount(),map.get(key).getCount()*map.get(key).getFood().getPrice(),null,false, map.get(key).getFood(), indent);
            indentDetailRepository.save(indentDetail);
        }
        for(int key:map.keySet()){
            cartRepository.delete(map.get(key));
        }
        AlipayTradePagePayRequest alipayRequest= new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new Date().toString();
        //付款金额，必填
        String total_amount = String.valueOf(indent.getSumPrice());
        //订单名称，必填
        String subject = "校园外卖";
        //商品描述，可空
        String body = "校园外卖预订";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    @PostMapping("/notify")
    public String notify(
            HttpServletRequest request, HttpSession session) throws AlipayApiException,Exception{
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        if(signVerified) {//验证成功
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                System.out.println("到此一游");
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }



        }else {//验证失败
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
        return "success";
    }

    @RequestMapping("returnurl")
    public String returnUrl(HttpServletRequest request,HttpSession session) throws Exception{

        return "forward:/order";
    }
}
