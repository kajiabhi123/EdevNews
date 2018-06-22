package com.example.designmodal.edevnews.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{

    public SectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
//                tab_profile_frag profile=new tab_profile_frag();
//                return profile;
            case 1:
//                tab_post_job_frag postjob=new tab_post_job_frag();
//                return postjob;
            case 2:
//                tab_jobs_posted_frag jobsposted= new tab_jobs_posted_frag();
//                return jobsposted;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "प्रमुख खबर";
            case 1:
                return "लोकप्रिय";
            case 2:
                return "ाजा अपडेट";

        }
        return null;
    }
}
