package com.example.bbt_true_app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fabu_renwu;
    private ViewPager mViewPager;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bnv_mainactivity);
        bottomNavigationView.setItemIconTintList(null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final ArrayList<Fragment> fgLists=new ArrayList<>(3);
        fgLists.add(new MissionFragment());
        fgLists.add(new MessageFragment());
        fgLists.add(new MoreFragment());

        FragmentPagerAdapter mPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);  //得到Fragment
            }
            @Override
            public int getCount() {
                return fgLists.size();  //得到数量
            }
        };
        mViewPager.setAdapter(mPagerAdapter);//设置适配器
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2); //预加载剩下两页

        //以上就将Fragment装入了ViewPager
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    //根据navagatin.xml中item的id进行case
                    case R.id.mission_item:
                        mViewPager.setCurrentItem(0);
                        //跳到对应ViewPager的page
                        break;
                    case R.id.message_item:
                        mViewPager.setCurrentItem(1);

                        break;
                    case R.id.more_item:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });


        fabu_renwu = (FloatingActionButton) findViewById(R.id.fab);
        fabu_renwu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_fabu = new Intent(MainActivity.this,FabuActivity.class);
                startActivity(intent_fabu);
            }
        });

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.fabu);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.bars);
        }

        View headview = navigationView.inflateHeaderView(R.layout.nav_header);
        TextView rewrite = (TextView) headview.findViewById(R.id.rewrite);
        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"You clicked 编辑个人资料",Toast.LENGTH_SHORT).show();
                Intent intent_bianji = new Intent(MainActivity.this,BianjiActivity.class);
                startActivity(intent_bianji);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.fabu:
                        //Toast.makeText(MainActivity.this,"You clicked 发布任务",Toast.LENGTH_SHORT).show();
                        Intent intent_fabu = new Intent(MainActivity.this,FabuActivity.class);
                        startActivity(intent_fabu);
                        break;
                    case R.id.personal:
                        //Toast.makeText(MainActivity.this,"You clicked 个人主页",Toast.LENGTH_SHORT).show();
                        Intent intent_personal = new Intent(MainActivity.this,PersonalActivity.class);
                        startActivity(intent_personal);
                        break;
                    case R.id.looked:
                        //Toast.makeText(MainActivity.this,"You clicked 浏览记录",Toast.LENGTH_SHORT).show();
                        Intent intent_looked = new Intent(MainActivity.this,LookedActivity.class);
                        startActivity(intent_looked);
                        break;
                    case R.id.exit:
                        //Toast.makeText(MainActivity.this,"You clicked 退出账号",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent_login = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent_login);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(mDrawerLayout.isDrawerOpen(findViewById(R.id.nav_view)))
        {
            mDrawerLayout.closeDrawers();
        }
        else
            super.onBackPressed();
    }
}


