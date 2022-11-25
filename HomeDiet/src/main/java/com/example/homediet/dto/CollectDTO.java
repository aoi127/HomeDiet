package com.example.homediet.dto;

import com.example.homediet.entity.Diet;
import lombok.Data;

import java.util.List;
@Data
public class CollectDTO {
    private List<Diet> list;
    private String time;
}
