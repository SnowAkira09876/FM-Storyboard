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
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.activities.main.MainActivityViewModel;
import com.akira.akirastoryboard.databinding.BottomSheetAddProjectBinding;
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

public class AddProjectBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddProjectBinding binding;
  private TextInputEditText te_path, te_project_name;
  private TextInputLayout tl_path, tl_project_name;
  private Button btn_create, btn_cancel;
  private MaterialButtonToggleGroup toggle_group;
  private MaterialButton checked_button;
  private final List<String> genres = new ArrayList<>();
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
    this.btn_create = binding.btnCreate;
    this.btn_cancel = binding.btnCancel;
    this.toggle_group = binding.btnToggleGroup;
    this.iv_cover = binding.ivCover;
  }

  private void onsetViews() {
    Picasso.get().load(R.drawable.sample).into(iv_cover);

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
                .into(iv_cover);
          }
        });

    toggle_group.addOnButtonCheckedListener(
        (group, checkedId, isChecked) -> {
          if (isChecked) {
            checked_button = binding.getRoot().findViewById(checkedId);
            genres.add(checked_button.getText().toString());
          }
        });
    btn_create.setOnClickListener(
        v -> {
          String img_path = te_path.getText().toString().trim();
          String project_name = te_project_name.getText().toString().trim();

          if (TextUtils.isEmpty(project_name)) {
            tl_project_name.setErrorEnabled(true);
            tl_project_name.setError("Name is empty");
          }

          if (TextUtils.isEmpty(img_path)) {
            tl_path.setErrorEnabled(true);
            tl_path.setError("Cover image is empty");
          }

          if (!TextUtils.isEmpty(img_path) && !TextUtils.isEmpty(project_name)) {
            model.setTitle(project_name);
            model.setScenes("0 scenes");
            model.setImagePath(img_path);
            model.setGenres(genres.isEmpty() ? "None" : TextUtils.join(", ", genres));

            viewModel.setNewProject(model);
          }
          
          dismiss();
        });

    btn_cancel.setOnClickListener(
        v -> {
          dismiss();
        });
  }
}
