package pl.polsl.shopserver.Auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;


public class FilterForRegistriation {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        var filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new Jwtfilter());
        filterRegistrationBean.setUrlPatterns(getUrlsForFilter());
        return filterRegistrationBean;
    }
    private ArrayList<String> getUrlsForFilter(){
        var arrayList=new ArrayList<String>();
        arrayList.add("/photo/addPhoto");
        return arrayList;
    }
}
