package com.sh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     private Button insert,query;
     private  UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert=(Button)findViewById(R.id.insert);
        query=(Button)findViewById(R.id.query);
        insert.setOnClickListener(this);
        query.setOnClickListener(this);

        //初始化数据库
      DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(MainActivity.this,"user.db",null);
        DaoMaster daoMaster=new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.insert:
                insert();
                break;
            case R.id.query:
                query();
                break;

        }

    }
   //查询数据
    private void query() {
        Toast.makeText(this, "点击了 queyt", Toast.LENGTH_SHORT).show();
        List<User> list = userDao.queryBuilder().build().list();
        for (User q:list){
            Log.e("MainActivity", "query: "+q.toString());
        }
    }

    //插入数据
    private void insert() {
        Toast.makeText(this, "点击了 insert", Toast.LENGTH_SHORT).show();
       for (int i=0;i<5;i++){
           User user=new User(null,"李宗书"+i,25);
           userDao.insert(user);
       }

    }
}
