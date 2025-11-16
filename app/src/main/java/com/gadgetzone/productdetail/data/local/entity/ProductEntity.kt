package com.gadgetzone.productdetail.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gadgetzone.productdetail.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Double,
    val ratingCount: Int,
    val lastUpdated: Long = System.currentTimeMillis()
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = rating,
            ratingCount = ratingCount
        )
    }
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = rating,
        ratingCount = ratingCount
    )
}

