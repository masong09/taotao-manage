package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;

@Service
public class ItemService extends BaseService<Item>{

	@Autowired
	private ItemDescService  itemDescService;
	
	public void saveItem(Item item, String desc) {
		item.setId(null);
		item.setStatus(1);
		super.save(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);		
		itemDescService.save(itemDesc);
	}

}
