package com.akira.akirastoryboard.bottomsheets.project;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.R;
import com.akira.akirastoryboard.activities.main.MainActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetAddProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.UUID;

public class AddProjectBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddProjectBinding binding;
  private TextInputEditText te_path, te_project_name, te_info, te_number;
  private TextInputLayout tl_path, tl_project_name, tl_info, tl_number;
  private Button btn_create, btn_cancel;
  private ProjectItemModel model;
  private MainActivityViewModel viewModel;
  private ShapeableImageView iv_cover;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    this.model = new ProjectItemModel();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    this.binding = BottomSheetAddProjectBinding.inflate(inflater);
    onsetViewBinding();
    onsetViews();
    return binding.getRoot();
  }

  private void onsetViewBinding() {
    this.te_path = binding.tePath;
    this.tl_path = binding.tlPath;
    this.te_project_name = binding.teName;
    this.tl_project_name = binding.tlName;
    this.te_info = binding.teInfo;
    this.tl_info = binding.tlInfo;

    this.btn_create = binding.btnCreate;
    this.btn_cancel = binding.btnCancel;
    this.iv_cover = binding.ivCover;
    this.te_number = binding.teProjectNumber;
    this.tl_number = binding.tlProjectNumber;
  }

  private void onsetViews() {
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
                .into(iv_cover);
          }
        });

    btn_create.setOnClickListener(
        v -> {
          String img_path = te_path.getText().toString().trim();
          String project_name = te_project_name.getText().toString().trim();
          String info = te_info.getText().toString().trim();
          String number = te_number.getText().toString().trim();

          if (TextUtils.isEmpty(project_name)) {
            tl_project_name.setErrorEnabled(true);
            tl_project_name.setError("Name is empty");
          }

          if (TextUtils.isEmpty(img_path)) {
            tl_path.setErrorEnabled(true);
            tl_path.setError("Cover image is empty");
          }

          if (TextUtils.isEmpty(info)) {
            tl_info.setErrorEnabled(true);
            tl_info.setError("Info is empty");
          }

          if (TextUtils.isEmpty(number)) {
            tl_number.setErrorEnabled(true);
            tl_number.setError("Project number is empty");
          }

          if (!TextUtils.isEmpty(img_path)
              && !TextUtils.isEmpty(project_name)
              && !TextUtils.isEmpty(info)
              && !TextUtils.isEmpty(number)) {
            model.setTitle(project_name);
            model.setImagePath(img_path);
            model.setInfo(info);
            model.setProjectId(UUID.randomUUID().toString());
            model.setNumber(Integer.parseInt(number));

            viewModel.setNewProject(model);
            dismiss();
          }
        });

    btn_cancel.setOnClickListener(
        v -> {
          dismiss();
        });
  }
}
