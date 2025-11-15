package com.gadgetzone.productdetail.data.repository;

import com.gadgetzone.productdetail.data.remote.ProductApiService;
import com.gadgetzone.productdetail.util.NetworkUtils;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ProductRepositoryImpl_Factory implements Factory<ProductRepositoryImpl> {
  private final Provider<ProductApiService> apiServiceProvider;

  private final Provider<NetworkUtils> networkUtilsProvider;

  public ProductRepositoryImpl_Factory(Provider<ProductApiService> apiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
  }

  @Override
  public ProductRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), networkUtilsProvider.get());
  }

  public static ProductRepositoryImpl_Factory create(Provider<ProductApiService> apiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    return new ProductRepositoryImpl_Factory(apiServiceProvider, networkUtilsProvider);
  }

  public static ProductRepositoryImpl newInstance(ProductApiService apiService,
      NetworkUtils networkUtils) {
    return new ProductRepositoryImpl(apiService, networkUtils);
  }
}
