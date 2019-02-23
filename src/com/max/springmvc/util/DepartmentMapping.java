package com.max.springmvc.util;

import com.max.springmvc.restful.model.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DepartmentMapping {


     Map<String,String> map = new HashMap<>();
     {
        map.put("D001","HR");
        map.put("D002","Sales");
        map.put("D003","RD");
        map.put("D004","Engineer");
     }

    public String departmentMapping(String depId){
        return map.get(depId);
    }

}
