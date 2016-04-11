package com.geniusvjr.tech.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.geniusvjr.tech.R;
import com.geniusvjr.tech.adapter.MenuAdapter;
import com.geniusvjr.tech.beans.MenuItem;
import com.geniusvjr.tech.fragment.GuanyuFragment;
import com.geniusvjr.tech.fragment.ArticleListFragment;
import com.geniusvjr.tech.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActionBarActivity {

    protected FragmentManager mFragmentManager; // Fragment管理器
    Fragment mArticleFragment = new ArticleListFragment(); // 文章列表Fragment
    Fragment mAboutFragment; // 关于Fragment
    private DrawerLayout mDrawerLayout; // 菜单布局
    private RecyclerView mMenuRecyclerView; // 菜单RecyclerView
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;

    }

    @Override
    protected void initWidgets() {
        mFragmentManager = getFragmentManager();
        setupDrawerToggle();
        setupMenuRecyclerView();
        //显示文章列表
        addFragment(mArticleFragment);
    }

    private void setupDrawerToggle() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setupMenuRecyclerView() {
        mMenuRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //初始化菜单Adapter
        MenuAdapter menuAdapter = new MenuAdapter();
        menuAdapter.addItems(prepareMenuItems());
        menuAdapter.setOnItemClickListener(new OnItemClickListener<MenuItem>() {
            @Override
            public void onClick(MenuItem item) {
                clickMenuItem(item);
            }
        });
        mMenuRecyclerView.setAdapter(menuAdapter);
    }

    private List<MenuItem> prepareMenuItems()
    {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(getString(R.string.article), R.drawable.home));
        menuItems.add(new MenuItem(getString(R.string.about_menu), R.drawable.about));
        menuItems.add(new MenuItem(getString(R.string.exit), R.drawable.exit));
        return menuItems;
    }

    //点击菜单项的处理函数
    private void clickMenuItem(MenuItem item)
    {
        mDrawerLayout.closeDrawers();
        switch (item.iconResId)
        {
            //全部
            case R.drawable.home:
                mFragmentManager.beginTransaction().replace(R.id.articles_container, mArticleFragment)
                        .commit();
                break;
            case R.drawable.about:
                if(mAboutFragment == null)
                {
                    mAboutFragment = new GuanyuFragment();
                }
                mFragmentManager.beginTransaction()
                        .replace(R.id.articles_container, mAboutFragment)
                        .commit();
                break;
            case R.drawable.exit:
                isQuit();
                break;
            default:
                break;
        }
    }
    protected void addFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().add(R.id.articles_container, fragment).commit();
    }

    protected void replaceFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().replace(R.id.articles_container, fragment).commit();
    }

    private void isQuit() {
        new AlertDialog.Builder(this)
                .setTitle("确认退出?").setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("取消", null).create().show();
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mFragmentManager = getFragmentManager();
//
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle(R.string.app_name);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
//                R.string.drawer_open,
//                R.string.drawer_close);
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        mMenuRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
//        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//        List<MenuItem> menuItems = new ArrayList<MenuItem>();
//        menuItems.add(new MenuItem(getString(R.string.article), R.drawable.home));
//        menuItems.add(new MenuItem(getString(R.string.about_menu), R.drawable.about));
//        menuItems.add(new MenuItem(getString(R.string.exit), R.drawable.exit));
//
//        MenuAdapter menuAdapter = new MenuAdapter(menuItems);
//        menuAdapter.setOnItemClickListener(new OnItemClickListener<MenuItem>()
//        {
//            @Override
//            public void onClick(MenuItem item) {
//                clickMenuItem(item);
//            }
//        });
//        mMenuRecyclerView.setAdapter(menuAdapter);
//        mFragmentManager.beginTransaction().add(R.id.articles_container,mArticleFragment)
//                .commitAllowingStateLoss();
//    }

}
