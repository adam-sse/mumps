package de.uni_hildesheim.mump.ui.leaderBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.uni_hildesheim.mump.R;
import de.uni_hildesheim.mump.dto.UserDto;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private List<UserDto> users;
    private final String currentUserId;

    public LeaderBoardAdapter(List<UserDto> users, String currentUserId) {
        this.users = users;
        this.currentUserId = currentUserId;
    }

    public void updateItems(List<UserDto> newUsers) {
        this.users = newUsers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserDto user = users.get(position);
        holder.tvRank.setText(String.valueOf(position + 1));
        holder.tvName.setText(user.userID());
        holder.tvPoints.setText(String.format("%,d", user.points()));

        // Highlight if this is the current user
        if (user.userID().equals(currentUserId)) {
            holder.itemView.setBackgroundResource(R.drawable.current_user_bg);
            holder.tvRank.setTextColor(0xFFFFFFFF);
            holder.tvName.setTextColor(0xFFFFFFFF);
            holder.tvPoints.setTextColor(0xFFFFFFFF);
        } else {
            holder.itemView.setBackgroundResource(android.R.color.transparent);
            holder.tvRank.setTextColor(0xFF6B7280);
            holder.tvName.setTextColor(0xFF1F2937);
            holder.tvPoints.setTextColor(0xFF6366F1);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRank, tvName, tvPoints;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPoints = itemView.findViewById(R.id.tv_points);
        }
    }
}
