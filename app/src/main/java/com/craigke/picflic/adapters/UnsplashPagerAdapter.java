package com.craigke.picflic.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.craigke.picflic.ImageDetailFragment;
import com.craigke.picflic.model.Result;

import java.util.List;

public class UnsplashPagerAdapter extends FragmentPagerAdapter {

    private List<Result> mPictures;

    public UnsplashPagerAdapter(FragmentManager fm, List<Result> mPictures) {
        super(fm);
        this.mPictures = mPictures;
    }

    @Override
    public Fragment getItem(int i) {
        return ImageDetailFragment.newInstance(mPictures.get(i));
    }

    @Override
    public int getCount() {
        return mPictures.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mPictures.get(position).getUser().getFirstName();
    }
}
