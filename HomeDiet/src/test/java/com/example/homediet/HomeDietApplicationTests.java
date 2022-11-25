package com.example.homediet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.dto.CollectDTO;
import com.example.homediet.entity.Collect;
import com.example.homediet.entity.Diet;
import com.example.homediet.entity.Foods;
import com.example.homediet.mapper.DietMapper;
import com.example.homediet.mapper.FoodsMapper;
import com.example.homediet.service.ICollectService;
import com.example.homediet.service.IDietService;
import com.example.homediet.service.IFoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class HomeDietApplicationTests {
    @Autowired
    private DietMapper dietMapper;

    @Autowired
    private FoodsMapper foodsMapper;

    @Autowired
    private ICollectService collectService;
    @Autowired
    private IDietService dietService;
    @Autowired
    private IFoodsService foodsService;
    @Test
    void collectTest(){
        String openid="1777";

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
        System.out.println(collects);

    }

    @Test
    void insertcho() {
        int fat = 43;
        int cho = 305;
        int pro = 90;
        int cal = 1969;

        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        //30%、40%、30%
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        //早餐
        queryWrapper.eq("kind","谷薯芋、杂豆、主食")
                .eq("type",1)
                .between("calorie",(double)0,(double)350)
                .between("carb",25.0,100.0);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());
        //计算能吃多少克
        Foods bre_cho= list.get(num);
        int gram = (int)((cho*0.4/list.get(num).getCarb())*100);
        //插入
        Diet bre = new Diet();
        bre.setFoodid(bre_cho.getId().toString());
        bre.setDate(format.format(date));
        bre.setGram(gram);
        bre.setTime("午餐");
        bre.setOpenid("1777");
        System.out.println(list.get(num));
        System.out.println(gram);
        dietMapper.insert(bre);


    }
    @Test
    void insertpro()
    {
        int pro = 90;
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind","蛋类、肉类及制品")
                .eq("type",1)
                .between("calorie",(double)0,(double)350)
                .between("protein",13.0,100.0)
                .between("fat",0.0,13.0);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());
        //减去碳水的蛋白质
        QueryWrapper<Diet> qw = new QueryWrapper<>();
        qw.eq("openid","1777").eq("date",format.format(date)).eq("time","午餐");
        List<Diet> dlist = dietMapper.selectList(qw);
        System.out.println(dlist);
        //计算主食蛋白质
        Foods bre_cho = foodsMapper.selectById(dlist.get(0).getFoodid());
        double pro_Of_cho = bre_cho.getProtein()/100*dlist.get(0).getGram();

        Foods bre_pro= list.get(num);
        int gram = (int)(((pro*0.4-pro_Of_cho)/list.get(num).getProtein())*100);
        System.out.println(list.get(num));
        System.out.println(gram);
        //插入
        Diet bre = new Diet();
        bre.setFoodid(list.get(num).getId().toString());
        bre.setGram(gram);
        bre.setTime("晚餐");
        bre.setOpenid("1777");
        bre.setDate(format.format(date));
        dietMapper.insert(bre);
    }

    @Test
    void insertfat(){
        int fat = 43;
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind","食用油、油脂及制品")
                     .eq("type",1);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());

        //获取早餐中碳水、蛋白质的脂肪含量
        QueryWrapper<Diet> qw = new QueryWrapper<>();
        qw.eq("openid","1777").eq("date",format.format(date)).eq("time","午餐");
        List<Diet> dlist = dietMapper.selectList(qw);
        Foods bre_cho = foodsMapper.selectById(dlist.get(0).getFoodid());
        Foods bre_pro = foodsMapper.selectById(dlist.get(1).getFoodid());
        double fat_of_bre =  bre_cho.getFat()+bre_pro.getFat();

        Foods bre_fat= list.get(num);
        int gram = (int)(((fat*0.4-fat_of_bre)/list.get(num).getFat())*100);
        System.out.println(bre_fat);
        System.out.println(gram);

        //插入
        Diet bre = new Diet();
        bre.setFoodid(bre_fat.getId().toString());
        bre.setGram(gram);
        bre.setTime("晚餐");
        bre.setOpenid("1777");
        bre.setDate(format.format(date));
        dietMapper.insert(bre);

    }
}
