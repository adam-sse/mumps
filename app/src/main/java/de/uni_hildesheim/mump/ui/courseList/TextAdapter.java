package de.uni_hildesheim.mump.ui.courseList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.uni_hildesheim.mump.R;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {

    private final List<String> items;

    public TextAdapter(List<String> items) {
        this.items = items;
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textItem;

        public TextViewHolder(View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.textItem);
        }
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.textItem.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<String> newItems) {
        int startIndex = items.size();
        items.addAll(newItems);
        notifyItemRangeInserted(startIndex, newItems.size());
    }
}
