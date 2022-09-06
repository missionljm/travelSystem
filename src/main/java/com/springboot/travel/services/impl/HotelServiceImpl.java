package com.springboot.travel.services.impl;

import com.springboot.travel.dao.HotelMapper;
import com.springboot.travel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HotelService")
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;
}
