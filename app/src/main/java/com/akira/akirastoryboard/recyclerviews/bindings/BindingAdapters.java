package com.akira.akirastoryboard.recyclerviews.bindings;

import android.net.Uri;
import android.text.Spanned;
import android.view.Gravity;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;
import java.io.File;
import com.akira.akirastoryboard.R;

public class BindingAdapters {

  @BindingAdapter("htmlText")
  public static void setHtmlText(TextView textView, String htmlText) {
    Spanned spannedText = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY);
    textView.setText(spannedText);
  }

  @BindingAdapter("centerText")
  public static void setCenterText(TextView textView, boolean isCenter) {
    if (isCenter) textView.setGravity(Gravity.CENTER);
  }

  @BindingAdapter("imagePath")
  public static void loadImage(ShapeableImageView imageView, String path) {
    Picasso.get()
        .load(Uri.fromFile(new File(path)))
        .placeholder(R.drawable.ic_image_broken)
        .into(imageView);
  }
}
