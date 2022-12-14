package com.softaai.expandablerecyclerviewdemo;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AdColony.configure(this,           // activity context
//                "appdcff5318551c4d299b",
//                "vz900471727cc240169f", "vz4f9ee2f5315048a599");
//        AppLovinSdk.initializeSdk(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
       // actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
		navigationView.setVisibility(View.GONE);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					menuItem.setChecked(true);
					mDrawerLayout.closeDrawers();
					Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
					return true;
				}
			});

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Snackbar.make(findViewById(R.id.coordinator), "I'm a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								Toast.makeText(MainActivity.this, "Snackbar Action", Toast.LENGTH_LONG).show();
							}
						}).show();
				}
			});

        DesignDemoPagerAdapter adapter = new DesignDemoPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        switch (id) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//            case R.id.action_settings:
//                return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public static class DesignDemoFragment extends Fragment {
        private static final String TAB_POSITION = "tab_position";

        public DesignDemoFragment() {

        }

        public static DesignDemoFragment newInstance(int tabPosition) {
            DesignDemoFragment fragment = new DesignDemoFragment();
            Bundle args = new Bundle();
            args.putInt(TAB_POSITION, tabPosition);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle args = getArguments();
            int tabPosition = args.getInt(TAB_POSITION);

			ArrayList<String> items = new ArrayList<String>();
            for (int i = 0; i < 50; i++) {
                items.add("Tab #" + tabPosition + " item #" + i);
            }

            View v =  inflater.inflate(R.layout.fragment_list_view, container, false);
            RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            //recyclerView.setAdapter(new BasicRecyclerAdapter(items));

            return null;
        }
    }

    static class DesignDemoPagerAdapter extends FragmentStatePagerAdapter {
        Context context;
        public DesignDemoPagerAdapter(FragmentManager fm, MainActivity mainActivity) {
            super(fm);
            context = mainActivity;
        }

        @Override
        public Fragment getItem(int position) {
			//return DesignDemoFragment.newInstance(position);
			if(position == 0)
			{
				return new BasicFragment();
			}
			else
			{
				return new ExpandableFragment();
				
			}
//			else if (position == 1)
//			{
//				return new AdvanceSettingFragment();
//			}
//			else
//			{
//				return new HelpFragment();
//			}
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
			//  return "Tab " + position;

			if(position == 0)
			{
				return context.getString(R.string.basic_tab_text);
			}
			else 
			{
				return context.getString(R.string.expandable_tab_text);
			}
			
        }
    }
	
	@Override
	public void onResume()
	{
		// TODO: Implement this method
		super.onResume();
	}

	@Override
	public void onPause()
	{
		// TODO: Implement this method
		super.onPause();
	}


	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
	
	
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       
    }

}
