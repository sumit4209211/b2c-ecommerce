package com.lucenesearchecommerce.service;

import java.util.List;

import com.lucenesearchecommerce.entity.Color;
import com.lucenesearchecommerce.repository.ColorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {
	
	@Autowired
	ColorRepository colorRepository;

	@Override
	public List<Color> findAll() {
		return colorRepository.findAll();
	}
	
}
