package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {//添加了一个通用超类

    protected abstract Fragment creatFragment();//为了实例化新的fragment，新增了名为creatFragment()的抽象方法;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager(); //获取FragmentManager类
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);//获取一个fragment事务

        if (fragment == null){
            fragment = creatFragment();//创建一个新的fragment事务，执行一个fragment添加操作，然后提交该事务
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();//fragment事务被用来添加，移除，附加，分离或替换fragment队列中的fragment
            //FragmentManger.beginTransaction()方法创建并返回FragmentTransaction实例
            //add方法是核心，第一个参数是容器视图资源id，第二个参数是新创建的CrimeFragment。
            //如果需要从FragmentManger中获取CrimeFragment，使用容器视图资源ID即可
        }
    }
}
