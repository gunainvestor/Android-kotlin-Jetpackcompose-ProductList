package com.gadgetzone.productdetail.di;

import com.gadgetzone.productdetail.data.remote.ProductApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppModule_ProvideProductApiServiceFactory implements Factory<ProductApiService> {
  @Override
  public ProductApiService get() {
    return provideProductApiService();
  }

  public static AppModule_ProvideProductApiServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProductApiService provideProductApiService() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideProductApiService());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideProductApiServiceFactory INSTANCE = new AppModule_ProvideProductApiServiceFactory();
  }
}
