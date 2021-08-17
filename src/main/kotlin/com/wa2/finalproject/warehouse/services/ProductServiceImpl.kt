package com.wa2.finalproject.warehouse.services

import com.wa2.finalproject.warehouse.domain.Product
import com.wa2.finalproject.warehouse.dto.ProductDTO
import com.wa2.finalproject.warehouse.dto.WarehouseDTO
import com.wa2.finalproject.warehouse.repositories.ProductAvailabilityRepository
import com.wa2.finalproject.warehouse.repositories.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.transaction.Transactional


@Service
@Transactional
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val availabilityRepository: ProductAvailabilityRepository
) : ProductService {

    override fun getAll(category: String?): List<ProductDTO>{
        val products = productRepository.findAll()
        print(products)
        var productsList = mutableListOf<ProductDTO>()
        if(category != null){
            productsList = products.filter { it.category == category }.map { it.toDTO() } as MutableList<ProductDTO>
        }
        else productsList = products.map { it.toDTO() } as MutableList<ProductDTO>
        /*for(p in products){
            productsList.add(p.toDTO())
            // print(w.toDTO())
        }*/
        return productsList
    }

    override fun getById(productId: Long) : ProductDTO{
        val product = productRepository.findById(productId)

        if (product.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        }
        return product.get().toDTO()
    }

    override fun create(name: String, description: String, picture_url: String?, category: String?, price: Float, average_rating: Float, creation_date: Date) : ProductDTO{
     val product = Product(null, name, description, picture_url, category, price, average_rating, creation_date, mutableListOf())
        return productRepository.save(product).toDTO()
    }

    override fun updateFull(productId: Long, name: String, description: String, picture_url: String, category: String, price: Float, average_rating: Float, creation_date: Date) : ProductDTO{
        var product = productRepository.findById(productId)
        var newProduct: Product
        if(product.isEmpty){
            newProduct = Product(null, name, description, picture_url, category, price, average_rating, creation_date, mutableListOf())
        }
        newProduct = Product(productId, name, description, picture_url, category, price, average_rating, creation_date)
        return productRepository.save(newProduct).toDTO()
    }

    override fun updatePartial(productId: Long, name: String?, description: String?, picture_url: String?, category: String?, price: Float?, average_rating: Float?, creation_date: Date?) : ProductDTO{
        var product = productRepository.findById(productId)
        var newProduct: Product

        if(product.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        }

        /*if(name == null && location == null){ return product.get().toDTO()}

        else if(name == null && location != null){
            newProduct = Product(productId, product.get().name, location)
        }
        else if(name != null && location == null){
            newProduct = Product(productId, name, product.get().location)
        }
        else{
            newProduct = Product(productId, name!!, location!!)
        }*/

        newProduct = Product(productId, name ?: product.get().name, description ?: product.get().description, picture_url ?: product.get().picture_url, category ?: product.get().category, price ?: product.get().price , average_rating ?: product.get().average_rating, creation_date ?: product.get().creation_date)
        return productRepository.save(newProduct).toDTO()

    }

    override fun delete(productId: Long) {
        var product = productRepository.findById(productId)

        if (product.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        }
        return productRepository.delete(product.get())

    }

    override fun getPictureByID(productId: Long): String{
        val product = productRepository.findById(productId)

        if (product.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        }
        return product.get().picture_url ?: ""
    }

    override fun updatePicture(productId: Long, picture_url: String): ProductDTO{
        var product = productRepository.findById(productId)
        var newProduct: Product = product.get()

        if (product.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        }
        newProduct = Product(newProduct.id, newProduct.name, newProduct.description, picture_url, newProduct.category, newProduct.price, newProduct.average_rating, newProduct.creation_date)
        return productRepository.save(newProduct).toDTO()
    }

    override fun getWarehousesForProduct(productId: Long): List<WarehouseDTO> {
        TODO("Not yet implemented")
    }
    /*  override fun getProductsForProduct(productId: Long): List<WarehouseDTO>{
          var warehousesListID = mutableListOf<Long>()
      }

     */

}