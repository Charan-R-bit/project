package com.charu.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charu.Repository.CharuRepo;
import com.charu.entity.CharuEntity;
import com.charu.exception.CharuException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/charu")
public class CharuController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CharuController.class);
	@Autowired
	CharuRepo rep;
//	CharuEntity entity;

	@GetMapping("/get")
	public List<CharuEntity> get() {
		return rep.findAll();
	}

	@GetMapping("/get/{id}")
	public Optional<CharuEntity> getall(@PathVariable int id) throws CharuException {
		Optional<CharuEntity> view = rep.findById(id);
		if (view.isEmpty()) {
			throw new CharuException(id + ":  ID not found!!! ");
		} else {
			return rep.findById(id);
		}
//		try
//		{
//			return rep.findById(id);
//		}
//		catch(Exception e)
//		{
//			throw new CharuException("null pointer");
//		}
	}

	@PostMapping("/post")
	public CharuEntity post(@RequestBody @Valid CharuEntity charu) {
		LOGGER.info("Art details  added");
		return rep.save(charu);

	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		rep.deleteById(id);
		LOGGER.info("Art deleted");

	}

	@PutMapping("/put/{id}")
	public void put(@PathVariable int id, @Valid @RequestBody CharuEntity entity) {
		entity.setId(entity.getId());
		entity.setName(entity.getName());
		entity.setPrice(entity.getPrice());
		entity.setAbout(entity.getAbout());
		LOGGER.info("Update Complete ");
		rep.save(entity);

	}

}
