package com.DBProject.heo.pit.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.ProjectBean;
import com.DBProject.heo.pit.R;

import java.util.ArrayList;

/**
 * Created by Heo on 2015. 10. 10..
 */

public class BaseExpandableAdapter extends BaseExpandableListAdapter {


    private ArrayList<String> groupList = null;
    private ArrayList<ArrayList<String>> childList = null;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;

    public BaseExpandableAdapter(Context c, ArrayList<String> groupList, ArrayList<ArrayList<String>> childList){

        super();
        this.inflater = LayoutInflater.from(c);
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public String getGroup(int groupPosition){
        return groupList.get(groupPosition);
    }

    @Override
    public int getGroupCount(){ return groupList.size();}

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        View v = convertView;

        ////////////////////////////////////////
        final int pos = groupPosition;
        final Context context = parent.getContext();
        /////////////////////////////////////////
        if(v == null){
            viewHolder = new ViewHolder();
            //v = inflater.inflate(R.layout.list_row, parent, false);

            v = inflater.inflate(R.layout.listview_button,parent,false);
            //////
            /*TextView text = (TextView) convertView.findViewById(R.id.textss);
            text.setText(groupList.get(pos));
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_button,parent,false);
            /////*/
            viewHolder.tv_groupName = (TextView)v.findViewById(R.id.tv_groups);
            viewHolder.Buttons_MakePro = (Button) v.findViewById(R.id.btn_project_make);
            viewHolder.Buttons_DeletePro = (Button) v.findViewById(R.id.btn_project_delete);
            viewHolder.Buttons_MakePro.setFocusable(false);
            viewHolder.Buttons_DeletePro.setFocusable(false);
            //viewHolder.tv_groupName = (TextView)v.findViewById(R.id.tv_groups);
            //viewHolder.iv_image = (ImageView)v.findViewById(R.id.iv_image);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }

        ////////////////////////////////

        viewHolder.Buttons_MakePro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 터치 시 해당 아이템 이름 출력
                Toast.makeText(context, "생성을 누름 " + groupList.get(pos), Toast.LENGTH_SHORT).show();
                CategoryBean.CateName = groupList.get(pos);
                Intent intent;
                intent = new Intent(context,ProjectWizard.class);
                context.startActivity(intent);
            }
        });

        viewHolder.Buttons_DeletePro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 터치 시 해당 아이템 이름 출력
                Toast.makeText(context, "삭제을 누름 " + groupList.get(pos), Toast.LENGTH_SHORT).show();
                CategoryBean.CateName = groupList.get(pos);
                Intent intent;
                intent = new Intent(context,ProjectWizard_Delete.class);
                context.startActivity(intent);
            }
        });


                /////////////////////////////////

        /*if(isExpanded){
            viewHolder.iv_image.setBackgroundColor(Color.GREEN);
        }else{
            viewHolder.iv_image.setBackgroundColor(Color.WHITE);
        }*/

        viewHolder.tv_groupName.setText(getGroup(groupPosition));

        return v;
    }

    @Override
    public String getChild(int groupPosition, int childPosition){
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return childList.get(groupPosition).size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        View v = convertView;

        ////////////////////////////////////////
        final int pos = groupPosition;
        final int pos_c = childPosition;
        final Context context = parent.getContext();
        /////////////////////////////////////////

        Log.e("TAG", groupPosition + " " + childPosition);

        if(v == null){
            viewHolder = new ViewHolder();
            //v = inflater.inflate(R.layout.list_row, null);
            v = inflater.inflate(R.layout.listview_button_child,parent,false);

            viewHolder.tv_childName = (TextView) v.findViewById(R.id.tv_childs);
            viewHolder.Buttons_ModifyPro = (Button) v.findViewById(R.id.btn_project_modify);
            viewHolder.Buttons_ModifyPro.setFocusable(false);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.Buttons_ModifyPro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 터치 시 해당 아이템 이름 출력
                Toast.makeText(context, "수정을 누름 " + groupList.get(pos) + "," + childList.get(pos).get(pos_c), Toast.LENGTH_SHORT).show();
                Log.i("EEE", "ASDF");
                CategoryBean.CateName = groupList.get(pos);
                ProjectBean.ProjectName = childList.get(pos).get(pos_c);
                Log.i("EEE","SSSSSSASDASDSAD");
                Log.i("EEE",CategoryBean.CateName + ProjectBean.ProjectName);
                //////////인텐트 작성
                Intent intent;
                intent = new Intent(context, ProjectWizard_Modify.class);
                context.startActivity(intent);

            }
        });

        viewHolder.tv_childName.setText(getChild(groupPosition, childPosition));

        return v;
    }

    @Override
    public boolean hasStableIds(){return true;}

    @Override
    public boolean isChildSelectable(int groupPostion, int childPosition){return true;}


    class ViewHolder{
        //public ImageView iv_image;
        public TextView tv_groupName;
        public TextView tv_childName;
        public Button Buttons_MakePro;
        public Button Buttons_DeletePro;
        public Button Buttons_ModifyPro;
    }
}


