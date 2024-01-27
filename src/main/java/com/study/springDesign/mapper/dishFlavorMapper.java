package com.study.springDesign.mapper;

import java.util.List;
import java.util.Map;

public interface dishFlavorMapper {
    List<Map<String,Object>> selectFlavorByIdStep2(Long id);
}