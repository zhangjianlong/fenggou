package com.slash.youth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.slash.youth.R;
import com.slash.youth.databinding.ActivityEditorIdentityBinding;
import com.slash.youth.ui.viewmodel.EditorIdentityModel;
import com.slash.youth.utils.LogKit;

import java.util.ArrayList;

/**
 * Created by zss on 2016/11/1.
 */
public class EditorIdentityActivity extends Activity implements View.OnClickListener {

    private View root;
    private EditorIdentityModel editorIdentityModel;
    private ActivityEditorIdentityBinding activityEditorIdentityBinding;
    private Intent intent;
    private StringBuffer sb = new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        activityEditorIdentityBinding = DataBindingUtil.setContentView(this, R.layout.activity_editor_identity);
        editorIdentityModel = new EditorIdentityModel(activityEditorIdentityBinding,this);
        activityEditorIdentityBinding.setEditorIdentityModel(editorIdentityModel);
        initView();
        back();
    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.tv_userinfo_title);
        title.setText("编辑斜杠职业");
        TextView tvfinish = (TextView) findViewById(R.id. tv_userinfo_save);
        tvfinish.setVisibility(View.VISIBLE);
        tvfinish.setText("完成");
        tvfinish.setOnClickListener(this);
        ImageView ivmenu = (ImageView) findViewById(R.id.iv_userinfo_menu);
        ivmenu.setVisibility(View.GONE);
    }

    //返回键
    private void back() {
        findViewById(R.id.iv_userinfo_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //点击事件,完成
    @Override
    public void onClick(View v) {
        ArrayList<String> newSkillLabelList = editorIdentityModel.newSkillLabelList;
        if(!newSkillLabelList.isEmpty()){
            for (int i = 0; i < newSkillLabelList.size(); i++) {
                if(i!=newSkillLabelList.size()-1&&i>=0){
                    sb.append(newSkillLabelList.get(i));
                    sb.append("/");
                }else {
                    sb.append(newSkillLabelList.get(i));
                }
            }
            intent.putExtra("identity",sb.toString());
            setResult(RESULT_OK,intent);
        }
        finish();
    }
}