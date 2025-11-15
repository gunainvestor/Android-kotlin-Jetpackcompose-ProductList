package com.gadgetzone.productdetail.presentation.viewmodel;

import com.gadgetzone.productdetail.domain.usecase.GetProductsUseCase;
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
public final class ProductListViewModel_Factory implements Factory<ProductListViewModel> {
  private final Provider<GetProductsUseCase> getProductsUseCaseProvider;

  public ProductListViewModel_Factory(Provider<GetProductsUseCase> getProductsUseCaseProvider) {
    this.getProductsUseCaseProvider = getProductsUseCaseProvider;
  }

  @Override
  public ProductListViewModel get() {
    return newInstance(getProductsUseCaseProvider.get());
  }

  public static ProductListViewModel_Factory create(
      Provider<GetProductsUseCase> getProductsUseCaseProvider) {
    return new ProductListViewModel_Factory(getProductsUseCaseProvider);
  }

  public static ProductListViewModel newInstance(GetProductsUseCase getProductsUseCase) {
    return new ProductListViewModel(getProductsUseCase);
  }
}
