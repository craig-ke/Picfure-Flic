package com.craigke.picflic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.craigke.picflic.ImageDetailActivity;
import com.craigke.picflic.ImageDetailFragment;
import com.craigke.picflic.R;
import com.craigke.picflic.model.Result;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.ImageViewHolder> {
private Context mContext;
private List<Result> mResult;

public ListImageAdapter(Context context, List<Result> results) {
        mContext = context;
        mResult = results;
        }
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.userNameView) TextView mUserTextView;
    @BindView(R.id.categoryTextView) TextView mImageDescription;
    @BindView(R.id.ratingTextView) TextView mRatingView;
    @BindView(R.id.display_image) ImageView mImageView;

    private Context context;

    public ImageViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
    }
    public void bindImageViewer(Result results){
        Picasso.get().load(results.getUrls().getRegular()).into(mImageView);
        mUserTextView.setText(results.getUser().getUsername());
        mImageDescription.setText(results.getDescription());
        mRatingView.setText("People:" + results.getUser().getTotalLikes()+ "/Likes");
    }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ImageDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("pictures", Parcels.wrap(mResult));
            mContext.startActivity(intent);
        }
    }
    @Override
    public int getItemCount(){
        return mResult.size();
    }
    @Override
    public ListImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewTypes){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_image, parent,false);
        ImageViewHolder viewHolder = new ImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageAdapter.ImageViewHolder holder, int position) {
        holder.bindImageViewer(mResult.get(position));
}
}


//    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();
//    private Context mContext;
//
//    public RestaurantListAdapter(Context context, ArrayList<Restaurant> restaurants){
//        mContext = context;
//        mRestaurants = restaurants;
//    }
//
//    @Override
//    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
//        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RestaurantListAdapter.RestaurantViewHolder holder, int position){
//        holder.bindRestaurant(mRestaurants.get(position));
//    }
//
//    @Override
//    public int getItemCount(){
//        return mRestaurants.size();
//    }
//
//public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//    @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;
//    @BindView(R.id.restaurantNameTextView) TextView mNameTextView;
//    @BindView(R.id.categoryTextView) TextView mCategoryTextView;
//    @BindView(R.id.ratingTextView) TextView mRatingTextView;
//
//    private Context mContext;
//
//    public RestaurantViewHolder(View itemView){
//        super(itemView);
//        ButterKnife.bind(this, itemView);
//        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
//    }
//
//    public void bindRestaurant(Restaurant restaurant) {
//        mNameTextView.setText(restaurant.getName());
//        mCategoryTextView.setText(restaurant.getCategories().get(0));
//        mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
//        Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
//    }
//
//    @Override
//    public void onClick(View v){
//        int itemPosition = getLayoutPosition();
//        Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
//        intent.putExtra("position", itemPosition);
//        intent.putExtra("restaurants", Parcels.wrap(mRestaurants));
//        mContext.startActivity(intent);
//    }
//}
//}
//
