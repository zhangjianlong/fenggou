package com.slash.youth.ui.viewmodel;

import android.app.AlertDialog;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityEditorIdentityBinding;
import com.slash.youth.databinding.DialogCustomSkilllabelBinding;
import com.slash.youth.ui.activity.EditorIdentityActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.PatternUtils;
import com.slash.youth.utils.ToastUtils;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by zss on 2016/11/1.
 */
public class EditorIdentityModel extends BaseObservable {
    private ActivityEditorIdentityBinding activityEditorIdentityBinding;
    private EditorIdentityActivity editorIdentityActivity;
    private DialogCustomSkillLabelModel dialogCustomSkillLabelModel;
    private int measuredllWidth;
    private int labelRightMargin;
    private ImageView imageViewAdd;
    private LinearLayout addSkillaLabel;
    private LinearLayout newSkillLabrel;
    public static ArrayList<String> newSkillLabelList  = new ArrayList<>();
    private int newSkillWidth;
    private int width = 0;
    private  int index = 0;
    private int spec;

    public EditorIdentityModel(ActivityEditorIdentityBinding activityEditorIdentityBinding, EditorIdentityActivity editorIdentityActivity) {
        this.activityEditorIdentityBinding = activityEditorIdentityBinding;
        this.editorIdentityActivity = editorIdentityActivity;
        initData();
        initView();

    }

    private void initData() {

    }

    private void initView() {
        activityEditorIdentityBinding.llAddSkillLabel.post(new Runnable() {
            @Override
            public void run() {
                //总的宽度
                measuredllWidth = activityEditorIdentityBinding.llAddSkillLabel.getMeasuredWidth();
                spec = View.MeasureSpec.makeMeasureSpec(measuredllWidth, View.MeasureSpec.AT_MOST);
                updateLableView(newSkillLabelList);
            }
        });
    }

    private View addSkillaLabel() {
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(-2, -2);
        TextView textview = new TextView(CommonUtils.getContext());
        textview.setLayoutParams(ll);
        textview.setMaxLines(1);
        textview.setGravity(Gravity.CENTER);
        textview.setTextColor(0xffffffff);
        textview.setTextSize(14);
        textview.setText("添加");
        textview.setPadding(CommonUtils.dip2px(8), CommonUtils.dip2px(12), CommonUtils.dip2px(15), CommonUtils.dip2px(12));
        imageViewAdd = new ImageView(CommonUtils.getContext());
        imageViewAdd.setImageResource(R.mipmap.pluse1_icon);
        imageViewAdd.setPadding(CommonUtils.dip2px(15), CommonUtils.dip2px(12), CommonUtils.dip2px(5), CommonUtils.dip2px(12));
        imageViewAdd.setLayoutParams(ll);
        LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(-2, -2);
        ll2.leftMargin = CommonUtils.dip2px(17);
        LinearLayout linearLayout = new LinearLayout(CommonUtils.getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(ll2);
        linearLayout.addView(imageViewAdd);
        linearLayout.addView(textview);
        linearLayout.setBackgroundResource(R.drawable.shape_userinfo_skilllabel);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
                dialogCustomSkillLabelModel.setOnOkDialogListener(new DialogCustomSkillLabelModel.OnOkDialogListener() {
                    @Override
                    public void OnOkDialogClick(String text) {
                    if(newSkillLabelList.size()>=4){
                        ToastUtils.shortToast("最多创建四个技能");
                    }else {
                        String regex="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
                        boolean match = PatternUtils.match(regex, text);
                        if(match){
                            newSkillLabelList.add(text);
                            updateLableView(newSkillLabelList);
                        }else {
                            ToastUtils.shortToast("斜杠身份只能包含中文,英文,数字");
                        }
                    }
                    }
                });
            }
        });
        return linearLayout;
    }

    private void addLinearLayoutView() {
        activityEditorIdentityBinding.llAddSkillLabel.removeAllViews();
        for (int i = 0; i < newSkillLabelList.size(); i++) {
            index = i;
            //创建新的标签
            newSkillLabrel = (LinearLayout) createSkillaLabel(newSkillLabelList.get(i));
            newSkillLabrel.measure(0,0);
            //新添加的标签的宽度
            newSkillWidth = newSkillLabrel.getMeasuredWidth();
            //总的新加标签宽度
            width = width +newSkillWidth;
            activityEditorIdentityBinding.llAddSkillLabel.addView(newSkillLabrel);
        }
        addSkillaLabel = (LinearLayout) addSkillaLabel();
        activityEditorIdentityBinding.llAddSkillLabel.addView(addSkillaLabel);
    }

    //创造技能标签
    private View createSkillaLabel(final String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        LinearLayout llCheckedLabel = new LinearLayout(CommonUtils.getContext());
        llCheckedLabel.setOrientation(LinearLayout.HORIZONTAL);
        params.leftMargin = CommonUtils.dip2px(17);
        llCheckedLabel.setLayoutParams(params);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(-2, -2);
        TextView textview = new TextView(CommonUtils.getContext());
        textview.setLayoutParams(ll);
        textview.setMaxLines(1);
        textview.setGravity(Gravity.CENTER);
        textview.setTextColor(0xffffffff);
        textview.setTextSize(14);
        textview.setText(text);
        textview.setPadding(CommonUtils.dip2px(15), CommonUtils.dip2px(12), CommonUtils.dip2px(15), CommonUtils.dip2px(12));
        LinearLayout.LayoutParams ivbtnParams = new LinearLayout.LayoutParams(-2, -2);
        ivbtnParams.topMargin = CommonUtils.dip2px(-23);
        ivbtnParams.rightMargin = CommonUtils.dip2px(-9);
        ImageButton ivbtnUnCheckedLabel = new ImageButton(CommonUtils.getContext());
        ivbtnUnCheckedLabel.setBackground(new ColorDrawable(Color.TRANSPARENT));
        ivbtnUnCheckedLabel.setImageResource(R.mipmap.close_icon_2);
        ivbtnParams.leftMargin = CommonUtils.dip2px(-7);
        ivbtnUnCheckedLabel.setLayoutParams(ivbtnParams);
        llCheckedLabel.addView(textview);
        llCheckedLabel.addView(ivbtnUnCheckedLabel);
        llCheckedLabel.setBackgroundResource(R.drawable.shape_userinfo_add_skilllabel);
        llCheckedLabel.setGravity(Gravity.CENTER);
        ivbtnUnCheckedLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSkillLabelList.remove(text);
                updateLableView(newSkillLabelList);
            }
        });
        return llCheckedLabel;
    }

    private void showCustomDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(editorIdentityActivity);
        DialogCustomSkilllabelBinding dialogCustomSkilllabelBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.dialog_custom_skilllabel, null, false);
        dialogCustomSkillLabelModel = new DialogCustomSkillLabelModel(dialogCustomSkilllabelBinding);
        dialogCustomSkilllabelBinding.setDialogCustomSkillLabelModel(dialogCustomSkillLabelModel);
        dialogBuilder.setView(dialogCustomSkilllabelBinding.getRoot());
        AlertDialog dialogCustomSkillLabel = dialogBuilder.create();
        dialogCustomSkillLabel.show();
        dialogCustomSkillLabelModel.currentDialog = dialogCustomSkillLabel;
        dialogCustomSkillLabel.setCanceledOnTouchOutside(false);
        Window dialogSubscribeWindow = dialogCustomSkillLabel.getWindow();
        WindowManager.LayoutParams dialogParams = dialogSubscribeWindow.getAttributes();
        dialogParams.width = CommonUtils.dip2px(300);//宽度
        dialogParams.height = CommonUtils.dip2px(190);//高度
        dialogSubscribeWindow.setAttributes(dialogParams);
//        dialogSubscribeWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialogSubscribeWindow.setDimAmount(0.1f);//dialog的灰度
//        dialogBuilder.show();
    }

    private int lineCount = 0;
    private int lineWidth = 0;
    public LinearLayout llSkilllabelLine;

    private void updateLableView(ArrayList<String> data) {
        lineWidth = 0;//每次刷新数据就赋值为0
        activityEditorIdentityBinding.llAddSkillLabel.removeAllViews();
        createLabelLine();//new
        for (int i = 0; i < data.size(); i++) {
            //测量标签TextView的宽度并判断是否换行
            adddView(createSkillaLabel( data.get(i)));
        }
        adddView(addSkillaLabel());
        activityEditorIdentityBinding.llAddSkillLabel.addView(llSkilllabelLine);
    }

    public void createLabelLine() {
        //创建Line
        LinearLayout.LayoutParams llParamsForLine = new LinearLayout.LayoutParams(-1, -2);
        if (lineCount % 2 == 0) {
            llParamsForLine.topMargin = CommonUtils.dip2px(5);
        } else {
            llParamsForLine.topMargin = CommonUtils.dip2px(5);
        }
        llSkilllabelLine = new LinearLayout(CommonUtils.getContext());
        llSkilllabelLine.setLayoutParams(llParamsForLine);
        llSkilllabelLine.setOrientation(LinearLayout.HORIZONTAL);
        lineCount++;
//                lineLabelCount = 0;
    }
    public static final String TAG="addView";
    public void adddView(View childView){
        childView.measure(0, 0);
        int childWidth = childView.getMeasuredWidth()+(( LinearLayout.LayoutParams)childView.getLayoutParams()).leftMargin;
        if ((lineWidth + childWidth) > measuredllWidth) {
            activityEditorIdentityBinding.llAddSkillLabel.addView(llSkilllabelLine);
            createLabelLine();
            lineWidth=0;
        }
        lineWidth += childWidth;
        llSkilllabelLine.addView(childView);
    }
}
