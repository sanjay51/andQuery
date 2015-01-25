package com.codingg.andquery.Animation.xView;

import android.content.Context;
import android.graphics.Rect;

public class xImageView extends android.widget.ImageView {
    public Rect rect;
    public xImageView(Context context) {
        super(context);
    }

    public boolean intersectsWith(xImageView xiv2) {
        return Rect.intersects(rect, xiv2.rect);
    }
}
