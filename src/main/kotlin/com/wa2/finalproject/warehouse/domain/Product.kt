package com.wa2.finalproject.warehouse.domain

import com.wa2.finalproject.warehouse.dto.ProductDTO
import com.wa2.finalproject.warehouse.dto.WarehouseDTO
import java.util.*
import javax.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator",
        sequenceName = "sequence_1", initialValue = 1, allocationSize = 1)
    @Column(updatable = false, nullable = false)
    val id : Long? = null,

    val name: String?,
    val description: String?,
    val picture_url: String?,
    val category: String?,
    val price: Float,
    val average_rating: Float,
    val creation_date: Date,

    @OneToMany(mappedBy = "product")
    val availabilities : MutableList<ProductAvailability> = mutableListOf()

){
    //: EntityBase<Long>(){


    fun toDTO(): ProductDTO {
        var map=  mutableMapOf<Long, Int>()
        for(a in availabilities){
            map.put(a.warehouse.id!!, a.quantity)
        }
        return ProductDTO(id!!, name!!, description!!, picture_url, category, price, average_rating, creation_date, map!!)
    }

}