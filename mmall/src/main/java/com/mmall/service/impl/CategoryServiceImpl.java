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
	
	//��ѯ���и��ڵ�
	public List<Category> getParentCategory(){
		
		List<Category>  categorys=categoryMapper.selectAllTopCategorys();
		List<Category> tempCategroys=Lists.newArrayList();
		//
		for (Category category : categorys) {
			//Ϊ0����ʾΪ���ϲ�ڵ�
			if(category.getParentId()==0){
				tempCategroys.add(category);
			}
		}
			return tempCategroys;
	}
	
	
 
	
	/**
	 * ���ݽڵ��ѯ�ýڵ��µĵ�һ���ӽڵ�
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
		
		//����ID��ѯ��ǰ�ڵ�
	
		Category category = categoryMapper.selectByPrimaryKey(categoeyId);
		if(null !=category)	{
			cList.add(category);
		}
		
		//���ݵ�ǰ�ڵ��ѯ�����ӽڵ�
		List<Category> childCategoryByParentId = getChildCategoryByParentId(categoeyId);
		
		for (Category tmpcategory : childCategoryByParentId) {
			getAllChildByCategoryId(cList,tmpcategory.getId());
		}
		return cList;
		
		
		
	}
}
