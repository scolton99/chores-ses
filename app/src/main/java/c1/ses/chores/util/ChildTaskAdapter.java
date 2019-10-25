package c1.ses.chores.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import c1.ses.chores.R;
import c1.ses.chores.models.Task;

public class ChildTaskAdapter extends RecyclerView.Adapter<ChildTaskAdapter.ViewHolder> {

    private final List<Task> tasks;

    public ChildTaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ChildTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_task, parent, false);
        return new ChildTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Task task = this.tasks.get(position);

        holder.title.setText(task.getName());

        DecimalFormat df = new DecimalFormat("$###,###.##");

        holder.subtitle.setText(df.format(task.getValue()));

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setComplete();



                tasks.remove(position);

                ChildTaskAdapter.this.notifyItemRemoved(position);
                ChildTaskAdapter.this.notifyItemRangeChanged(position, tasks.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final TextView subtitle;
        final ImageButton check;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.taskTitle);
            this.subtitle = itemView.findViewById(R.id.taskSubtitle);
            this.check = itemView.findViewById(R.id.taskCheck);
        }
    }
}
