package com.intern.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.intern.main.entity.Items;
import com.intern.main.service.ItemServices;

@RestController
@RequestMapping("/v1")
public class ItemController {

	@Autowired
	ItemServices service;
	
	
	/****************************************************************************************/
		//Retrive items from table by Id
 		@GetMapping("getProduct/{itemid}")
		public ResponseEntity<Object> getProductDetails(@PathVariable("itemid") String itemid) {
			Optional<Items> op = service.Detail(itemid);
			if (op.isPresent()) {
				Items item = op.get();
				return new ResponseEntity<Object>(item, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Item is not present", HttpStatus.NOT_FOUND);
			}

		}
		
	
		
		/*****************************************************************************************/
		
		//Retrive data from table by name
		
		@GetMapping("getByName/{name}")
		public ResponseEntity<Object> getProductByName(@PathVariable("name") String name) {
			Items op = service.SearchByName(name);
			if (op!=null) {
				return new ResponseEntity<Object>(op, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Item is not present", HttpStatus.NOT_FOUND);
			}

		}
		
		
		
		
		/**********************************************************************************************/
		
		//Retrieve AllData from table
		@GetMapping("getAll")
		public ResponseEntity<Object> getAllProductDetails() {
			List<Items> list=service.AllItems();
			
				return new ResponseEntity<Object>(list, HttpStatus.OK);
			
		}
		
		
		
		/************************************************************************************************/
		
		//saving data in the table
		@PostMapping("/save")
		public ResponseEntity<String> saveitem(@RequestBody Items fod) {
			
			Items op = service.SearchByName(fod.getName());
			if (!(op==null)) {
				return new ResponseEntity<String>(" Item already present", HttpStatus.ALREADY_REPORTED);
			}
			service.saveItem(fod);
			return new ResponseEntity<String>("Successfully added", HttpStatus.CREATED);

		}
		
		
		/**************************************************************************************************/
		
		//Deleting data from table using id
		
		@DeleteMapping("/deleteitem/{id}")
		public ResponseEntity<String> deleteProductById(@PathVariable("id") String id)
		{
			Optional<Items> op=service.Detail(id);
			if(op.isPresent())
			{
				service.Delete(id);
				return new ResponseEntity<String>("DELETE-SUCCESSFULLY",HttpStatus.OK);
			}
			return new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
		}
		
		
		
		/*****************************************************************************************************/
		
		//Updating Data in the table
		
		@PatchMapping("/updateitem/{id}")
		public ResponseEntity<Object> updateProductById(@PathVariable("id") String id, @RequestBody Items item)
		{
			Optional<Items> op= service.Detail(id);
			if(op.isPresent())
			{
				Items i=op.get();
				if(item.getName()!=null)
					i.setName(item.getName());
				if(item.getPrice()!=0)
					i.setPrice(item.getPrice());
				if(item.getDescription()!=null)
					i.setDescription(item.getDescription());
				
				
				Optional<Items> it=service.saveItem(i);
				return new ResponseEntity<Object>(it.get(),HttpStatus.OK);
				
			}
			else
				return new ResponseEntity<Object>("NOT FOUND",HttpStatus.NOT_FOUND);
		}
		
		/****************************************************************************************************/
		
		//Deleting All item from table
		@DeleteMapping("/deleteallItems")
		public ResponseEntity<String> DeleteAllItems()
		{
			service.DeleteAll();
			return new ResponseEntity<String>("DELETED ALL ITEMS",HttpStatus.OK);
		}
		
		/******************************************************************************************************/
		
		//Getting Data from table in a range
		@GetMapping("/getinrange/{low}/{high}")
		public ResponseEntity<Object> SearchInRange(@PathVariable("low") double low,@PathVariable("high") double high)
		{
			List<Items> list = service.SearchInRange(low, high);
			if(list.size()>0)
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			else
				return new ResponseEntity<Object>("No Data Present",HttpStatus.BAD_REQUEST);
		}
		
		
		/*********************************************************************************************************/
		
		//Sorting Data from table in ascending order  
		@GetMapping("/sortbypriceASC")
		public ResponseEntity<Object> SortByPrice()
		{
			List<Items> list = service.SortByPriceASC();
			if(list.size()>0)
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			else
				return new ResponseEntity<Object>("No Data Present",HttpStatus.BAD_REQUEST);
		}
		
		
		
		/************************************************************************************************************/
		
		
		//Sorting Data from table in decending order
		@GetMapping("/sortbypriceDSC")
		public ResponseEntity<Object> SortByPriceDSC()
		{
			List<Items> list = service.SortByPriceDSC();
			if(list.size()>0)
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			else
				return new ResponseEntity<Object>("No Data Present",HttpStatus.BAD_REQUEST);
		}
		
		
		/**************************************************************************************************************/
		
		
		//Sorting Data from table by taking input from users
		@GetMapping("/sortbyvar/{value}/{value1}")
		public ResponseEntity<Object> SortByVar(@PathVariable("value") String val1, @PathVariable("value1") String val2)
		{
			if(val1.equalsIgnoreCase("name"))
			{
				if(val2.equalsIgnoreCase("asc"))
					return new ResponseEntity<Object>(service.SortByName(),HttpStatus.OK);
				else if(val2.equalsIgnoreCase("dsc"))
					return new ResponseEntity<Object>(service.SortByNameDSC(),HttpStatus.OK);
				else
					return new ResponseEntity<Object>("Please Enter either asc or dsc",HttpStatus.BAD_REQUEST);
			}
			
			else if(val1.equalsIgnoreCase("description"))
			{
				if(val2.equalsIgnoreCase("asc"))
					return  new ResponseEntity<Object>(service.SortByDesc(),HttpStatus.OK);
				else if(val2.equalsIgnoreCase("dsc"))
					return new ResponseEntity<Object>(service.SortByDescDSC(),HttpStatus.OK);
				else
					return new ResponseEntity<Object>("Please Enter either asc or dsc",HttpStatus.BAD_REQUEST);
			}
			else if(val1.equalsIgnoreCase("price"))
			{
				if(val2.equalsIgnoreCase("asc"))
					return  new ResponseEntity<Object>(service.SortByPriceASC(),HttpStatus.OK);
				else if(val2.equalsIgnoreCase("dsc"))
					return new ResponseEntity<Object>(service.SortByPriceDSC(),HttpStatus.OK);
				else
					return new ResponseEntity<Object>("Please Enter either asc or dsc",HttpStatus.BAD_REQUEST);
			}
			return  new ResponseEntity<Object>("Wrong Input",HttpStatus.BAD_REQUEST);
		}
		
		
		
		/*********************************************************************************************************/
		
		
		//Getting data from table in a limit
		@GetMapping("/getinLimit/{value}")
		public ResponseEntity<Object> GetByLimit(@PathVariable("value") int n)
		{
			List<Items> limit = new ArrayList<>();
			List<Items> nlimit = new ArrayList<>();
			limit=service.AllItems();
			if(limit.size()>=n)
			{
			for(int i=0;i<n;i++)
			{
				nlimit.add(limit.get(i));
			}
			return new ResponseEntity<Object>(nlimit,HttpStatus.OK);
			}
			else
				return new ResponseEntity<Object>(limit,HttpStatus.OK);
		}
		
		
		
		/**********************************************************************************************************/
		
		
		//Getting data from table with offset
		@GetMapping("/getinoffset/{value}")
		public ResponseEntity<Object> GetInOffset(@PathVariable("value") int n)
		{
			List<Items> present=new ArrayList<>();
			present=service.AllItems();
			List<Items> after = new ArrayList<>();
			if((present.size()-n)<=0)
			{
				return new ResponseEntity<Object>("No Value Found",HttpStatus.BAD_REQUEST);
			}
			else if(n==0)
				return new ResponseEntity<Object>(present,HttpStatus.OK);
			else
			{
				for(int i=n;i<present.size();i++)
				{
					after.add(present.get(i));
				}
				return new ResponseEntity<Object>(after,HttpStatus.OK);
			}
			
		}
		
		
		
		/*****************************************************************************************************/
		
		
		//Getting data from table which has name given by user
		@GetMapping("/nameLike/{value}")
		public ResponseEntity<Object> nameLike(@PathVariable("value") String val)
		{
			val="%"+val+"%";
			List<Items> list = service.nameLike(val);
			if(list.size()>0)
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			else
				return new ResponseEntity<Object>("Name with this value is not present",HttpStatus.BAD_REQUEST);
		}
		
		
		/*****************************************************************************************************/
		
		
		//Getting Data from table which has description given by user
		@GetMapping("/descLike/{value}")
		public ResponseEntity<Object> descLike(@PathVariable("value") String val)
		{
			val="%"+val+"%";
			List<Items> list = service.descLike(val);
			if(list.size()>0)
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			else
				return new ResponseEntity<Object>("Description with this value is not present",HttpStatus.BAD_REQUEST); 
		}
}


		/********************************************************************************************************/
