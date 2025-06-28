package de.uni_hildesheim.mump.ui.courseList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false); // Ensure item_text.xml is your layout for a single item
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
        int startIndex = items.size(); // Get current size before adding
        items.addAll(newItems);
        notifyItemRangeInserted(startIndex, newItems.size());
    }


    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textItem;

        public TextViewHolder(View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.textItem);
        }

        public void bind(final String itemData, final OnItemClickListener clickListener) { // itemData is the specific String
            textItem.setText(itemData);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        // Use the 'itemData' that was passed to bind,
                        // which is the correct data for this ViewHolder.
                        clickListener.onItemClick(itemData);
                    }
                }
            });
        }
    }
}