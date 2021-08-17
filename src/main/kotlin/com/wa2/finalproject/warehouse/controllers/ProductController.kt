package com.wa2.finalproject.warehouse.controllers


import com.wa2.finalproject.warehouse.dto.ProductDTO
import com.wa2.finalproject.warehouse.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(val productService: ProductService) {

    /*Retrieves the list of all products. Specifying the category, retrieves all products by a given category*/
    @GetMapping // OK
    // TODO category filter
    fun getProducts(category: String?): List<ProductDTO>{ // OK
        return productService.getAll(category)
    }

    /*Retrieves the product identified by productID*/
    @GetMapping("/{productID}") // OK
    fun getProductByID(@PathVariable productID: Long): ProductDTO {
        return productService.getById(productID)
    }

    /*Adds a new product*/
    @PostMapping // OK
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(name: String, description: String, picture_url: String, category: String, price: Float, average_rating: Float, creation_date: Date): ProductDTO {
        return productService.create(name, description, picture_url, category, price, average_rating, creation_date )
    }

    /*Updates an existing product (full representation), or adds a new one if not exists*/
    @PutMapping("/{productID}") // OK
    fun updateFullProduct(@PathVariable productID: Long, name: String, description: String, picture_url: String, category: String, price: Float, average_rating: Float, creation_date: Date): ProductDTO {
        return productService.updateFull(productID, name, description, picture_url, category, price, average_rating, creation_date)
    }

    /*Updates an existing product (partial representation)*/
    @PatchMapping("/{productID}") // OK
    fun updatePartialProduct(@PathVariable productID: Long, name: String?, description: String?, picture_url: String?, category: String?, price: Float?, average_rating: Float?, creation_date: Date?): ProductDTO {
        return productService.updatePartial(productID, name, description, picture_url, category, price, average_rating, creation_date)
    }

    /*Deletes a product*/
    @DeleteMapping("/{productID}") // OK
    fun deleteProduct(@PathVariable productID: Long) {
        return productService.delete(productID)
    }

    /*Retrieves the picture of the product identified by productID*/
    @GetMapping("/{productID}/picture") // OK
    fun getPictureByID(@PathVariable productID: Long): String {
        return productService.getPictureByID(productID)
    }

    /*Updates the picture of the product identified by productID*/
    @PostMapping("/{productID}/picture") // OK
    fun updatePictureByID(@PathVariable productID: Long, picture_url: String): ProductDTO {
        return productService.updatePicture(productID, picture_url)
    }

    /*Gets the list of the products that contain the product*/
   /* @GetMapping("/{productID}/warehouses") // OK
    fun getWarehousesByProductID(@PathVariable productID: Long): ProductDTO {
        return productService.getWarehousesForProduct()
    }*/

}