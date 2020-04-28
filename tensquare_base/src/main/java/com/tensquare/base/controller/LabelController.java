package com.tensquare.base.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.tensquare.base.Service.LabelService;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/label")
@RefreshScope
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;

    @Value("${ip}")
    private String ip;

    @GetMapping()
    public Result findAll(){
        System.out.println("ip为："+ ip);
        // 获取头信息
        String header = request.getHeader("Authorization");
        System.out.println(header);

        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId ){
        System.out.println("222222222222222222");
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{labelId}")
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{labelId}")
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search")
    public Result findSearch( @RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery( @RequestBody Label label, @PathVariable int page,  @PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }
}
