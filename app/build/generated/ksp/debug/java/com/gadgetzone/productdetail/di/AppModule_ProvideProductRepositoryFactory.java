package com.gadgetzone.productdetail.di;

import com.gadgetzone.productdetail.data.remote.ProductApiService;
import com.gadgetzone.productdetail.domain.repository.ProductRepository;
import com.gadgetzone.productdetail.util.NetworkUtils;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideProductRepositoryFactory implements Factory<ProductRepository> {
  private final Provider<ProductApiService> apiServiceProvider;

  private final Provider<NetworkUtils> networkUtilsProvider;

  public AppModule_ProvideProductRepositoryFactory(Provider<ProductApiService> apiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
  }

  @Override
  public ProductRepository get() {
    return provideProductRepository(apiServiceProvider.get(), networkUtilsProvider.get());
  }

  public static AppModule_ProvideProductRepositoryFactory create(
      Provider<ProductApiService> apiServiceProvider, Provider<NetworkUtils> networkUtilsProvider) {
    return new AppModule_ProvideProductRepositoryFactory(apiServiceProvider, networkUtilsProvider);
  }

  public static ProductRepository provideProductRepository(ProductApiService apiService,
      NetworkUtils networkUtils) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideProductRepository(apiService, networkUtils));
  }
}
