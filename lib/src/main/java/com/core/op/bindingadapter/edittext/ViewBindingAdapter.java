package com.core.op.bindingadapter.edittext;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.utils.StrUtil;


/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {


    @android.databinding.BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            editText.setFocusableInTouchMode(true);
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        } else {
            editText.setFocusable(false);
        }

    }

    @android.databinding.BindingAdapter({"textWatcher"})
    public static void textWatcher(EditText editText, final TextWatcher textWatcher) {
        if (textWatcher != null) {
            editText.addTextChangedListener(textWatcher);
        }
    }


    @BindingAdapter("app:intText")
    public static void bindIntegerInText(AppCompatEditText tv, int value) {
        if (!StrUtil.isEmpty(tv.getText())) {
            int oldValue = Integer.parseInt(tv.getText().toString());
            if (value == oldValue) {
                return;
            } else {
                tv.setText(String.valueOf(value));
            }
        } else {
            tv.setText(String.valueOf(value));
        }


    }

    @InverseBindingAdapter(attribute = "app:intText", event = "app:intTextAttrChanged")
    public static int getIntegerFromBinding(AppCompatEditText view) {
        if (StrUtil.isEmpty(view.getText())) {
            bindIntegerInText(view, 1);
            return 1;
        } else {
            view.setSelection(view.getText().length());
            int maxNum = Integer.parseInt(view.getText().toString());
            if (maxNum > 999) {
                maxNum = 999;
                bindIntegerInText(view, 999);

            }
            return maxNum;
        }

    }


    @android.databinding.BindingAdapter(value = {"beforeTextChangedCommand", "onTextChangedCommand", "afterTextChangedCommand", "intTextAttrChanged"}, requireAll = false)
    public static void editTextCommand(EditText editText,
                                       final ReplyCommand<TextChangeDataWrapper> beforeTextChangedCommand,
                                       final ReplyCommand<TextChangeDataWrapper> onTextChangedCommand,
                                       final ReplyCommand<String> afterTextChangedCommand, final InverseBindingListener intTextAttrChanged) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (beforeTextChangedCommand != null) {
                    beforeTextChangedCommand.execute(new TextChangeDataWrapper(s, start, count, count));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (intTextAttrChanged != null) {
                    intTextAttrChanged.onChange();
                }

                if (onTextChangedCommand != null) {
                    onTextChangedCommand.execute(new TextChangeDataWrapper(s, start, before, count));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (afterTextChangedCommand != null) {
                    afterTextChangedCommand.execute(s.toString());
                }
            }
        });
    }


    public static class TextChangeDataWrapper {
        public CharSequence s;
        public int start;
        public int before;
        public int count;

        public TextChangeDataWrapper(CharSequence s, int start, int before, int count) {
            this.s = s;
            this.start = start;
            this.before = before;
            this.count = count;
        }
    }


}

