package com.ingl0ri0us.cryptoassetstracker.data.image;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader<ImageView> {

    private Picasso picasso;

    public PicassoImageLoader() {
        this.picasso = Picasso.get();
    }

    @Override
    public void loadInto(String url, ImageView container) {
        picasso.load(url).into(container);
    }
}
