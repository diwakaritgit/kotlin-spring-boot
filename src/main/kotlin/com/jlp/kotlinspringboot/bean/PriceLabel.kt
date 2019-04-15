package com.jlp.kotlinspringboot.bean

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.io.Serializable


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true) class PriceLabel
(
		var showWasNow : String = "",
		var ShowWasThenNow : String = "",
		var ShowPercDscount : String = ""
) : Serializable

