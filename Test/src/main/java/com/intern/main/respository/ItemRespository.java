package com.intern.main.respository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intern.main.entity.Items;



@Repository
public interface ItemRespository extends CrudRepository<Items, String>
{

	@Query(value="select i from Items i where i.Name=:name")
	Items SearchByName(@Param("name") String name);
	
	@Query(value="select i from Items i where i.Price between :low and :high")
	List<Items> SearchInRange(@Param("low") double low,@Param("high") double high);
	
	@Query(value="select i from Items i order by i.Price ")
	List<Items> SortByPriceASC();
	
	@Query(value="select i from Items i order by i.Price DESC")
	List<Items> SortByPriceDSC();
	
	@Query(value="select i from Items i order by name")
	List<Items> SortByName();
	
	@Query(value="select i from Items i order by name DESC")
	List<Items> SortByNameDSC();
	
	@Query(value="select i from Items i order by description")
	List<Items> SortByDesc();
	
	@Query(value="select i from Items i order by description DESC")
	List<Items> SortByDescDSC();
	
	@Query(value="select i from Items i where name like :var")
	List<Items> nameLike(@Param("var") String var);
	
	@Query(value="select i from Items i where description like :var")
	List<Items> descLike(@Param("var") String var);

}
