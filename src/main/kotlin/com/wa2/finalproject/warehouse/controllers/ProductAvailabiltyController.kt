package com.wa2.finalproject.warehouse.controllers

import com.wa2.finalproject.warehouse.dto.ProductDTO
import com.wa2.finalproject.warehouse.services.ProductAvailabilityService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/availability")
class ProductAvailabiltyController(val productAvailabilityService: ProductAvailabilityService) {

    /*Update product-warehouse relationship*/
        @PostMapping("/{productID}/warehouse/{warehouseID}") // OK
        fun newRelationship(
            @PathVariable productID: Long,
            @PathVariable warehouseID: Long,
            quantity: Int,
            alarm: Int
        ): ProductDTO {
            return productAvailabilityService.productInWarehouse(productID, warehouseID, quantity, alarm)
        }
    }
