package com.akira.akirastoryboard.bottomsheets.category;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.akira.akirastoryboard.databinding.BottomSheetAddCategoryBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddCategoryBottomSheet extends BottomSheetDialogFragment {
  private BottomSheetAddCategoryBinding binding;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
	this.binding = BottomSheetAddCategoryBinding.inflate(inflater);	
		
    return binding.getRoot();
  }
}
