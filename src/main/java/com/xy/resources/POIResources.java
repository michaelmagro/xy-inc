package com.xy.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.domain.POI;
import com.xy.repository.POIRepository;

@RequestMapping("/poi")
@RestController
public class POIResources {
	
	@Autowired
	private POIRepository poiRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<POI> list() {
		return poiRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void save(@Valid @RequestBody POI poi) {
		poiRepository.save(poi);
	}
	
	@RequestMapping(value = "/listByProximity/{x}/{y}/{range}", method = RequestMethod.GET)
	public List<POI> listByProximity(@PathVariable("x") Integer x, @PathVariable("y") Integer y, @PathVariable("range") Integer range) {
		return poiRepository.listByProximity(x, y, new Double(range.toString()));
	}
	
}
