package com.akira.akirastoryboard.activities.editor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ActivityEditorBinding;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;
import com.google.android.material.appbar.MaterialToolbar;

public class EditorActivity extends AppCompatActivity {
  private ActivityEditorBinding binding;
  private MaterialToolbar toolBar;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private EditorAdapter adapter;
  private final EditorActivityViewModel viewModel = new EditorActivityViewModel();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivityEditorBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getEditorAdapter();

    onsetViewBinding();
    onsetViews();
    onsetViewModels();
  }

  private void onsetViewBinding() {
    this.toolBar = binding.toolBar;
	this.rv = binding.rvEditor;	
  }

  private void onsetViews() {
    toolBar.setTitle("Frame 1");
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
  }

  private void onsetViewModels() {
    viewModel
        .getList()
        .observe(
            this,
            list -> {
              adapter.submitList(list);
            });
  }
}
