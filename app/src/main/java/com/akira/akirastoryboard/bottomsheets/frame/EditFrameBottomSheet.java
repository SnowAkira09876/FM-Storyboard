package com.akira.akirastoryboard.bottomsheets.frame;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.activities.frame.FrameActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetEditFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import java.io.File;
import com.akira.akirastoryboard.R;

public class EditFrameBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetEditFrameBinding binding;
  private TextInputEditText te_path, te_info;
  private TextInputLayout tl_path, tl_info;
  private Button btn_save, btn_delete;
  private FrameItemModel model;
  private FrameActivityViewModel viewModel;
  private ShapeableImageView iv_frame;
  private CheckBox cb_centered;

  @SuppressWarnings("deprecation")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.viewModel = new ViewModelProvider(getActivity()).get(FrameActivityViewModel.class);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      this.model = getArguments().getParcelable("frame", FrameItemModel.class);
    else this.model = getArguments().getParcelable("frame");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    this.binding = BottomSheetEditFrameBinding.inflate(inflater);
    onsetViewBinding();
    onsetViews();
    return binding.getRoot();
  }

  private void onsetViewBinding() {
    this.te_path = binding.tePath;
    this.tl_path = binding.tlPath;
    this.te_info = binding.teInfo;
    this.tl_info = binding.tlInfo;

    this.btn_save = binding.btnSave;
    this.btn_delete = binding.btnDelete;

    this.iv_frame = binding.ivFrame;
    this.cb_centered = binding.cbCentered;
  }

  private void onsetViews() {
    Picasso.get()
        .load(Uri.fromFile(new File(model.getImagePath())))
        .placeholder(R.drawable.ic_image_broken)
        .into(iv_frame);

    te_path.setText(model.getImagePath());
    te_info.setText(model.getInfo());
    cb_centered.setChecked(model.getIsCentered());

    te_path.addTextChangedListener(
        new TextWatcher() {

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {}

          @Override
          public void afterTextChanged(Editable s) {
            Picasso.get()
                .load(Uri.fromFile(new File(s.toString())))
                .placeholder(R.drawable.ic_image_broken)
                .into(iv_frame);
          }
        });

    btn_save.setOnClickListener(
        v -> {
          String img_path = te_path.getText().toString().trim();
          String frame_body = te_info.getText().toString().trim();

          if (TextUtils.isEmpty(frame_body)) {
            tl_info.setErrorEnabled(true);
            tl_info.setError("Info is empty");
          }

          if (TextUtils.isEmpty(img_path)) {
            tl_path.setErrorEnabled(true);
            tl_path.setError("Image is empty");
          }

          if (!TextUtils.isEmpty(img_path) && !TextUtils.isEmpty(frame_body)) {
            model.setInfo(frame_body);
            model.setImagePath(img_path);
            
            viewModel.setUpdateFrame(model);
            dismiss();
          }
        });

    btn_delete.setOnClickListener(
        v -> {
          viewModel.deleteFrame(model);
          dismiss();
        });

    cb_centered.setOnCheckedChangeListener(
        (CompoundButton buttonView, boolean isChecked) -> {
          if (isChecked) model.setIsCentered(true);
          else model.setIsCentered(false);
        });
  }
}
