package com.mmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	//查询所有父节点
	public List<Category> getParentCategory(){
		
		List<Category>  categorys=categoryMapper.selectAllTopCategorys();
		List<Category> tempCategroys=Lists.newArrayList();
		//
		for (Category category : categorys) {
			//为0，表示为最上层节点
			if(category.getParentId()==0){
				tempCategroys.add(category);
			}
		}
			return tempCategroys;
	}
	
	
 
	
	/**
	 * 根据节点查询该节点下的第一层子节点
	 * @param categoeyId
	 * @return
	 */
	public List<Category> getChildCategoryByParentId(Integer categoeyId){
		
		List<Category> lists=	categoryMapper.selcetChildByParentId(categoeyId);
		
		if(null !=categoeyId)
			return lists;
			
		
		
		return lists;
	}
	
	public List<Category> getAllChildByCategoryId(List<Category> cList,Integer categoeyId){
		
		//根据ID查询当前节点
	
		Category category = categoryMapper.selectByPrimaryKey(categoeyId);
		if(null !=category)	{
			cList.add(category);
		}
		
		//根据当前节点查询所有子节点
		List<Category> childCategoryByParentId = getChildCategoryByParentId(categoeyId);
		
		for (Category tmpcategory : childCategoryByParentId) {
			getAllChildByCategoryId(cList,tmpcategory.getId());
		}
		return cList;
		
		
		
	}
}
