package com.akira.akirastoryboard.bottomsheets.frame;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.activity.result.ActivityResultLauncher;
import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.R;
import com.akira.akirastoryboard.activities.frame.FrameActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetEditFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import java.io.File;

public class EditFrameBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetEditFrameBinding binding;
  private TextInputEditText te_path, te_info, te_number;
  private TextInputLayout tl_path, tl_info, tl_number;
  private Button btn_save, btn_cancel;
  private FrameItemModel model;
  private FrameActivityViewModel viewModel;
  private ShapeableImageView iv_frame;
  private CheckBox cb_centered;
  private ActivityResultLauncher<Intent> launcher =
      registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
              Intent intent = result.getData();
              Uri imageUri = intent.getData();
              String[] projection = {MediaStore.Images.Media.DATA};
              try (Cursor cursor =
                  getContext().getContentResolver().query(imageUri, projection, null, null, null)) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String imageFilePath = cursor.getString(column_index);
                te_path.setText(imageFilePath);
                Picasso.get()
                    .load(Uri.fromFile(new File(imageFilePath)))
                    .placeholder(
                        ContextCompat.getDrawable(getActivity(), R.drawable.ic_image_broken))
                    .into(iv_frame);
              }
            }
          });

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
    this.te_number = binding.teFrameNumber;
    this.tl_number = binding.tlFrameNumber;

    this.btn_save = binding.btnSave;
    this.btn_cancel = binding.btnCancel;

    this.iv_frame = binding.ivFrame;
    this.cb_centered = binding.cbCentered;
  }

  private void onsetViews() {
    Picasso.get()
        .load(Uri.fromFile(new File(model.getImagePath())))
        .placeholder(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image_broken))
        .into(iv_frame);

    te_path.setText(model.getImagePath());
    te_info.setText(model.getInfo());
    te_number.setText(String.valueOf(model.getNumber()));
    cb_centered.setChecked(model.getIsCentered());

    tl_path.setEndIconOnClickListener(
        v -> {
          Intent intent =
              new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          launcher.launch(intent);
        });

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
                .placeholder(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image_broken))
                .into(iv_frame);
          }
        });

    btn_save.setOnClickListener(
        v -> {
          String img_path = te_path.getText().toString().trim();
          String info = te_info.getText().toString().trim();
          String number = te_number.getText().toString().trim();

          if (TextUtils.isEmpty(info)) {
            tl_info.setErrorEnabled(true);
            tl_info.setError("Info is empty");
          }

          if (TextUtils.isEmpty(img_path)) {
            tl_path.setErrorEnabled(true);
            tl_path.setError("Image is empty");
          }

          if (TextUtils.isEmpty(number)) {
            tl_number.setErrorEnabled(true);
            tl_number.setError("Project number is empty");
          }

          if (!TextUtils.isEmpty(img_path)
              && !TextUtils.isEmpty(info)
              && !TextUtils.isEmpty(number)) {
            model.setInfo(info);
            model.setImagePath(img_path);
            model.setNumber(Integer.parseInt(number));

            viewModel.setUpdateFrame(model);
            dismiss();
          }
        });

    cb_centered.setOnCheckedChangeListener(
        (CompoundButton buttonView, boolean isChecked) -> {
          if (isChecked) model.setIsCentered(true);
          else model.setIsCentered(false);
        });

    btn_cancel.setOnClickListener(
        v -> {
          dismiss();
        });
  }
}
