package com.homecredit.takehomeexam.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.homecredit.takehomeexam.R;

public class BaseContainerFragment extends Fragment{

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public boolean popFragment(){
        boolean hasPop = false;
        if(getChildFragmentManager().getBackStackEntryCount() > 0){
            getChildFragmentManager().popBackStack();
            hasPop = true;
        }
        return hasPop;
    }
}
