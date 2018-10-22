package com.mmall.service;

import java.util.List;

import com.mmall.pojo.Category;

/**
 * ��Ʒ���Service
 * @author jiu2_li
 *
 */
public interface CategoryService {
	List<Category> getParentCategory();
	List<Category> getChildCategoryByParentId(Integer categoeyId);
	List<Category> getAllChildByCategoryId(List<Category> cList,Integer categoeyId);
}
