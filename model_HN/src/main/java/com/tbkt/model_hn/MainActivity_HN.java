package com.tbkt.model_hn;

import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tbkt.model_hn.fragment.FirstFragment;
import com.tbkt.model_hn.fragment.HomeFragment;
import com.tbkt.model_hn.fragment.MyFragment;
import com.tbkt.model_hn.fragment.ShopFragment;
import com.tbkt.model_hn.fragment.kejianFragment;
import com.tbkt.model_lib.Tools.Content;
import com.tbkt.model_lib.Tools.LogUtils;
import com.tbkt.model_lib.Tools.Util;
/**
 * @Author: DBJ 
 * @Date: 2018/6/8 11:21

 * @Description:
 *
 */
@Route(path = Content.GOTO_HENAN)
public class MainActivity_HN extends AppCompatActivity{
    FrameLayout content ;
    BottomNavigationViewEx bottomNavigationItemView;
    FragmentManager manager ;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__hn);
        init();
        int bbb = Util.getAppVersionCode(MainActivity_HN.this,"com.tbkt.model_hn");
        LogUtils.showLog("河南版本号---",bbb+"");
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i=item.getItemId();
            if (i == R.id.bottom_nav_first) {
                FirstFragment first= new FirstFragment();
                replace(first,"first");
            } else if (i == R.id.bottom_nav_home) {
                HomeFragment home= new HomeFragment();
                replace(home,"home");
            } else if (i == R.id.bottom_nav_kejian) {
                kejianFragment kejian= new kejianFragment();
                replace(kejian,"kejian");
            } else if (i == R.id.bottom_nav_shop) {
                ShopFragment shop= new ShopFragment();
                replace(shop,"shop");
            } else if (i == R.id.bottom_nav_my) {
                MyFragment my= new MyFragment();
                replace(my,"my");
            }
            return true;
        }

    };
    private void init() {

        bottomNavigationItemView=findViewById(R.id.bottom_nav);
//        bottomNavigationItemView.getMenu().findItem(R.id.bottom_nav_first).set;
        bottomNavigationItemView.getMenu().findItem(R.id.bottom_nav_first).setTitle("动态更换");
//        bottomNavigationItemView.getMenu().findItem(R.id.bottom_nav_my).setVisible(false);
        bottomNavigationItemView.enableAnimation(true);
        bottomNavigationItemView.enableShiftingMode(false);
        bottomNavigationItemView.enableItemShiftingMode(false);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content=(FrameLayout)findViewById(R.id.framelayout);
        manager=getSupportFragmentManager();
        FirstFragment first= new FirstFragment();
        replace(first,"first");
    }
    private void replace(Fragment fragment,String index){
        transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout, fragment,index);
        transaction.commit();
    }
}
