package com.jlp.kotlinspringboot.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import java.io.Serializable

import java.util.ArrayList

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
 class Products
(
		var colorSwatches: ArrayList<ColorSwatches> = ArrayList(),
		var priceLabel: List<PriceLabel> = ArrayList(),
		var productId : String = "",
		var nowPrice : String = "",
		var title : String = "",
		var price : Object?=null

) : Serializable
