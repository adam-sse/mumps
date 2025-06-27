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
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String courseName);
    }

    public TextAdapter(List<String> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textItem;

        public TextViewHolder(View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.textItem);
        }

        public void bind(String item, OnItemClickListener listener) {
            textItem.setText(item);
            itemView.setOnClickListener(v -> listener.onItemClick(item));
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
        holder.bind(items.get(position), listener);
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
