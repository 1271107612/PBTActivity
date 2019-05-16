package com.example.dell.pbtactivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.dell.pbtactivity.Fragment.SchoolFragment;
import com.example.dell.pbtactivity.Fragment.HomeFragment;
import com.example.dell.pbtactivity.Fragment.ChatFragment;
/*
 * FragmentManager manager = getSupportFragmentManager();
 * FragmentTransaction transaction = manager.beginTransaction();
 * transaction.add(R.id.main_body,new CourseFragment()).commit();
 * */
//AppCom....原为FragmentActivity
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_main_title;//标题栏中的标题
    private RelativeLayout title_bar;// android:id="@+id/title_bar"
    //来自activity_main.xml
    private RelativeLayout main_body;
    private TextView bottom_bar_text_school;
    private ImageView bottom_bar_image_school;
    private TextView bottom_bar_text_home;
    private ImageView bottom_bar_image_home;
    private TextView bottom_bar_text_chat;
    private ImageView bottom_bar_image_chat;
    private LinearLayout main_bottom_bar;
    private RelativeLayout bottom_bar_school_btn;
    private RelativeLayout bottom_bar_home_btn;
    private RelativeLayout bottom_bar_chat_btn;
    private DrawerLayout mDrawerLayout;
    //对下面的 项 进行注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setMain();

        TextView tv_found=(TextView)findViewById(R.id.tv_found);
        tv_found.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v) {
                 Intent intentfound=new Intent(MainActivity.this,found.class);
                 startActivity(intentfound);
             }
        });
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.go_back_selector);
        }
    }
    //实例化
    private void initView(){
        //标题显示
        tv_main_title =findViewById(R.id.tv_main_title);
        title_bar = findViewById(R.id.title_bar);
        //底部导航栏
        bottom_bar_text_school=findViewById(R.id.bottom_bar_text_school);
        bottom_bar_image_school=findViewById(R.id.bottom_bar_image_school);
        bottom_bar_school_btn =findViewById(R.id.bottom_bar_school_btn);
        bottom_bar_text_home=findViewById(R.id.bottom_bar_text_home);
        bottom_bar_image_home=findViewById(R.id.bottom_bar_image_home);
        bottom_bar_home_btn=findViewById(R.id.bottom_bar_home_btn);
        bottom_bar_text_chat=findViewById(R.id.bottom_bar_text_chat);
        bottom_bar_image_chat=findViewById(R.id.bottom_bar_image_chat);
        bottom_bar_chat_btn=findViewById(R.id.bottom_bar_chat_btn);
        //包含底部Android：id="@+id/main_bottom_bar"
        main_bottom_bar=findViewById(R.id.main_bottom_bar);
        //添加点击事件
        bottom_bar_school_btn.setOnClickListener(this);
        bottom_bar_home_btn.setOnClickListener(this);
        bottom_bar_chat_btn.setOnClickListener(this);
        //tv_back.setOnClickListener(this);
        tv_main_title.setText("校园");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
    }
    //点击的底部按钮改变时  对改变的颜色还原 成素色
    private void clearBottomImageState() {
        bottom_bar_text_school.setTextColor(Color.parseColor("#666666"));
        bottom_bar_text_home.setTextColor(Color.parseColor("#666666"));
        bottom_bar_text_chat.setTextColor(Color.parseColor("#666666"));

        bottom_bar_image_school.setImageResource(R.drawable.main_school_icon);
        bottom_bar_image_home.setImageResource(R.drawable.main_home_icon);
        bottom_bar_image_chat.setImageResource(R.drawable.main_chat_icon);
    }
    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:

                bottom_bar_image_school.setImageResource(R.drawable.main_school_icon);
                bottom_bar_text_school.setTextColor(Color.parseColor("#0097f7"));
                tv_main_title.setText("校  园");//将标题栏定义的文本  进行更改
                break;
            case 1:

                bottom_bar_image_home.setImageResource(R.drawable.main_home_icon);
                bottom_bar_text_home.setTextColor(Color.parseColor("#0097f7"));
                tv_main_title.setText("社  团");
                break;
            case 2:

                bottom_bar_image_chat.setImageResource(R.drawable.main_chat_icon);
                bottom_bar_text_chat.setTextColor(Color.parseColor("#0097f7"));
                tv_main_title.setText("消  息");
                break;
        }
    }
    //用于打开初始页面
//起始时的  底部栏的状态
    private void setMain() {
        //getSupportFragmentManager() -> beginTransaction() -> add -> (R.id.main_boy,显示课程 new CourseFragment()
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new SchoolFragment()).commit();
    }
    @Override
    //点击事件
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_bar_school_btn:
                clearBottomImageState();
                /**  replacing instead of adding **/
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body, new SchoolFragment()).commit();
                setSelectedStatus(0);
                break;
            case R.id.bottom_bar_home_btn:
                clearBottomImageState();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body, new HomeFragment()).commit();
                setSelectedStatus(1);
                break;
            case R.id.bottom_bar_chat_btn:
                clearBottomImageState();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body, new ChatFragment()).commit();
                setSelectedStatus(2);
                break;
        }
    }
    //右上角的查找按钮


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.found_item:
                Toast.makeText(this,"dasdw",Toast.LENGTH_SHORT).
                        show();
                break;
            case R.id.build_item:
                Toast.makeText(this,"dasdw",Toast.LENGTH_SHORT).
                        show();
                break;
                default:
        }
        return true;
    }
}
