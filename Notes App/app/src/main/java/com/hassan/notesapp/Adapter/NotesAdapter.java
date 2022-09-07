package com.hassan.notesapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.notesapp.Activity.UpdateNotesActivity;
import com.hassan.notesapp.MainActivity;
import com.hassan.notesapp.Model.NotesEntity;
import com.hassan.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<NotesEntity> notesEntities;
    List<NotesEntity> allNotesItem;

    public NotesAdapter(MainActivity mainActivity, List<NotesEntity> notesEntities) {

        this.mainActivity = mainActivity;
        this.notesEntities = notesEntities;
        allNotesItem = new ArrayList<>(notesEntities);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchNotes(List<NotesEntity> filteredName){

        this.notesEntities = filteredName;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.notes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {

        NotesEntity note = notesEntities.get(position);

        holder.title.setText(note.notesTitle);
        holder.subTitle.setText(note.notesSubTitle);
        holder.notesDate.setText(note.notesDate);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
                intent.putExtra("id",note.id);
                intent.putExtra("title",note.notesTitle);
                intent.putExtra("subtitle",note.notesSubTitle);
                intent.putExtra("notesdata",note.notesData);
                intent.putExtra("priority",note.notesPriority);
                mainActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesEntities.size();
    }

    static class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle, notesDate;
        View notesPriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subTitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);


        }
    }
}
