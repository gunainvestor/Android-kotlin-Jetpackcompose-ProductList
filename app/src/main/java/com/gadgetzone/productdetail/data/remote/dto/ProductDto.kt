package com.gadgetzone.productdetail.data.remote.dto

import com.gadgetzone.productdetail.domain.model.Product

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = rating.rate,
            ratingCount = rating.count
        )
    }
}

data class RatingDto(
    val rate: Double,
    val count: Int
)


