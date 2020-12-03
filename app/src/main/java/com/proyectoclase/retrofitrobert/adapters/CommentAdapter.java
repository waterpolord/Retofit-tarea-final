package com.proyectoclase.retrofitrobert.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyectoclase.retrofitrobert.models.Comment;
import com.proyectoclase.retrofitrobert.R;

import java.io.Serializable;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> implements Serializable {

    private final Context context;
    private List<Comment> elements;

    public CommentAdapter(Context context, List<Comment> elements)
    {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_commnents, parent, false);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CommentAdapter.MyViewHolder holder, int position) {
        Comment element = elements.get(position);
        holder.txtId.setText(String.valueOf(element.getId()));
        holder.txtTitle.setText(element.getName());
        holder.txtBody.setText(element.getBody());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtTitle;
        TextView txtBody;

        public MyViewHolder( View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
        }
    }

}
