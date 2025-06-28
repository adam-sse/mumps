package de.uni_hildesheim.mump.ui.courseList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import de.uni_hildesheim.mump.R;
import de.uni_hildesheim.mump.help_classes.CourseInfo; // Import your data class

public class CourseInfoAdapter extends RecyclerView.Adapter<CourseInfoAdapter.CourseInfoViewHolder> {

    private List<CourseInfo> courseInfoList;
    private OnItemClickListener listener; // Keep the click listener if needed

    // Interface for click events (if you want the whole item to be clickable)
    public interface OnItemClickListener {
        void onItemClick(CourseInfo item); // Now passes the whole CourseInfo object
    }

    public CourseInfoAdapter(List<CourseInfo> courseInfoList, OnItemClickListener listener) {
        this.courseInfoList = courseInfoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false); // Use your new item layout
        return new CourseInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseInfoViewHolder holder, int position) {
        CourseInfo currentCourse = courseInfoList.get(position);
        holder.bind(currentCourse, listener);
    }

    @Override
    public int getItemCount() {
        return courseInfoList.size();
    }

    public void addItems(List<CourseInfo> newItems) {
        int startIndex = courseInfoList.size();
        courseInfoList.addAll(newItems);
        notifyItemRangeInserted(startIndex, newItems.size());
    }

    // --- ViewHolder Class ---
    public static class CourseInfoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCourseName;

        public CourseInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseName = itemView.findViewById(R.id.textItem);
        }

        public void bind(final CourseInfo course, final OnItemClickListener clickListener) {
            textViewCourseName.setText(course.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onItemClick(course);
                    }
                }
            });
        }
    }
}