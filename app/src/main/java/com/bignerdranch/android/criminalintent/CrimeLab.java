package com.bignerdranch.android.criminalintent;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;//s前缀表示其是一个静态变量

    private List<Crime> mCrimes;//变量声明语句。

    protected static CrimeLab get(Context context){//创建单例
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes=new ArrayList<>();//实例化
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }

    public List<Crime> getCrimes(){//返回数组列表
        return mCrimes;
    }

    public Crime getCrime(UUID id){//返回带有指定ID的Crime对象
        for (Crime crime:mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
