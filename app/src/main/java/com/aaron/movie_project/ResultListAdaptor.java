package com.aaron.movie_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaron.movie_project.model.Movie.Result;

import java.util.ArrayList;
import java.util.List;

import static com.aaron.movie_project.Constants.SERIALIZABLE_RESULT;

/**
 * Created by aaron on 9/8/17.
 */

public class ResultListAdaptor extends RecyclerView.Adapter<ResultListAdaptor.ViewHolder> {

    List<Result> resultList;
    Context context;
    int pos;

    public ResultListAdaptor(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMovieTitle = (TextView)itemView.findViewById(R.id.tvMovieTitle);

        }



    }



    @Override
    public ResultListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_result_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultListAdaptor.ViewHolder holder, int position) {
        final Result result = resultList.get(position);
        pos = position;
        holder.tvMovieTitle.setText(result.getTitle());
        holder.tvMovieTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ResultActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(SERIALIZABLE_RESULT, result);
                intent.putExtra(SERIALIZABLE_RESULT, b);
                v.getContext().startActivity(intent);

            }


        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


}
