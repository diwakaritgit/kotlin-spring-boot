package com.jlp.kotlinspringboot.service.impl

import com.jlp.kotlinspringboot.bean.ColorSwatches
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.client.RestTemplate

import com.jlp.kotlinspringboot.bean.PriceLabel
import com.jlp.kotlinspringboot.bean.ProductLists
import com.jlp.kotlinspringboot.bean.Products
import com.jlp.kotlinspringboot.service.ReadJsonDataService
import com.jlp.kotlinspringboot.utililty.UtilityFile
import java.util.*
import kotlin.collections.LinkedHashMap

@Service
class ReadJsonDataServiceImpl : ReadJsonDataService {

	override fun readProductData(uri: String): ProductLists {
		val productLists = RestTemplate().getForObject(uri, ProductLists::class.java)
		return productLists
	}

	override fun getProductsList(jsonArray: ProductLists, labelType: String): List<Products> {
		val prodList= ArrayList<Products>()

		jsonArray.products?.forEach { key ->
			val prod = Products()
			val pLabel = PriceLabel()
			val priceLabelList = ArrayList<PriceLabel>()
			val colorList = ArrayList<ColorSwatches>()
			prod.productId = key.productId
			prod.title = key.title
			if (key.colorSwatches.size > 0) {
				UtilityFile.setColorSwatchesList(key.colorSwatches, colorList)
			}
			val priceMap = key.price as LinkedHashMap<*, *>

			val now : Array<String?> = arrayOfNulls<String>(1)
			val then : Array<String?> = arrayOfNulls<String>(1)

			priceMap.forEach { pricekey, priceVal ->

				if (pricekey == "then2" && priceVal != "") {
					then[0] = priceVal.toString()
				} else if (pricekey == "then1" && priceVal != "") {
					then[0] = priceVal.toString()
				}
				if (pricekey == "was" && priceVal != "") {
					prod.price = priceVal as Object
					prod.colorSwatches = colorList
				}

				if (pricekey == "now") {

					val m1: Map<String, String>?
					if (priceVal.toString().startsWith("{")) {
						m1 = priceVal as Map<String, String>
						if (m1 != null) {
							val to = m1["to"].toString()
							now[0] =  to
						}
					} else {
						now[0] = priceVal.toString()
					}

					if (!StringUtils.isEmpty(prod.price)) {
						UtilityFile.setByLabelType(labelType, then, now, prod.price.toString(), pLabel)
						priceLabelList.add(pLabel)
						if(now[0] != null){
							prod.nowPrice = (now[0].toString())
						}
						prod.priceLabel = priceLabelList
						prodList.add(prod)
					}
				}
			}
		}
		return prodList
	}
}

