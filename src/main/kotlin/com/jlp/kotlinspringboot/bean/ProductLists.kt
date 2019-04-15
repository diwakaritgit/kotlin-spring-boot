package com.jlp.kotlinspringboot.bean


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable
import java.util.ArrayList

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ProductLists
(
		var products : List<Products>? = null


) : Serializable
