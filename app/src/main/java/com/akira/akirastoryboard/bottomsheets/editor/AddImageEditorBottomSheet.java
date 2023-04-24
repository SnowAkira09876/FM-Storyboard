package com.akira.akirastoryboard.bottomsheets.editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.akira.akirastoryboard.databinding.BottomSheetAddImageEditorBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddImageEditorBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddImageEditorBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    this.binding = BottomSheetAddImageEditorBinding.inflate(inflater);
    onsetViewBinding();
    onsetViews();
    return binding.getRoot();
  }

  private void onsetViewBinding() {
    // TODO: Implement this method

  }

  private void onsetViews() {
    // TODO: Implement this method

  }
}
