package com.akira.akirastoryboard.bottomsheets.scene;

import android.app.Dialog;
import android.content.DialogInterface;
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

public class AddSceneBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddSceneBinding binding;
  private TextInputEditText te_name;
  private TextInputLayout tl_name;
  private TextInputEditText te_story;
  private TextInputLayout tl_story;
  private Button btn_create, btn_cancel;
  private SceneActivityViewModel viewModel;
  private SceneItemModel model;
  private int projectId;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.viewModel = new ViewModelProvider(getActivity()).get(SceneActivityViewModel.class);
    this.projectId = getArguments().getInt("projectId");
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
    this.te_story = binding.teStory;
    this.tl_story = binding.tlStory;
    this.btn_create = binding.btnCreate;
    this.btn_cancel = binding.btnCancel;
  }

  private void onsetViews() {
    btn_create.setOnClickListener(
        v -> {
          String scene_name = te_name.getText().toString().trim();
          String scene_story = te_story.getText().toString().trim();

          if (TextUtils.isEmpty(scene_name)) {
            tl_name.setErrorEnabled(true);
            tl_name.setError("Name is empty");
          }
          if (TextUtils.isEmpty(scene_story)) {
            tl_story.setErrorEnabled(true);
            tl_story.setError("Story is empty");
          }

          if (!TextUtils.isEmpty(scene_name) && !TextUtils.isEmpty(scene_story)) {
            model = new SceneItemModel();
            model.setTitle(scene_name);
            model.setProjectId(projectId);
            model.setStory(scene_story);
            
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
