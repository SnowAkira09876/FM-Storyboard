package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.ProjectViewHolder;

public class ProjectAdapter extends BaseListAdapter<ProjectItemModel, ProjectViewHolder> {
  private ProjectItemClickListener listener;

  public ProjectAdapter(
      DiffUtil.ItemCallback<ProjectItemModel> diffUtil, ProjectItemClickListener listener) {
    super(diffUtil);
    this.listener = listener;
  }

  @Override
  public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ProjectViewHolder(
        ItemProjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(ProjectViewHolder holder, int position) {
    holder.bind(getItem(position), listener, position);
  }

  @Override
  public void onRowMoved(int fromPosition, int toPosition) {
    notifyItemMoved(fromPosition, toPosition);
  }

  @Override
  public void onRowSelected(RecyclerView.ViewHolder viewHolder) {
    ((ProjectViewHolder) viewHolder).onRowSelected();
  }

  @Override
  public void onRowClear(RecyclerView.ViewHolder viewHolder) {
    ((ProjectViewHolder) viewHolder).onRowClear();
  }

  public interface ProjectItemClickListener {
    void onProjectClick(int position, ProjectItemModel model);

    void onProjectLongClick(int position, ProjectItemModel model);

    void requestDrag(ViewHolder viewHolder);
  }
}
