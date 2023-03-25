package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemGroupedSceneBinding;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.GroupedSceneViewHolder;

public class GroupedSceneAdapter
    extends BaseListAdapter<GroupedSceneItemModel, GroupedSceneViewHolder> {
  private GroupedSceneItemClickListener listener;

  public GroupedSceneAdapter(
      DiffUtil.ItemCallback<GroupedSceneItemModel> diffUtil,
      GroupedSceneItemClickListener listener) {
    super(diffUtil);
    this.listener = listener;
  }

  @Override
  public GroupedSceneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new GroupedSceneViewHolder(
        ItemGroupedSceneBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(GroupedSceneViewHolder holder, int position) {
    holder.bind(getItem(position), listener, position);
  }

  public interface GroupedSceneItemClickListener {
    void onGroupClick(int position, GroupedSceneItemModel model);
  }
}
