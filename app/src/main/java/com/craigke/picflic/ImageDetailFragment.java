package com.craigke.picflic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.craigke.picflic.model.Result;
import com.craigke.picflic.model.UnsplashAPIResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDetailFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.fullImageView)
    ImageView mFullImageView;
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.unsplashInfo)
    RelativeLayout mLinkToUnsplash;
    @BindView(R.id.saveImageButton)
    Button mSaveImageButton;
    @BindView(R.id.photographer) ImageView mPhotographer;


    private Result mPicture;
    public static final String FIREBASE_CHILD_PHOTO = "photo";


    public ImageDetailFragment() {
        // Required empty public constructor
    }

    public static ImageDetailFragment newInstance(Result image){
       ImageDetailFragment fragmentImageDetail = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("picture", Parcels.wrap(image));
        fragmentImageDetail.setArguments(args);
        return fragmentImageDetail;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPicture = Parcels.unwrap(getArguments().getParcelable("picture"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mPicture.getUrls().getRegular()).into(mFullImageView);
        Picasso.get().load(mPicture.getUser().getProfileImage().getLarge()).into(mPhotographer);
        mUserName.setText("Photo by " + mPicture.getUser().getInstagramUsername() + " on Unsplash");

//        mLinkToUnsplash.setOnClickListener(this);
        mSaveImageButton.setOnClickListener(this);
        mPhotographer.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveImageButton){

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uId = user.getUid();
            DatabaseReference reference = FirebaseDatabase
                    .getInstance()
                    .getReference(FIREBASE_CHILD_PHOTO)
                    .child(uId);
            DatabaseReference pushRef = reference.push();

            pushRef.setValue(mPicture);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mPhotographer){
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPicture.getLinks().getHtml()));
            startActivity(webIntent);
        }
    }
}
