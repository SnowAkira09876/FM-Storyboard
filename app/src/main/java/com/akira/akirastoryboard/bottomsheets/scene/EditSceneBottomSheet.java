package com.akira.akirastoryboard.bottomsheets.scene;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.activities.scene.SceneActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetEditSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditSceneBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetEditSceneBinding binding;
  private TextInputEditText te_name, te_info, te_number;
  private TextInputLayout tl_name, tl_info, tl_number;
  private Button btn_save, btn_cancel;
  private SceneActivityViewModel viewModel;
  private SceneItemModel model;

  @SuppressWarnings("deprecation")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.viewModel = new ViewModelProvider(getActivity()).get(SceneActivityViewModel.class);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      this.model = getArguments().getParcelable("scene", SceneItemModel.class);
    else this.model = getArguments().getParcelable("scene");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    this.binding = BottomSheetEditSceneBinding.inflate(inflater);
    onsetViewBinding();
    onsetViews();
    return binding.getRoot();
  }

  private void onsetViewBinding() {
    this.te_name = binding.teName;
    this.tl_name = binding.tlName;
    this.te_info = binding.teInfo;
    this.tl_info = binding.tlInfo;
    this.te_number = binding.teSceneNumber;
    this.tl_number = binding.tlSceneNumber;

    this.btn_save = binding.btnSave;
    this.btn_cancel = binding.btnCancel;
  }

  private void onsetViews() {
    te_name.setText(model.getTitle());
    te_info.setText(model.getInfo());
    te_number.setText(String.valueOf(model.getNumber()));

    btn_save.setOnClickListener(
        v -> {
          String scene_name = te_name.getText().toString().trim();
          String scene_info = te_info.getText().toString().trim();
          String number = te_number.getText().toString().trim();

          if (TextUtils.isEmpty(scene_name)) {
            tl_name.setErrorEnabled(true);
            tl_name.setError("Name is empty");
          }
          if (TextUtils.isEmpty(scene_info)) {
            tl_info.setErrorEnabled(true);
            tl_info.setError("Info is empty");
          }

          if (TextUtils.isEmpty(number)) {
            tl_number.setErrorEnabled(true);
            tl_number.setError("Project number is empty");
          }

          if (!TextUtils.isEmpty(scene_name)
              && !TextUtils.isEmpty(scene_info)
              && !TextUtils.isEmpty(number)) {
            model.setTitle(scene_name);
            model.setInfo(scene_info);
            model.setNumber(Integer.parseInt(number));

            viewModel.setUpdateScene(model);
            dismiss();
          }
        });

    btn_cancel.setOnClickListener(
        v -> {
          dismiss();
        });
  }
}
