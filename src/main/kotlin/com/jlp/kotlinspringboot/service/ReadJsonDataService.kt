package com.jlp.kotlinspringboot.service

import com.jlp.kotlinspringboot.bean.ProductLists

import com.jlp.kotlinspringboot.bean.Products
import org.springframework.stereotype.Repository

@Repository
interface ReadJsonDataService {
	fun getProductsList(jsonArray: ProductLists, labelType: String): List<Products>
	fun readProductData(url: String): ProductLists
}
