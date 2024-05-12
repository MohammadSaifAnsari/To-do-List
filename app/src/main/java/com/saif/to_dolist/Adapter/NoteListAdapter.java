package com.saif.to_dolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.saif.to_dolist.Database.NotesModel;
import com.saif.to_dolist.R;

import javax.security.auth.callback.Callback;

public class NoteListAdapter  extends ListAdapter<NotesModel,NoteListAdapter.ViewHolder> {


    public NoteListAdapter(){
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<NotesModel> CALLBACK = new DiffUtil.ItemCallback<NotesModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull NotesModel oldItem, @NonNull NotesModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NotesModel oldItem, @NonNull NotesModel newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesModel notesModel = getItem(position);
        holder.recyclerTitle.setText(notesModel.getTitle());
        holder.recyclerDescription.setText(notesModel.getDescription());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView recyclerTitle,recyclerDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerTitle = itemView.findViewById(R.id.rv_title);
            recyclerDescription = itemView.findViewById(R.id.rv_Description);
        }
    }
}
