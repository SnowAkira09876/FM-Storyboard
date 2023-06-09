package com.akira.akirastoryboard.bottomsheets.scene;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.activities.scene.SceneActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetAddSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.UUID;

public class AddSceneBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddSceneBinding binding;
  private TextInputEditText te_name, te_info, te_number;
  private TextInputLayout tl_name, tl_info, tl_number;
  private Button btn_create, btn_cancel;
  private SceneActivityViewModel viewModel;
  private SceneItemModel model;
  private String id;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.id = getArguments() != null ? getArguments().getString("projectId") : "";
    this.viewModel = new ViewModelProvider(getActivity()).get(SceneActivityViewModel.class);
    this.model = new SceneItemModel();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    this.binding = BottomSheetAddSceneBinding.inflate(inflater);
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

    this.btn_create = binding.btnCreate;
    this.btn_cancel = binding.btnCancel;
  }

  private void onsetViews() {
    btn_create.setOnClickListener(
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
            model.setProjectId(id);
            model.setFrames("0 frames");
            model.setSceneId(UUID.randomUUID().toString());
            model.setInfo(scene_info);
            model.setNumber(Integer.parseInt(number));

            viewModel.setNewScene(model);
            dismiss();
          }
        });

    btn_cancel.setOnClickListener(
        v -> {
          dismiss();
        });
  }
}
