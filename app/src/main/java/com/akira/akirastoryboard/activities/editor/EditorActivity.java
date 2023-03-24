package com.akira.akirastoryboard.activities.editor;

import android.content.Intent;
import android.os.Bundle;
import com.akira.akirastoryboard.activities.editor.EditorActivityPresenter;
import com.akira.akirastoryboard.activities.editor.EditorActivityView;
import com.akira.akirastoryboard.common.activity.BaseActivity;
import com.akira.akirastoryboard.databinding.ActivityEditorBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.ArrayList;

public class EditorActivity extends BaseActivity<EditorActivityPresenter>
    implements EditorActivityView {
  private ActivityEditorBinding binding;

  @Override
  protected EditorActivityPresenter createPresenter() {
    return new EditorActivityPresenter(this);
  }

  @Override
  protected void injectDependencies() {}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivityEditorBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    FrameItemModel model =
        getIntent().getExtras() != null
            ? getParcelableExtraEachApi(getIntent(), "frame", FrameItemModel.class)
            : new FrameItemModel("", new ArrayList<CategoryItemModel>());
		
    Intent intent = new Intent();
    intent.putExtra("frame", model);
    setResult(RESULT_OK, intent);
    finish();
  }
}
