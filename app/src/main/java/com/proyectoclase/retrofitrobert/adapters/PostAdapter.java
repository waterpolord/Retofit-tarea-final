package com.proyectoclase.retrofitrobert.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectoclase.retrofitrobert.fragments.fragmentComment;
import com.proyectoclase.retrofitrobert.models.Post;
import com.proyectoclase.retrofitrobert.R;

import java.io.Serializable;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> implements Serializable {

    private List<Post> elments;
    private final Context context;
    private static View.OnClickListener clickListener;

    public PostAdapter(Context context, List<Post> elements)
    {
        this.context = context;
        this.elments = elements;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,parent,false);
        //view.setOnClickListener( clickListener);
        return new PostAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostAdapter.MyViewHolder holder, int position) {
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getIntent().putExtra("Position",position);
                activity.getIntent().putExtra("Title",holder.title.getText().toString());
                Fragment myFragment = new fragmentComment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();
            }
        };
        Post element = elments.get(position);
        holder.id.setText(String.valueOf(element.getId()));
        holder.id.setOnClickListener(clickListener);
        holder.title.setText(element.getTitle());
        holder.title.setOnClickListener(clickListener);
        holder.body.setText(element.getText());
        holder.body.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {
        return elments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,title,body;
        public MyViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.txt);
            title = v.findViewById(R.id.titulo);
            body = v.findViewById(R.id.description);
        }
    }
}
