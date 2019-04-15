package com.jlp.kotlinspringboot.bean


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.*
import java.io.Serializable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
data class ColorSwatches

(
		var color : String = "",
		var rgbcolor : String = "",
		var skuId : String = "",
		var basicColor : String = ""

) : Serializable
