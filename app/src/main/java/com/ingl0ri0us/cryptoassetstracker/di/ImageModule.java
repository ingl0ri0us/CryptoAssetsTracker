package com.ingl0ri0us.cryptoassetstracker.di;

import android.widget.ImageView;

import com.ingl0ri0us.cryptoassetstracker.data.image.ImageLoader;
import com.ingl0ri0us.cryptoassetstracker.data.image.PicassoImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
class ImageModule {

    @Provides
    ImageLoader<ImageView> picassoImageLoader() {
        return new PicassoImageLoader();
    }
}