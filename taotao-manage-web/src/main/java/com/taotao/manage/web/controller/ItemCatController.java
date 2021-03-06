package com.taotao.manage.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;


@Controller
@RequestMapping("item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService; 
	
	/**
	 * 根据父id查询类目
	 * @param parentId
	 * @return
	 */
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ItemCat>>  queryItemCatByParentId(@RequestParam(value="id",defaultValue="0") Long parentId){
		
		
		try {
			
			List<ItemCat> list = itemCatService.queryItemCatByParentId(parentId);		
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
