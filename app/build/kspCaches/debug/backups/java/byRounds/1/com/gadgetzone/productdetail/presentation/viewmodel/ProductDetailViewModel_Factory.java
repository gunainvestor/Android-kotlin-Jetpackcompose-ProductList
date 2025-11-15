package com.gadgetzone.productdetail.presentation.viewmodel;

import com.gadgetzone.productdetail.domain.usecase.GetProductByIdUseCase;
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
public final class ProductDetailViewModel_Factory implements Factory<ProductDetailViewModel> {
  private final Provider<GetProductByIdUseCase> getProductByIdUseCaseProvider;

  public ProductDetailViewModel_Factory(
      Provider<GetProductByIdUseCase> getProductByIdUseCaseProvider) {
    this.getProductByIdUseCaseProvider = getProductByIdUseCaseProvider;
  }

  @Override
  public ProductDetailViewModel get() {
    return newInstance(getProductByIdUseCaseProvider.get());
  }

  public static ProductDetailViewModel_Factory create(
      Provider<GetProductByIdUseCase> getProductByIdUseCaseProvider) {
    return new ProductDetailViewModel_Factory(getProductByIdUseCaseProvider);
  }

  public static ProductDetailViewModel newInstance(GetProductByIdUseCase getProductByIdUseCase) {
    return new ProductDetailViewModel(getProductByIdUseCase);
  }
}
