package com.viewpagerindicator.as.sample.recycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.viewpagerindicator.as.recycler.pageview.CheeseListFragment;
import com.viewpagerindicator.as.recycler.pageview.FragmentStatePagerAdapter;

import java.util.LinkedHashMap;

/**
 * Created by buyi on 15/12/23.
 */
class FragmentsAdapter extends FragmentStatePagerAdapter {
    LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position, Fragment.SavedState savedState) {
        Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                : new CheeseListFragment();
//        Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey
//                (position));
        if (savedState == null || f.getArguments() == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            f.setArguments(bundle);
//            Log.e("test", "setArguments:" + position);
        } else if (mFragmentCache.containsKey(position)) {
            f.setInitialSavedState(savedState);
//            Log.e("test", "setInitialSavedState:" + position);
        }
        mFragmentCache.put(position, f);
        return f;
    }

    @Override
    public void onDestroyItem(int position, Fragment fragment) {
        // onDestroyItem
        while (mFragmentCache.size() > 5) {
            Object[] keys = mFragmentCache.keySet().toArray();
            mFragmentCache.remove(keys[0]);
        }
    }


    public String getPageTitle(int position) {
        return "item-" + position;
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}