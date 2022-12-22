package com.stock.managementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/ajax")
@Controller
public class autocompleteController {

    @RequestMapping(value = "/autocomplete.do")
    public @ResponseBody Map<String, Object> autocomplete
            (@RequestParam Map<String, Object> paramMap) throws Exception{

        List<Map<String, Object>> resultList=null;
        paramMap.put("resultList", resultList);

        return paramMap;
    }
}
