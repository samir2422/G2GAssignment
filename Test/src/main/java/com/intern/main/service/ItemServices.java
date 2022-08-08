package com.intern.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.intern.main.entity.Items;
import com.intern.main.respository.ItemRespository;

@Service
public class ItemServices {

	@Autowired
	ItemRespository repo;
	
	public Optional<Items> saveItem(Items i) {
		repo.save(i);
		return repo.findById(i.getId());
	}
	
	public Optional<Items> Detail(String id) {
		return repo.findById(id);
	}
	
	public List<Items> AllItems(){
		List<Items> list = new ArrayList();
		repo.findAll().forEach(list::add);
		return list;
	}
	public void Delete(String itemid)
	{
		repo.deleteById(itemid);
	}
	public void DeleteAll()
	{
		repo.deleteAll();
	}
	public Items SearchByName(String name)
	{
		Items f= repo.SearchByName(name);
		if(f==null)
			return null;
		return f;
	}
	public List<Items> SearchInRange(double low,double high)
	{
		List<Items> list = new ArrayList();
		repo.SearchInRange(low, high).forEach(list::add);
		return list;
	}
	public List<Items> SortByPriceASC()
	{
		List<Items> list = new ArrayList();
		repo.SortByPriceASC().forEach(list::add);
		return list;
	}
	public List<Items> SortByPriceDSC()
	{
		List<Items> list = new ArrayList();
		repo.SortByPriceDSC().forEach(list::add);
		return list;
	}
	public List<Items> SortByName()
	{
		List<Items> list = new ArrayList();
		repo.SortByName().forEach(list::add);
		return list;
	}
	public List<Items> SortByNameDSC()
	{
		List<Items> list = new ArrayList();
		repo.SortByNameDSC().forEach(list::add);
		return list;
	}
	public List<Items> SortByDesc()
	{
		List<Items> list = new ArrayList();
		repo.SortByDesc().forEach(list::add);
		return list;
	}
	public List<Items> SortByDescDSC()
	{
		List<Items> list = new ArrayList();
		repo.SortByDescDSC().forEach(list::add);
		return list;
	}
	public List<Items> nameLike(String var)
	{
		List<Items> list =new ArrayList();
				repo.nameLike(var).forEach(list::add);
		return list;
	}
	
	public List<Items> descLike(String var)
	{
		List<Items> list =new ArrayList();
				repo.descLike(var).forEach(list::add);
		return list;
	}
	
}
