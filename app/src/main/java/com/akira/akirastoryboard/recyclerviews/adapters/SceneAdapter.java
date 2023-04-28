package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.SceneViewHolder;

public class SceneAdapter extends BaseListAdapter<SceneItemModel, SceneViewHolder> {
  private SceneItemClickListener listener;

  public SceneAdapter(
      DiffUtil.ItemCallback<SceneItemModel> diffUtil, SceneItemClickListener listener) {
    super(diffUtil);
    this.listener = listener;
  }

  @Override
  public SceneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SceneViewHolder(
        ItemSceneBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(SceneViewHolder holder, int position) {
    holder.bind(getItem(position), listener, position);
  }

  @Override
  public void onRowMoved(int fromPosition, int toPosition) {}

  @Override
  public void onRowSelected(RecyclerView.ViewHolder viewHolder) {}

  @Override
  public void onRowClear(RecyclerView.ViewHolder viewHolder) {}

  public interface SceneItemClickListener {
    void onSceneClick(int position, SceneItemModel model);

    void onSceneLongClick(int position, SceneItemModel model);
  }
}
