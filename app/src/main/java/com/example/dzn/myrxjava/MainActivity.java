package com.example.dzn.myrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
        findViewById(R.id.remove_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.removeItem(myAdapter.getItemCount() / 2);
            }
        });
        findViewById(R.id.add_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.insertItem(new User("插入的目标带特效"), myAdapter.getItemCount() / 2);
            }
        });
        findViewById(R.id.add_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.addItemNormal(myAdapter.getItemCount() / 2, new User("插入的目标无特效"));
            }
        });
        findViewById(R.id.add_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> insertItems = new ArrayList<User>();
                for (int i = 0; i < 4; i++) {
                    insertItems.add(new User("组插入目标" + i));
                }
                myAdapter.insertItems(insertItems, myAdapter.getItemCount() / 2);
            }
        });


        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerview);
        initRecyclerView();
    }


    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llmanager);
        List<User> listDate = new ArrayList<User>();
        for (int i = 0; i < 10; ++i) {
            listDate.add(new User("我是item" + i));
        }
        myAdapter = new MyAdapter(listDate, this);
        recyclerView.setAdapter(myAdapter);
//        recyclerView.addItemDecoration(new MyBorderDecoration(this, R.drawable.v_line_ic, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new ItemOffsets(getResources().getDimensionPixelSize(R.dimen.spase)));
        myAdapter.setMyItemListener(new MyItemListener() {
            @Override
            public void itemOnClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击的item位置为" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void subViewOnClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了子视图位置" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemOnLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长按子视图位置" + position, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("滑动状态", "" + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Toast.makeText(MainActivity.this, llmanager.findFirstVisibleItemPosition() + "   " + llmanager.findLastVisibleItemPosition(), Toast.LENGTH_SHORT).show();
                Log.i("顶部位置", "" + llmanager.findFirstVisibleItemPosition());
                Log.i("尾部位置", "" + llmanager.findLastVisibleItemPosition());

            }
        };
        recyclerView.setOnScrollListener(onScrollListener);
    }

    public void test() {
        final TestBean test = new TestBean("nima", "wocao", "nimei");
        Observable<Object> myObservable = Observable.create(
                new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        e.onNext(test);
                    }
                }
        );
        Consumer<Object> mySuvscriber = new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                TestBean test = (TestBean) o;
                Toast.makeText(MainActivity.this, test.getName() + test.getSchool() + test.getSex(), Toast.LENGTH_SHORT).show();
            }
        };
        myObservable.subscribe(mySuvscriber);
    }

}
