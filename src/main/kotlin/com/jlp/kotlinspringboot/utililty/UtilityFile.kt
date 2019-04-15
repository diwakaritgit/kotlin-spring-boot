package com.jlp.kotlinspringboot.utililty

import com.jlp.kotlinspringboot.bean.ColorSwatches
import com.jlp.kotlinspringboot.bean.PriceLabel
import com.jlp.kotlinspringboot.bean.Products
import org.springframework.util.StringUtils
import java.util.ArrayList
import java.util.HashMap

object UtilityFile {
	fun basicColorToRGB(): HashMap<String, String> {

		val colorMap = HashMap<String, String>()
		colorMap.put("Blue", "#F0A1C2")
		colorMap.put("Red", "#F3B4CE")
		colorMap.put("White", "#FFFFFF")
		colorMap.put("Grey", "#FFFFFF")
		colorMap.put("Purple", "#808080")
		colorMap.put("Black", "#000000")
		colorMap.put("Orange", "#808080")
		colorMap.put("Yellow", "#f5f5cc")

		return colorMap
	}

	val DESCENDING_COMPARATOR = { prod1 : Products, prod2 : Products ->

		val discount1 = Math.round(java.lang.Float.parseFloat(prod1.price.toString())) - Math.round(java.lang.Float.parseFloat(prod1.nowPrice))
		val discount2 = Math.round(java.lang.Float.parseFloat(prod2.price.toString())) - Math.round(java.lang.Float.parseFloat(prod2.nowPrice))
		if (discount1 > discount2) -1 else 1
	}

	fun setColorSwatchesList(colorSwatches: ArrayList<ColorSwatches>, colorList: ArrayList<ColorSwatches>) {

		colorSwatches.forEach { coloritem ->
			val swatches = ColorSwatches()
			val strSkuId = coloritem.skuId
			val strColor = coloritem.color
			val basicColor = coloritem.basicColor

			if (strColor != null) {
				swatches.color = strColor
				val b: String? = UtilityFile.basicColorToRGB()[basicColor]
				if (b != null && b.length > 0) {
					swatches.rgbcolor = b
				}
			}
			if (strSkuId != null) {
				swatches.skuId = strSkuId
			}
			colorList.add(swatches)
		}
	}

	fun setByLabelType(labelType: String, then: Array<String?>, now: Array<String?>, priceval: String, pLabel: PriceLabel) {

		if (!StringUtils.isEmpty(labelType)) {
			if (labelType == "ShowPercDscount") {
				val discount = Math.round(java.lang.Float.parseFloat(priceval)) - Math.round(java.lang.Float.parseFloat(now[0]))
				val percent = discount * 100 / Math.round(java.lang.Float.parseFloat(priceval))
				pLabel.ShowPercDscount = percent.toString() + "% off - now £" + now[0]
			} else if (labelType == "ShowWasThenNow") {
				if (!StringUtils.isEmpty(then[0])) {
					pLabel.ShowWasThenNow = "was £" + priceval + ", then £" + then[0] + ", now £" + now[0]
				} else {
					pLabel.ShowWasThenNow = "was £" + priceval + ", now £" + now[0]
				}
			} else {
				pLabel.showWasNow = "Was £" + priceval + ", now £" + now[0]
			}
		} else {
			pLabel.showWasNow = "Was £" + priceval + "," + " now £" + now[0]
		}
	}
}
