package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private static int mCrimeIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStatew){
        View view =inflater.inflate(R.layout.fragment_crime_list,container,false);
             mCrimeRecyclerView=(RecyclerView)view.findViewById(R.id.crime_recycler_view);
             mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

             updateUI();

             return view;
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private ImageView mSolvedImageView;


        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            itemView.setOnClickListener(this);

            mTitleTextView=(TextView)itemView.findViewById(R.id.crime_title);
            mDateTextView=(TextView)itemView.findViewById(R.id.crime_date);
            mSolvedImageView=(ImageView)itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            
            Date d=mCrime.getDate();
            SimpleDateFormat fm = new SimpleDateFormat("EEE， MMM dd， yyyy", Locale.ENGLISH);
            mDateTextView.setText(fm.format(d).toString());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public  void onClick(View view){
            Intent intent=CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            mCrimeIndex=getAdapterPosition();
            startActivity(intent);
        }
    }

    private class requirePoliceCrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Button mRequirePolice;
        private Crime mCrime;
        private ImageView mSolvedImageView;

        public  requirePoliceCrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_require_crime,parent,false));
            itemView.setOnClickListener(this);

            mTitleTextView=(TextView)itemView.findViewById(R.id.crime_title);
            mDateTextView=(TextView)itemView.findViewById(R.id.crime_date);
            mRequirePolice=(Button)itemView.findViewById(R.id.require_police);
            mSolvedImageView=(ImageView)itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
            Date d=mCrime.getDate();
            DateFormat fm = new SimpleDateFormat("EEE， MMM dd， yyyy", Locale.ENGLISH);
            mDateTextView.setText(fm.format(d).toString());
        }
        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(),"Require Police!",Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter {
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public int getItemViewType(int position){
            if (mCrimes.get(position).getRequiresPolice()){
                return 1;
            } else{
                return  0;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());

            if (viewType==1)
                return new requirePoliceCrimeHolder(layoutInflater,parent);
            else
                return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
            if (holder instanceof CrimeHolder){
                Crime crime=mCrimes.get(position);
                ((CrimeHolder)holder).bind(crime);
            }else if (holder instanceof requirePoliceCrimeHolder){
                Crime crime=mCrimes.get(position);
                ((requirePoliceCrimeHolder)holder).bind(crime);
            }

        }

        @Override
        public int getItemCount(){
            return mCrimes.size();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_crime:
                Crime crime=new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent =CrimePagerActivity
                        .newIntent(getActivity(),crime.getId());
                startActivity(intent);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter==null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyItemChanged(mCrimeIndex);
        }
    }
}
