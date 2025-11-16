package com.gadgetzone.productdetail.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gadgetzone.productdetail.presentation.ui.screen.ProductDetailScreen
import com.gadgetzone.productdetail.presentation.ui.screen.ProductListScreen

sealed class Screen(val route: String) {
    object ProductList : Screen("product_list")
    data class ProductDetail(val productId: Int) : Screen("product_detail/{productId}") {
        companion object {
            fun createRoute(productId: Int) = "product_detail/$productId"
        }
    }
}

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {
        composable(Screen.ProductList.route) {
            ProductListScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }
        composable("product_detail/{productId}") {
            backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            ProductDetailScreen(
                productId = productId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

