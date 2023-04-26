package com.akira.akirastoryboard.bottomsheets.frame;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.activities.frame.FrameActivityViewModel;
import com.akira.akirastoryboard.activities.main.MainActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetAddFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.akira.akirastoryboard.R;
import java.util.UUID;

public class AddFrameBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddFrameBinding binding;
  private TextInputEditText te_path, te_info;
  private TextInputLayout tl_path, tl_info;
  private Button btn_create, btn_cancel;
  private FrameItemModel model;
  private FrameActivityViewModel viewModel;
  private ShapeableImageView iv_frame;
  private String id;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.id = getArguments() != null ? getArguments().getString("sceneId") : "";
    this.viewModel = new ViewModelProvider(getActivity()).get(FrameActivityViewModel.class);
    this.model = new FrameItemModel();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    this.binding = BottomSheetAddFrameBinding.inflate(inflater);
    onsetViewBinding();
    onsetViews();
    return binding.getRoot();
  }

  private void onsetViewBinding() {
    this.te_path = binding.tePath;
    this.tl_path = binding.tlPath;
    this.te_info = binding.teInfo;
    this.tl_info = binding.tlInfo;

    this.btn_create = binding.btnCreate;
    this.btn_cancel = binding.btnCancel;
    this.iv_frame = binding.ivFrame;
  }

  private void onsetViews() {
    Picasso.get().load(R.drawable.sample).into(iv_frame);

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
                .placeholder(R.drawable.sample)
                .into(iv_frame);
          }
        });

    btn_create.setOnClickListener(
        v -> {
          String img_path = te_path.getText().toString().trim();
          String info = te_info.getText().toString().trim();

          if (TextUtils.isEmpty(info)) {
            tl_info.setErrorEnabled(true);
            tl_info.setError("Info is empty");
          }

          if (TextUtils.isEmpty(img_path)) {
            tl_path.setErrorEnabled(true);
            tl_path.setError("Cover image is empty");
          }

          if (!TextUtils.isEmpty(img_path) && !TextUtils.isEmpty(info)) {
            model.setInfo(info);
            model.setImagePath(img_path);
            model.setSceneId(id);
            
            viewModel.setNewFrame(model);
            dismiss();
          }
        });

    btn_cancel.setOnClickListener(
        v -> {
          dismiss();
        });
  }
}
