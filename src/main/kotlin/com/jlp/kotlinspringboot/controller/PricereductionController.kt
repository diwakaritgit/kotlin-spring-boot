package com.jlp.kotlinspringboot.controller

import com.google.gson.Gson
import com.jlp.kotlinspringboot.bean.Products;
import com.jlp.kotlinspringboot.service.ReadJsonDataService
import com.jlp.kotlinspringboot.utililty.UtilityFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping


@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/priceReductionList")
@RestController
public class PriceDeductionController @Autowired constructor(private val readJsonDataService: ReadJsonDataService){

	@Value("\${url.jlp}")
     var url: String = ""

	@RequestMapping(produces = arrayOf("application/json"), method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun priceReductionList(@RequestParam(value = "labelType", defaultValue = "showWasNow") labelType: String): ResponseEntity<String> {
		val responseObj = mutableMapOf<String, List<Products>>()

		var msg: String = ""
		try {
			val json = readJsonDataService.readProductData(url);
			if (json != null) {
				val prodList: List<Products> = readJsonDataService.getProductsList(json, labelType)

				Collections.sort(prodList, UtilityFile.DESCENDING_COMPARATOR);
				responseObj.put("products", prodList);
				msg = Gson().toJson(responseObj);

			} else {
				msg = "FAIL DURING URL CALL";
			}
		} catch (e: Exception) {
			e.printStackTrace();
		}
		return ResponseEntity<String>(msg, HttpStatus.OK);

	}
}
