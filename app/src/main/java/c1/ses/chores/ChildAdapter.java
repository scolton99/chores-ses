package c1.ses.chores;
/**
 * Jennifer Carballo
 * 8.8.2019
 * Handles the child preview tiles for the main parent page
 */

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder>{

    private List<Kid> children;
    private KidClickedListener listener;
    private KidClickedListener mkidClickedListener;

    public ChildAdapter(List<Kid> children, KidClickedListener mkidClickedListener) {
        this.children = children;
        this.mkidClickedListener = mkidClickedListener;
    }

    /**
     * Called when the UI needs the a new row (at {position}) to be <b>created</b>.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_child_tile, parent, false);
        return new ViewHolder(view, mkidClickedListener);
    }

    /**
     * Called when the UI needs the next row (at {position}) to be <b>filled with data</b> rendered
     * and passes the {@link ViewHolder} which should be filled with data.
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Kid currentKid = children.get(position);
        holder.childName.setText(currentKid.getName());
        holder.accountsTotal.setText("$" + currentKid.getAccountsTotal());
    }

    /**
     * Used to determine how many rows the list should be in total.
     */
    @Override
    public int getItemCount() {
        return children.size();
    }

    /**
     * Holds the UI widgets which will comprise a single row in the list (to render
     */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView childList;
        TextView childName;
        TextView accountsTotal;
        KidClickedListener kidClickedListener;

        ViewHolder(View rootView, KidClickedListener kidClickedListener) {
            super(rootView);
            childList = rootView.findViewById(R.id.childList);
            childName = rootView.findViewById(R.id.parentWelcome);
            accountsTotal = rootView.findViewById(R.id.accountsTotal);
            this.kidClickedListener = kidClickedListener;

            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            kidClickedListener.onKidClicked(getAdapterPosition());
        }
    }

    /**
     * Will receive callbacks whenever a kid in the list is clicked.
     */
    public interface KidClickedListener {
        void onKidClicked(int pos);
    }
}

