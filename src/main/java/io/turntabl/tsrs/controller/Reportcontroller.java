package io.turntabl.tsrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api")
@RestController
public class Reportcontroller {
    @Autowired
    private RestTemplate restTemplate;
    private static String url = "https://exchange.matraining.com/orderbook/";
    private static String url_userportfolio="http://localhost:8080/api/v1/auth/portfolio";

    @GetMapping("/{product}/{order_type}")
    public List<List> BuyOrderProduct(@PathVariable String product,@PathVariable String order_type){
        List forObject = restTemplate.getForObject(url+"/"+product+"/"+order_type, List.class);
        return Arrays.asList(forObject);
    }

    @GetMapping("/user/{username}")
    public String getUserPortfolio(@PathVariable String username){
          return "jee";
    }

    @GetMapping("/allPortfolio")
    public List<List> getAllUsersPortfolio(){
        List forObject = restTemplate.getForObject(url_userportfolio, List.class);
        return Arrays.asList(forObject);
    }

    @GetMapping("/portfolio/{user_id}")
    public List<List> getPortfolioByUserId(@PathVariable Long user_id){
        List forObject = restTemplate.getForObject(url_userportfolio + "/" + user_id, List.class);
        return Arrays.asList(forObject);
    }

}
