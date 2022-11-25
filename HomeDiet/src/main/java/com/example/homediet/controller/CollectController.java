package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.dto.CollectDTO;
import com.example.homediet.entity.Collect;
import com.example.homediet.entity.Diet;
import com.example.homediet.entity.Foods;
import com.example.homediet.service.ICollectService;
import com.example.homediet.service.IDietService;
import com.example.homediet.service.IFoodsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzr
 * @since 2022-11-25
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private ICollectService collectService;
    @Autowired
    private IDietService dietService;
    @Autowired
    private IFoodsService foodsService;

    @PostMapping("/add")
    @ApiOperation("输入用户的openid，和食谱id，收藏食谱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid"),
            @ApiImplicitParam(name = "dietid", value = "dietid")
    })
    public BaseResult addCollect(@RequestParam(value = "openid") String openid,@RequestParam(value = "dietid") List<Integer> dietid
    ) {
        for(Integer id:dietid){
            Diet diet=dietService.getById(id);
            if(diet!=null){
                QueryWrapper<Collect>wrapper=new QueryWrapper<>();
                wrapper.eq("openid",openid).eq("dietid",id);
                if(collectService.count(wrapper)>0){
                    continue;
                }
                Collect collect=new Collect();
                collect.setOpenid(openid);
                collect.setDate(diet.getDate());
                collect.setDietid(id);
                collectService.save(collect);
            }
        }
        return BaseResult.success();
    }

    @DeleteMapping("/remove")
    @ApiOperation("输入用户的openid，和食谱id，取消收藏食谱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid"),
            @ApiImplicitParam(name = "dietid", value = "dietid")
    })
    public BaseResult removeCollect(@RequestParam(value = "openid") String openid,@RequestParam(value = "dietid") List<Integer> dietid
    ) {
        for(Integer id:dietid){
           Collect collect=collectService.getOne(new QueryWrapper<Collect>().eq("openid",openid).eq("dietid",id));
           if(collect!=null){
               collectService.removeById(collect.getId());
           }
        }
        return BaseResult.success();
    }

    @GetMapping("/get")
    @ApiOperation("输入用户的openid，查询用户收藏的食谱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid")
    })
    public BaseResult getCollect(@RequestParam(value = "openid") String openid
    ) {
        List<CollectDTO>collects=new ArrayList<>();
        //获取时间
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        if (openid != null) {
            queryWrapper.eq("openid", openid);
        }
        queryWrapper.select("distinct date").orderByAsc("date");
        List<Collect> list=collectService.list(queryWrapper);
        for(Collect collectTime:list){
            CollectDTO collectDTO=new CollectDTO();
            collectDTO.setTime(collectTime.getDate());
            List<Diet>diets=new ArrayList<>();
            //按时间查询收藏关系
            QueryWrapper<Collect>queryWrapper2=new QueryWrapper<>();
            if (openid != null) {
                queryWrapper2.eq("openid", openid);
            }
            queryWrapper2.eq("date",collectTime.getDate());
            List<Collect>collectList=collectService.list(queryWrapper2);
            //根据收藏关系查询食谱
            for(Collect collect:collectList){
                QueryWrapper<Diet> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("id",collect.getDietid());
                Diet diet=dietService.getOne(queryWrapper3);
                QueryWrapper<Foods> q = new QueryWrapper<>();
                diet.setFood(foodsService.getOne(q.eq("id",diet.getFoodid())));
                diets.add((diet));
            }
            collectDTO.setList(diets);
            collects.add(collectDTO);
        }

        return BaseResult.successWithData(collects);
    }
}

