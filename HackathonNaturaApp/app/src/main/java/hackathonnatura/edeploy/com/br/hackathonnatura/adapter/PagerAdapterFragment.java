package hackathonnatura.edeploy.com.br.hackathonnatura.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by vcmoraes on 12/07/16.
 */
public class PagerAdapterFragment extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public PagerAdapterFragment(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
