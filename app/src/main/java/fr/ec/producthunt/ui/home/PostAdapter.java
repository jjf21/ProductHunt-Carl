package fr.ec.producthunt.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Post;

public class PostAdapter extends BaseAdapter {

    private static final int TYPE_ITEM_FIRST = 0;

    private static final int TYPE_ITEM = 1;

    private List<Post> dataSource = Collections.emptyList();

    public PostAdapter() {
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {

        return position == 0 ? TYPE_ITEM_FIRST : TYPE_ITEM;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        int type = getItemViewType(position);

        //if (convertView == null) {

        viewHolder = new ViewHolder();

        switch (type) {

            case TYPE_ITEM_FIRST:
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_big, parent, false);
                break;
            case TYPE_ITEM:
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item, parent, false);
                break;

        }

        viewHolder.title = convertView.findViewById(R.id.title);
        viewHolder.subTitle = convertView.findViewById(R.id.sub_title);
        viewHolder.postImage = convertView.findViewById(R.id.img_product);
        viewHolder.commentsCount = convertView.findViewById(R.id.comments_count);

        //convertView.setTag(viewHolder);
        //} else {

        //viewHolder = (ViewHolder) convertView.getTag();
        //}

        Post post = dataSource.get(position);
        viewHolder.title.setText(post.getTitle());
        viewHolder.subTitle.setText(post.getSubTitle());
        viewHolder.commentsCount.setText("Comments : "+post.getCommentsCount());


        Picasso.with(parent.getContext())
                .load(post.getImageUrl())
                .centerCrop()
                .fit()
                .into(viewHolder.postImage);

        return convertView;
    }

    public void showPosts(List<Post> posts) {
        dataSource = posts;

        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView title;
        TextView subTitle;
        ImageView postImage;
        TextView commentsCount;
    }
}
